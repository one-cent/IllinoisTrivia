package com.example.illinoistrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        System.out.println("Started End Screen");

        Button submit = findViewById(R.id.submitHighScore);
        Button mainMenu = findViewById(R.id.mainMenu);

        TextView scoreHold = findViewById(R.id.score);

        Intent get = getIntent();
        int score = get.getIntExtra("score", 0);

        scoreHold.setText(score);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Doesn't do anything yet -- need to implement some sort of online interface.
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
