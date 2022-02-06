package com.example.logindatabase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.logindatabase.data.DatabaseHandler;
import com.example.logindatabase.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static int currId;

    DatabaseHandler db = new DatabaseHandler(MainActivity.this);


//    public MainActivity(){
//
//    }
//    public MainActivity(int currId){
//        currId = currId;
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*******************Back-End Work Test **************//

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());



        //user test
        User arman = new User();
        arman.setEmail("armanthaque@yahoo.com");
        arman.setPassword("123");
        arman.setFirstName("Arman");
        arman.setLastName("Haque");
        arman.setTimestamp(ts.toString());

        //db.addUser(arman);


        //***************************UI********************//
        ConstraintLayout main = findViewById(R.id.main_layout);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView textViewSignUp = findViewById(R.id.textView_SignUp);
        EditText editTextEmail = findViewById(R.id.editText_Email);
        editTextEmail.setInputType(InputType.TYPE_NULL);
        EditText editTextPassword = findViewById(R.id.editText_Password);
        editTextPassword.setInputType(InputType.TYPE_NULL);


        //listing all the user

        List<User> userList = db.getAllUsers();

        for(User user: userList){
            Log.d("Database List", "UserList: " + user.getId() + " - " + user.getEmail() + ", " + user.getPassword() + ", " +
                    user.getFirstName() + ", " + user.getLastName() + ", " + user.getTimestamp());
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(User user: userList){
                    if(user.getEmail().equals(editTextEmail.getText().toString().trim()) &&
                            user.getPassword().equals(editTextPassword.getText().toString().trim())){
                        Log.d("Database", "Email and Password are correct" );

                        currId = user.getId();

                        Log.d("Database", "OnCreate - Storing CurrId: " + currId);

                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(btnLogin,"Incorrect Email and Password", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });



        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }

    public int getCurrId(){
        return currId;
    }

    public void setCurrId(int currId){
        this.currId = currId;
    }


}