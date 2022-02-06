package com.example.logindatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.logindatabase.Adapter.CustomAdapterListView;
import com.example.logindatabase.Adapter.CustomAdapterRecyclerView;
import com.example.logindatabase.data.DatabaseHandler;
import com.example.logindatabase.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(UserActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


// *********************Back Button in the Action Bar UI ************************
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


// *********************ListView UI ************************
        //ListView listView = findViewById(R.id.listView);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);




        //list all users of emails from DATABASE
        List<User> userList = db.getAllUsers();
        List<String> emailList = new ArrayList<>();

        for (User user: userList){
            String email = user.getEmail();
            emailList.add(email);
        }




// *******************************Using the default ListView Array Adapter Class****************************
        //ArrayAdapter<String> userAdapter = new ArrayAdapter<>(UserActivity.this, android.R.layout.simple_list_item_1, emailList);
        //listView.setAdapter(userAdapter);

// *******************************Using the Custom ListView Array Adapter Class****************************
        //CustomAdapterListView customAdapter = new CustomAdapterListView(UserActivity.this, R.layout.custom_list_item, emailList);
        //listView.setAdapter(customAdapter);

// *******************************Using the Custom RecyclerView Array Adapter Class****************************
        CustomAdapterRecyclerView customAdapter = new CustomAdapterRecyclerView(UserActivity.this, emailList);

        //ItemTouchHelper allows each itemholder swipeable
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //deleting from Database
                String email = emailList.get(viewHolder.getAdapterPosition());
                int dbPosition = db.getID(email);
                Log.d("Delete_one_DB", "dbPosition: " + dbPosition);
                db.removeOneUser(dbPosition);

                //deleting from string array list
                emailList.remove(viewHolder.getAdapterPosition());

                List<User> userList2 = db.getAllUsers();

                for (User user: userList2){
                    int id = user.getId();
                    String emails = user.getEmail();
                    Log.d("Delete_one_DB", "DataBase: " + id +  ", " +  emails);
                }
                customAdapter.notifyDataSetChanged();
            }
        };

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));








    }



}