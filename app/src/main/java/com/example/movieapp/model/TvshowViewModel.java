package com.example.movieapp.model;


import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class TvshowViewModel extends ViewModel {

    private static final String API_KEY = "938122a82f3ffa8d480c1b9f9736629b";
    private MutableLiveData<ArrayList<TvShowData>> listTv = new MutableLiveData<>();

    public void setTv() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShowData> tvData = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvShowData tvData1 = new TvShowData(tv);
                        tvData.add(tvData1);
                    }
                    listTv.postValue(tvData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TvShowData>> getMovies() {
        return listTv;
    }
}
