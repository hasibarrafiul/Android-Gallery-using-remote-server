package com.techrz.loaddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class imageDescription extends AppCompatActivity {

    ImageView image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);
        image = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);

        Intent intent = getIntent();

        String imageID = intent.getStringExtra("imageID");
        String des = intent.getStringExtra("imageDes");

        text.setText(des);
        Picasso.get().load("https://muthosoft.com/univ/photos/"+imageID).resize(1000,1000).into(image);
    }
}