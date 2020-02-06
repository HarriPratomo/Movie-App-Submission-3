package com.example.movieapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.movieapp.R;
import com.example.movieapp.model.MovieData;

public class Detail_Movie_Activity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private ImageView imgMovie;
    private ImageView imgMoviedetail;
    private TextView nameMovie;
    private TextView score;
    private TextView overviewMovie;
    private ProgressBar progressBardetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setTitle("Detail Movie");

        imgMovie = findViewById(R.id.img);
        imgMoviedetail = findViewById(R.id.img_detail);
        nameMovie = findViewById(R.id.name_movie);
        score = findViewById(R.id.scoremovie);
        overviewMovie = findViewById(R.id.overview);
        progressBardetail = findViewById(R.id.progressBardetail);
        progressBardetail.bringToFront();


        final MovieData detail = getIntent().getParcelableExtra(EXTRA_MOVIE);


            nameMovie.setText(detail.getTitle_movie());
            overviewMovie.setText(detail.getOverview());
            double value = detail.getRating_movie() * 10;
            score.setText(String.valueOf((int) value));


            String img_url = "https://image.tmdb.org/t/p/original" + detail.getImage_movie();
            Glide.with(Detail_Movie_Activity.this).load(img_url).into(imgMovie);
            Glide.with(Detail_Movie_Activity.this)
                    .load(img_url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBardetail.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(imgMoviedetail);

    }
}

