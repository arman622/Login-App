package com.example.logindatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logindatabase.data.DatabaseHandler;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

// *********************Back Button in the Action Bar UI ************************
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHandler db = new DatabaseHandler(ProfileActivity.this);

        //Buttons
        Button btnLogout = findViewById(R.id.btn_Logout);
        Button btnUsers = findViewById(R.id.btn_Users);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UserActivity.class);
                startActivity(intent);

            }
        });

        //Text Views
        TextView name = findViewById(R.id.name_textView);
        TextView email = findViewById(R.id.email_textView);

        MainActivity curr = new MainActivity();


        int id = curr.getCurrId();

        Log.d("Database", "Profile Activity - Getting CurrId: " + id);

        name.setText("Name: " + db.getUser(id).getFirstName() + " " + db.getUser(id).getLastName());
        email.setText("Email: " + db.getUser(id).getEmail().trim());

    }
}