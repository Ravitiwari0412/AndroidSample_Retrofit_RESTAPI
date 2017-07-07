package com.codingblocks.restapiretrofitjson.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.PhotoAdapter;
import com.codingblocks.restapiretrofitjson.interfaces.OnUrlClickListener;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity extends AppCompatActivity {
    public static final String TAG= "photo";
    RecyclerView rvphotolist;
    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        rvphotolist=(RecyclerView)findViewById(R.id.rvPhotolist);
        photoAdapter=new PhotoAdapter(PhotosActivity.this,new ArrayList<Photo>());
        rvphotolist.setLayoutManager(new LinearLayoutManager(this));
        rvphotolist.setAdapter(photoAdapter);
        photoAdapter.setOnUrlClickListener(new OnUrlClickListener() {
            @Override
            public void OnUrlClick(String URL, String title) {
                Intent i=new Intent(PhotosActivity.this,ZoomedActivity.class);
                i.putExtra("url",URL);
                i.putExtra("title",title);
                startActivity(i);
            }
        });



        int albumId=getIntent().getIntExtra("albumId",0);
        API.getInstance().getPhotosAPI().getphotosByAlbumId(albumId).enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                Log.d(TAG, "onResponse: ");
                Log.d(TAG, "onResponse: Photo"+response.body().size());
                photoAdapter.updatePhotos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });

    }
}
