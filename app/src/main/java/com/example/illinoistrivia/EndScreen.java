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

        Button submit = findViewById(R.id.submitHighScore);
        Button mainMenu = findViewById(R.id.mainMenu);

        TextView scoreHold = findViewById(R.id.scoreDisplay);

        Intent get = getIntent();
        int score = get.getIntExtra("score", 0);

        String scoreString = String.valueOf(score);

        scoreHold.setText(scoreString);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Doesn't do anything yet -- need to implement some sort of online interface.
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(EndScreen.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}
