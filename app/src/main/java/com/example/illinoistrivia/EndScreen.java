package com.example.illinoistrivia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class EndScreen extends AppCompatActivity {

    public static int gameSize;

    public static JsonObject scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        final Button submit = findViewById(R.id.submitHighScore);
        Button mainMenu = findViewById(R.id.mainMenu);

        final EditText name = findViewById(R.id.nameEnter);

        TextView scoreHold = findViewById(R.id.scoreDisplay);

        Intent get = getIntent();
        final int score = get.getIntExtra("score", 0);

        String scoreString = String.valueOf(score);

        scoreHold.setText(scoreString);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="https://illinoistrivia.firebaseio.com/scores/scoreList.json";

        final JsonParser parser = new JsonParser();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        scores = (JsonObject) parser.parse(response);
                        Log.d("Tag", "String: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                submit.setText(error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        final String[] scoresArray = new String[3];

        if (gameSize == 10) {
            scoresArray[0] = scores.get("one10").getAsString();
            scoresArray[1] = scores.get("two10").getAsString();
            scoresArray[2] = scores.get("three10").getAsString();
        } else if (gameSize == 20) {
            scoresArray[0] = scores.get("one20").getAsString();
            scoresArray[1] = scores.get("two20").getAsString();
            scoresArray[2] = scores.get("three20").getAsString();
        } else if (gameSize == 50) {
            scoresArray[0] = scores.get("one50").getAsString();
            scoresArray[1] = scores.get("two50").getAsString();
            scoresArray[2] = scores.get("three50").getAsString();
        } else {
            scoresArray[0] = scores.get("oneAll").getAsString();
            scoresArray[1] = scores.get("twoAll").getAsString();
            scoresArray[2] = scores.get("threeAll").getAsString();
        }

        int[] relScores = new int[3];
        relScores[0] = Integer.parseInt(scoresArray[0].split(" ")[1]);
        relScores[1] = Integer.parseInt(scoresArray[1].split(" ")[1]);
        relScores[2] = Integer.parseInt(scoresArray[2].split(" ")[1]);

        final int[] relaventScores = relScores;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().equals("")) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(EndScreen.this);
                    alert.setMessage("Please enter a name!");
                    alert.show();
                } else {
                    if (score > relaventScores[0]) {
                        String temp1 = scoresArray[0];
                        String temp2 = scoresArray[1];
                        scoresArray[0] = name.getText() + ": " + score;
                        scoresArray[1] = temp1;
                        scoresArray[2] = temp2;
                    } else if (score > relaventScores[1]) {
                        String temp1 = scoresArray[1];
                        scoresArray[1] = name.getText() + ": " + score;
                        scoresArray[2] = temp1;
                    } else if (score> relaventScores[2]) {
                        scoresArray[2] = name.getText() + ": " + score;
                    }

                    if (gameSize == 10) {

                    } else if (gameSize == 20) {

                    } else if (gameSize == 50) {

                    } else {

                    }



                }
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
