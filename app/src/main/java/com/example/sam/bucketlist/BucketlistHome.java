package com.example.sam.bucketlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sam on 19/03/2017.
 */

public class BucketlistHome extends AppCompatActivity{
    private Button loadToken;
    private TextView blists;
    BucketListAPICalls apiCalls = new BucketListAPICalls();
    public static String token;
    final Context context = this;
    private JSONObject bucketlistData;
    private  ListView bucketLister;
    private ArrayAdapter<String> adapter;
    private ArrayList <String>bucketListName =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucketlist_activity_layout);

        loadToken = (Button)findViewById(R.id.tokener);
        bucketLister =(ListView)findViewById(R.id.bucketlister);

        loadToken.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JSONArray myArrayObject = apiCalls.getBucketLists();

                for (int index = 0; index < myArrayObject.length() ; index++) {

                    try {
                        bucketlistData = new JSONObject( myArrayObject.getString(index));
                        bucketListName.add(bucketlistData.getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new ArrayAdapter<String>(getApplication(),
                        android.R.layout.simple_list_item_1, bucketListName);

                bucketLister.setAdapter(adapter);

            }
        });



    }
}


