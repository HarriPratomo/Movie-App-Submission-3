package com.example.movieapp.fragment.movie;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.activity.Detail_Movie_Activity;
import com.example.movieapp.adapter.Adapter_List_Movie;
import com.example.movieapp.model.MovieData;
import com.example.movieapp.model.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MovieFragment extends Fragment {


    RecyclerView rvlist_movie;
    private Adapter_List_Movie adapter;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;


    private Observer<ArrayList<MovieData>> getMovies = new Observer<ArrayList<MovieData>>() {
        @Override
        public void onChanged(ArrayList<MovieData> movieData) {
            if (movieData != null) {
                adapter.setData(movieData);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieViewModel.getMovies().removeObserver(getMovies);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieViewModel.getMovies().observe(Objects.requireNonNull(getActivity()), getMovies);
        movieViewModel.setMovie();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new Adapter_List_Movie();
        adapter.notifyDataSetChanged();

        progressBar = view.findViewById(R.id.refresh);
        rvlist_movie = view.findViewById(R.id.movie_list);
        rvlist_movie.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.divider)));
        rvlist_movie.addItemDecoration(itemDecoration);
        rvlist_movie.setHasFixedSize(true);
        rvlist_movie.setAdapter(adapter);
        progressBar.bringToFront();
        adapter.setOnItemClickListener(new Adapter_List_Movie.OnItemClickListener() {
            @Override
            public void onItemClick(MovieData moviedata) {
                Intent movietodetail = new Intent(getActivity(), Detail_Movie_Activity.class);
                movietodetail.putExtra(Detail_Movie_Activity.EXTRA_MOVIE, moviedata);
                Objects.requireNonNull(getActivity()).startActivity(movietodetail);
            }
        });
    }
}