package com.vvit.ammu.mycontacts;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    MyRecyclerAdapter myRecyclerAdapter;
    private List<Contact> contactList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        myRecyclerAdapter = new MyRecyclerAdapter(contactList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(myRecyclerAdapter);
        prepareList();


        fab = (FloatingActionButton) findViewById(R.id.id_add_contact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddContactActivity.class);
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            myRecyclerAdapter = new MyRecyclerAdapter(contactList,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(myRecyclerAdapter);
            getLastContact();
        }
    }

    private void prepareList() {
       DbHelper dbHelper= new DbHelper(this);
        Cursor cursor=dbHelper.getContacts();
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DbHelper.contactName));
                String phone = cursor.getString(cursor.getColumnIndex(DbHelper.mobile));
                contactList.add(new Contact(name, phone));
            } while (cursor.moveToNext());
        }
    }
    public void getLastContact(){
        DbHelper dbHelper= new DbHelper(this);
        Cursor cursor=dbHelper.getContacts();
        if(cursor.moveToLast()) {

                String name = cursor.getString(cursor.getColumnIndex(DbHelper.contactName));
                String phone = cursor.getString(cursor.getColumnIndex(DbHelper.mobile));
                contactList.add(new Contact(name, phone));

        }
    }
}
