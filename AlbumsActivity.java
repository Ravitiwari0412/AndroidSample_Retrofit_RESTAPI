package com.codingblocks.restapiretrofitjson.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.AlbumAdapter;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsActivity extends AppCompatActivity {
    RecyclerView rvlist;
    AlbumAdapter albumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        rvlist=(RecyclerView)findViewById(R.id.rvAlbumList);
        albumAdapter=new AlbumAdapter(this,new ArrayList<Album>());
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(albumAdapter);

        albumAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View view) {
                Intent albumintent=new Intent(AlbumsActivity.this,PhotosActivity.class);
                albumintent.putExtra("albumId",itemId);
                startActivity(albumintent);
            }
        });


        API.getInstance().getAlbumsAPI().getAlbums().enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
           albumAdapter.updateAlbums(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }
}
