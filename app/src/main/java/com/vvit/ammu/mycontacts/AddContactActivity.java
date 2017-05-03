package com.vvit.ammu.mycontacts;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
        EditText sname,smobile;
        Button save,get,next;
        TextView name,mobile;
        DbHelper dbHelper;
        Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        sname = (EditText)findViewById(R.id.id_contact_name);
        smobile = (EditText)findViewById(R.id.id_contact_mobile);
        name= (TextView)findViewById(R.id.id_name_show);
        mobile = (TextView)findViewById(R.id.id_mobile_show);
        save = (Button)findViewById(R.id.id_contact_save);
        get = (Button)findViewById(R.id.id_contact_show);
        next = (Button)findViewById(R.id.id_next_contact_show);
        dbHelper = new DbHelper(this);
        cursor = dbHelper.getContacts();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = sname.getText().toString();
                String m= smobile.getText().toString();
                Contact contact = new Contact(n,m);
                dbHelper.saveContact(contact);
                Toast.makeText(getApplicationContext(),"Contact Saved",Toast.LENGTH_SHORT).show();
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cursor.moveToFirst()){
                    int index = cursor.getColumnIndex(DbHelper.contactName);
                    String n= cursor.getString(index);
                    String m = cursor.getString(cursor.getColumnIndex(DbHelper.mobile));
                    name.setText(n);
                    mobile.setText(m);

                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isLast()){
                    cursor.moveToNext();
                    int index = cursor.getColumnIndex(DbHelper.contactName);
                    String n= cursor.getString(index);
                    String m = cursor.getString(cursor.getColumnIndex(DbHelper.mobile));
                    name.setText(n);
                    mobile.setText(m);
                }
                else {
                    Toast.makeText(getApplicationContext(),"No more contacts available",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
