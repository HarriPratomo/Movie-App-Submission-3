package com.example.movieapp.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.movieapp.R;
import com.example.movieapp.model.TvShowData;

public class Detail_Tv_Activity extends AppCompatActivity {


    public static final String EXTRA_TV = "extra_tv";
    TextView titleTv;
    TextView overviewTv;
    TextView datetv;
    TextView numberRating;
    RatingBar rating;
    ImageView imgTv;
    TextView popularityTV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        setTitle("Detail Tv Show");


        titleTv = findViewById(R.id.name);
        overviewTv = findViewById(R.id.overview);
        datetv = findViewById(R.id.datetv);
        numberRating = findViewById(R.id.numberrating);
        rating = findViewById(R.id.ratingBar);
        imgTv = findViewById(R.id.imgTV);
        popularityTV = findViewById(R.id.popularity);


        progressBar = findViewById(R.id.progressBar_detail_tv);
        progressBar.bringToFront();
        final TvShowData tv = getIntent().getParcelableExtra(EXTRA_TV);
        titleTv.setText(tv.getName());
        overviewTv.setText(tv.getOverview());
        double score = tv.getRating_tv() * 10;
        numberRating.setText(String.valueOf((int) score));
        datetv.setText(tv.getDate());
        rating.setRating((float) ((score * 5) / 100));
        popularityTV.setText(tv.getPopularity());


        String img_url = "https://image.tmdb.org/t/p/original" + tv.getPoster_path();
        Glide.with(Detail_Tv_Activity.this)
                .load(img_url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgTv);

    }


}

