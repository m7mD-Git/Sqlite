package com.qalex.m7md.sqlite;

import android.icu.text.UnicodeSetSpanner;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestActivity extends AppCompatActivity {

    String id;
    String name;
    String desc;
TextView textView;
    Http hp;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


textView = (TextView)findViewById(R.id.textView2);
        textView.setVisibility(View.INVISIBLE);
      new GetData().execute();

    }


public class GetData extends AsyncTask<Void,Void,Integer>{

    @Override
    protected void onPreExecute() {
        Toast.makeText(getBaseContext(),"pre",Toast.LENGTH_SHORT).show();
        textView.setText("aaaa");
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        int x = 0;
        for (int i = 0 ; i < 10000;i++){


            x++;
        }
        /*hp = new Http();
        result = hp.CallService("https://api.androidhive.info/contacts/");
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return x;


    }

    @Override
    protected void onPostExecute(Integer integer) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(""+integer);
        super.onPostExecute(integer);
    }
}
    public class Http {


        public String CallService(String s) {
String a = null;
            try {
                URL url = new URL(s);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    a = ConvertToString(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return a;
        }

        private String ConvertToString(InputStream inputStream) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while((line = reader.readLine()) !=null)
                    sb.append(line).append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
return sb.toString();
        }
    }

}
