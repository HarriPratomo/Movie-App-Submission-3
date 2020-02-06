package com.example.movieapp.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.movieapp.R;
import com.example.movieapp.model.TvShowData;

import java.util.ArrayList;

public class Adapter_TvShow extends RecyclerView.Adapter<Adapter_TvShow.TvViewHolder> {


    private ArrayList<TvShowData> listTv = new ArrayList<>();
    private OnItemClickListener listener;

    public void setData(ArrayList<TvShowData> data) {
        listTv.clear();
        listTv.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tvshow, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        holder.titleTV.setText(listTv.get(position).getName());
        double score = listTv.get(position).getRating_tv() * 10;
        holder.ratingTV.setRating((float) ((score * 5) / 100));
        holder.average.setText(String.valueOf((int) score));
        String img_url = "https://image.tmdb.org/t/p/original" + listTv.get(position).getPoster_path();
        Glide.with(holder.itemView.getContext())
                .load(img_url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.imgTV);
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;
        private ImageView imgTV;
        private RatingBar ratingTV;
        private ProgressBar progressBar;
        private TextView average;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.name_tv);
            imgTV = itemView.findViewById(R.id.img_tvshow);
            ratingTV = itemView.findViewById(R.id.ratingtv);
            progressBar = itemView.findViewById(R.id.progresstv);
            average = itemView.findViewById(R.id.vote_average_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listTv.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TvShowData tvdata);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
