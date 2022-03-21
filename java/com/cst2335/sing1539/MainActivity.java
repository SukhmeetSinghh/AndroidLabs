package com.cst2335.sing1539;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail, userPassword;
    static final String TAG = "MainActivity";
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);

        final Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(view -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            goToProfile.putExtra("EMAIL", userEmail.getText().toString());

            Log.e(TAG, "email" + userEmail.getText().toString());

            startActivity(goToProfile);
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("sharedLoginPref", MODE_PRIVATE);
        String uEmail = sharedPreferences.getString("userEmail", "");
        String uPassword = sharedPreferences.getString("userPassword", "");

        userEmail.setText(uEmail);
        userPassword.setText(uPassword);
    }

}