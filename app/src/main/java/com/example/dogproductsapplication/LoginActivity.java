package com.example.dogproductsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername,edpassword;
    Button lbtn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edpassword = findViewById(R.id.editTextLoginPassword);
        lbtn=findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);


        // Login Button

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username=edUsername.getText().toString();
                String password=edpassword.getText().toString();
                database db=new database(getApplicationContext(),"dogcare",null,1);

                if (username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Fields can't be empty !!",Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(username,password)==1){
                    Toast.makeText(getApplicationContext(),"Login Sucessful !!",Toast.LENGTH_SHORT).show();

                        //Save Data in key
                        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        //Redirect To register dashboard
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}