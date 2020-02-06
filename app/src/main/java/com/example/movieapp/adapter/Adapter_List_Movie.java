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
import com.example.movieapp.model.MovieData;

import java.util.ArrayList;

public class Adapter_List_Movie extends RecyclerView.Adapter<Adapter_List_Movie.MovieViewHolder> {


    private ArrayList<MovieData> listMovie = new ArrayList<>();
    private OnItemClickListener listener;

    public void setData(ArrayList<MovieData> data) {
        listMovie.clear();
        listMovie.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        holder.titleMovie.setText(listMovie.get(position).getTitle_movie());
        holder.popularity.setText(listMovie.get(position).getPopularity());
        holder.date.setText(listMovie.get(position).getDate_release_movie());
        double score = listMovie.get(position).getRating_movie()*10;
        holder.scoreMovie.setRating((float)((score*5)/100));
        holder.rating.setText(String.valueOf((int)score));
        String img_url = "https://image.tmdb.org/t/p/original" +listMovie.get(position).getImage_movie();
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
                }).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView titleMovie;
        private TextView date;
        private TextView rating;
        private TextView popularity;
        private ImageView imgMovie;
        private RatingBar scoreMovie;
        private ProgressBar progressBar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            titleMovie = itemView.findViewById(R.id.movie_title);
            date = itemView.findViewById(R.id.release_date);
            rating = itemView.findViewById(R.id.vote_average);
            popularity = itemView.findViewById(R.id.popularity);
            imgMovie = itemView.findViewById(R.id.movie_img);
            scoreMovie = itemView.findViewById(R.id.scoreMovie);
            progressBar = itemView.findViewById(R.id.progress_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener !=null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(listMovie.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(MovieData moviedata);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
