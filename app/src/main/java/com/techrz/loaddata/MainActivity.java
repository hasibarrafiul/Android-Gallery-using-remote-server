package com.techrz.loaddata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private GridView gridView;
    customImageAdapter customImageAdapter;
    ArrayList<imagesArrayList> arrayList;

    private String URL = "https://muthosoft.com/univ/photos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        arrayList = new ArrayList<>();

        httpRequest();
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... param){
                try{
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST");
                    return data;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                super.onPostExecute(data);
                //System.out.println(data);
                String[] splitByComma = data.split(",");
                ArrayList<imagesArrayList> arrayList = new ArrayList<>();
                for (String a : splitByComma){
                    String[] splitByColon = a.split(":");
                        //System.out.println(splitByColon[0]);
                        //System.out.println(splitByColon[1]);
                    imagesArrayList imagesArrayList = new imagesArrayList(splitByColon[0], splitByColon[1]);
                    arrayList.add(imagesArrayList);
                }

                loadData(arrayList);
            }
        }.execute();
    }
    void loadData(ArrayList arrayList){
        //System.out.println(arrayList.size());
        customImageAdapter = new customImageAdapter(this,arrayList);
        gridView.setAdapter(customImageAdapter);
        customImageAdapter.notifyDataSetChanged();

    }
}