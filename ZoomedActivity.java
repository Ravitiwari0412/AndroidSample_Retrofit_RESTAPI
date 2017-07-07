package com.codingblocks.restapiretrofitjson.activities.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.restapiretrofitjson.R;
import com.squareup.picasso.Picasso;

public class ZoomedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomed);
        ImageView photo=(ImageView)findViewById(R.id.ivPhoto);
        TextView title=(TextView)findViewById(R.id.tvTitle);
        int albumId=getIntent().getIntExtra("albumId",0);
        String photourl=getIntent().getStringExtra("url");
        String phototitle=getIntent().getStringExtra("title");
        title.setText(phototitle.toString());
        Picasso.with(this).load(photourl).into(photo);
    }
}
