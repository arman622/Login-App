package com.example.logindatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logindatabase.Util.Util;
import com.example.logindatabase.data.DatabaseHandler;
import com.example.logindatabase.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Timestamp;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

// *********************Back Button in the Action Bar UI ************************
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        DatabaseHandler db = new DatabaseHandler(SignUpActivity.this);

        //buttons
        Button btnSubmit = findViewById(R.id.btn_Submit);

        //edit text
        EditText editTextRegisterEmail = findViewById(R.id.editText_RegisterEmail);
        editTextRegisterEmail.setInputType(InputType.TYPE_NULL);
        EditText editTextRegisterPassword = findViewById(R.id.editText_RegisterPassword);
        editTextRegisterPassword.setInputType(InputType.TYPE_NULL);
        EditText editTextFirstName = findViewById(R.id.editText_FirstName);
        editTextFirstName.setInputType(InputType.TYPE_NULL);
        EditText editTextLastName = findViewById(R.id.editText_LastName);
        editTextLastName.setInputType(InputType.TYPE_NULL);




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextRegisterEmail.getText().toString().isEmpty() || editTextRegisterPassword.getText().toString().isEmpty() ||
                        editTextFirstName.getText().toString().isEmpty() || editTextLastName.getText().toString().isEmpty() ||
                editTextRegisterPassword.getText().toString().length() <= 8 && editTextRegisterEmail.getText().toString().contains("@")){
                    Snackbar.make(btnSubmit,"Incomplete Email or Password must have at least 8 characters", Snackbar.LENGTH_LONG).show();
                } else {
                    User user = new User();
                    user.setEmail(editTextRegisterEmail.getText().toString().trim());
                    user.setPassword(editTextRegisterPassword.getText().toString().trim());
                    user.setFirstName(editTextFirstName.getText().toString().trim());
                    user.setLastName(editTextLastName.getText().toString().trim());
                    user.setTimestamp(ts.toString());
                    db.addUser(user);


                    User last = db.getLastUser();


                    MainActivity set = new MainActivity();

                    int num = last.getId();

                    set.setCurrId(num);


                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
}