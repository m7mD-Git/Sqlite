package com.qalex.m7md.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button btn;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBAdapter(this);

        btn = (Button) findViewById(R.id.button);
      //txt1 = (EditText) findViewById(R.id.ID);
        // txt2 = (EditText) findViewById(R.id.Name);

//        try {


        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //btn_trans.playSoundEffect(SoundEffectConstants.CLICK);
//				Toast.makeText(getBaseContext(), "Trans. activity :)",
//						Toast.LENGTH_LONG).show();
               // String _id = txt1.getText().toString();
               // String name = txt2.getText().toString();
//                db.open();
//
//                long id = db.insertStudent("2", "ahmed","ooo@ppp.com");
//                if(id>0)
//                    Toast.makeText(getBaseContext(), "Done !!", Toast.LENGTH_LONG).show();
//                else
//
//                    Toast.makeText(getBaseContext(), "Failed !!", Toast.LENGTH_LONG).show();
//                db.close();

                //id = db.insertContact("Mary Jackson", "mary@jackson.com");
//                Cursor cc = db.getContact(1);
//                if (cc.moveToFirst())
//                    DisplayContact(cc);
//                else
//                    Toast.makeText(getBaseContext(), "No contact found", Toast.LENGTH_LONG).show();
                //db.close();

                db.open();
                Cursor c = db.getAllstudens();
                if (c.moveToFirst())
                {
                    do {
                        DisplayContact(c);
                    } while (c.moveToNext());
                }
                db.close();

//                db.open();
//                Cursor cc = db.getContact(2);
//                if (cc.moveToFirst())
//                    DisplayContact(cc);
//                else
//                    Toast.makeText(getBaseContext(), "No contact found", Toast.LENGTH_LONG).show();
//                db.close();

//                db.open();
//        if (db.deleteContact(1))
//            Toast.makeText(getBaseContext(),"Delete successful.", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(getBaseContext(), "Delete failed.", Toast.LENGTH_LONG).show();
//        db.close();

//                        db.open();
//        if (db.updateContact(2, "Wei-Meng Lee", "weimenglee@gmail.com"))
//        Toast.makeText(getBaseContext(), "Update successful.", Toast.LENGTH_LONG).show();
//        else
//        Toast.makeText(getBaseContext(), "Update failed.", Toast.LENGTH_LONG).show();
//        db.close();
            }
        });


    }

    ////////////// Get All Contacts ///////////////////


    /////////////////// get specific contact ////////////
       /* db.open();
        Cursor cc = db.getContact(2);
        if (cc.moveToFirst())
            DisplayContact(cc);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();
*/
    ////////////////////////////// Update
//        db.open();
//        if (db.updateContact(1, "Wei-Meng Lee", "weimenglee@gmail.com"))
//        Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
//        else
//        Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
//        db.close();

//////////////////////////////////  Delete
//        db.open();
//        if (db.deleteContact(1))
//            Toast.makeText(this,"Delete successful.", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
//        db.close();

    /////////////////////////////


    // }*/

    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +"Name: " + c.getString(1) + "\n" +
                        "Email: " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }

    public void CopyDB(InputStream inputStream,
                       OutputStream outputStream)
            throws IOException {
//---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
}