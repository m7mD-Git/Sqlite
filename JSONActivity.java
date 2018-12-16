package com.qalex.m7md.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONActivity extends AppCompatActivity {

    String result;
    TextView txt;

    String country;
    long rise;
    long set;

    double temp;
    int pressure;

    int id;
    String mainArr;
    String description;
    String icon;

    CustomAdapter customAdapter;
    //ArrayList <HashMap<String,String>> arrayList = new ArrayList<>();
    ArrayList arrayList2 = new ArrayList();
    ListView  listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        //txt = (TextView)findViewById(R.id.json);

        try {
            result = ReadJSON( getBaseContext().getAssets().open("JSON.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            JSONObject jsonObject = new JSONObject(result);

            /////////////////////////////// SYS //////////////////////////
            JSONObject sys = jsonObject.getJSONObject("sys");
            country = sys.getString("country");
            rise = sys.getLong("sunrise");
            set = sys.getLong("sunset");

            //////////////////////////////////main ////////////////////////////
            JSONObject main = jsonObject.getJSONObject("main");
            temp = main.getDouble("temp");
            pressure = main.getInt("pressure");

/////////////////////////// weather ////////////////
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            for (int i=0 ; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                id = obj.getInt("id");
                mainArr = obj.getString("main");
                description = obj.getString("description");
                icon = obj.getString("icon");



                arrayList2.add(description); arrayList2.add(mainArr); arrayList2.add(icon);
             //   arrayList.add(id);
               // arrayList.add(description);
                //arrayList.add(icon);
                //arrayList2.add(arrayList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       //
        Toast.makeText(this,country+"\n"+rise+"\n"+set+"\n"+temp+"\n"+pressure
                +"\n"+id+"\n"+mainArr+"\n"+description+"\n"+icon,Toast.LENGTH_SHORT).show();
       // txt.setText(result);

        customAdapter = new CustomAdapter(this,arrayList2);

        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList2);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

    }

    private String ReadJSON(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line;

        try {
            while ((line = reader.readLine()) != null){

                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                try {
                     inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return  sb.toString();
    }
}
