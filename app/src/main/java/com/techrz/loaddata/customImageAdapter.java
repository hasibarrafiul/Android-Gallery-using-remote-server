package com.techrz.loaddata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customImageAdapter extends BaseAdapter {

    Context context;
    ArrayList<imagesArrayList> arrayList;
    String imageURL, description;
    public customImageAdapter(Context context, ArrayList<imagesArrayList> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.datacustomview, parent, false);

        ImageView coursename = rowView.findViewById(R.id.coursename);



        imagesArrayList imagesArrayList = arrayList.get(position);


        imageURL = imagesArrayList.getimageID();
        description = imagesArrayList.getDescription();
        Picasso.get().load("https://muthosoft.com/univ/photos/"+imagesArrayList.getimageID()).resize(1000,1000).into(coursename);
        //coursename.setText(imagesArrayList.getimageID());
        //System.out.println(data);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, imageDescription.class);
                intent.putExtra("imageID", imagesArrayList.getimageID());
                intent.putExtra("imageDes", imagesArrayList.getDescription());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
