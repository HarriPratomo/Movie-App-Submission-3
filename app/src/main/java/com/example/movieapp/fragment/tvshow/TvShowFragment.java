package com.example.movieapp.fragment.tvshow;


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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.R;
import com.example.movieapp.activity.Detail_Tv_Activity;
import com.example.movieapp.adapter.Adapter_TvShow;
import com.example.movieapp.model.TvShowData;
import com.example.movieapp.model.TvshowViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class TvShowFragment extends Fragment {

    RecyclerView rv_tv;
    private Adapter_TvShow adapter;
    private ProgressBar progressBar;
    private TvshowViewModel tvshowViewModel;


    private Observer<ArrayList<TvShowData>> getTV = new Observer<ArrayList<TvShowData>>() {
        @Override
        public void onChanged(ArrayList<TvShowData> tvData) {
            if (tvData != null) {
                adapter.setData(tvData);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvshowViewModel.getMovies().removeObserver(getTV);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvshowViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvshowViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvshowViewModel.getMovies().observe(Objects.requireNonNull(getActivity()), getTV);
        tvshowViewModel.setTv();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new Adapter_TvShow();
        adapter.notifyDataSetChanged();

        progressBar = view.findViewById(R.id.refresh);
        rv_tv = view.findViewById(R.id.tv_list);
        rv_tv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.divider)));
        rv_tv.addItemDecoration(itemDecoration);
        rv_tv.setHasFixedSize(true);
        rv_tv.setAdapter(adapter);
        progressBar.bringToFront();
        adapter.setOnItemClickListener(new Adapter_TvShow.OnItemClickListener() {
            @Override
            public void onItemClick(TvShowData tvdata) {
                Intent movetoTV = new Intent(getActivity(), Detail_Tv_Activity.class);
                movetoTV.putExtra(Detail_Tv_Activity.EXTRA_TV, tvdata);
                Objects.requireNonNull(getActivity()).startActivity(movetoTV);
            }
        });
    }
}