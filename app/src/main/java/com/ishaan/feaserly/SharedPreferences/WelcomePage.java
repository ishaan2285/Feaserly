package com.ishaan.feaserly.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ishaan.feaserly.Login;
import com.ishaan.feaserly.R;


public class WelcomePage extends AppCompatActivity {

    Button logoutBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        logoutBT = findViewById(R.id.logoutBT);

        logoutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set LoggedIn status to false
                Savesharedpreference.setLoggedIn(getApplicationContext(), false);

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}
