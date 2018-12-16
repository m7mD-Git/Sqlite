package com.qalex.m7md.sqlite;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OnlineActivity extends AppCompatActivity {
     String result;
    int id;
    String name;
    String email;
    String address;
    String gender;
   // HttpHandler httpHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        new GetContact().execute();
    }
    public class  GetContact extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(
                    getBaseContext(),"ccccccccccccccccccc",Toast.LENGTH_SHORT
            ).show();
            HttpHandler httpHandler = new HttpHandler();

            //    ArrayList arrayList = new ArrayList();
            result = httpHandler.ServiceCall("https://api.androidhive.info/contacts/");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {



/*            Toast.makeText(
                    getBaseContext(),result,Toast.LENGTH_SHORT
            ).show();

*/
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                for (int i = 0 ; i  < jsonArray.length();i++){

                    JSONObject obj = jsonArray.getJSONObject(i);
                    id = obj.getInt("id");
                    email = obj.getString("email");
                    name = obj.getString("name");
                    gender = obj.getString("gender");
                    JSONObject phone = obj.getJSONObject("phone");


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(
                            getBaseContext(),result,Toast.LENGTH_LONG
                    ).show();
                }
            });
            return null;
        }

    }
}