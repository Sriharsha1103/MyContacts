package com.vvit.ammu.mycontacts;

import android.content.Intent;
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
        Button save;
        DbHelper dbHelper;
        Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        sname = (EditText)findViewById(R.id.id_contact_name);
        smobile = (EditText)findViewById(R.id.id_contact_mobile);

        save = (Button)findViewById(R.id.id_contact_save);

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
                setResult(RESULT_OK,new Intent());
                finish();
                //startActivity(new Intent(getApplicationContext(),ContactListActivity.class));
            }
        });


    }
}
