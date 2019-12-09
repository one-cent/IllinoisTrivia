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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
        final RequestQueue queue = Volley.newRequestQueue(this);
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

                    /*if (gameSize == 10) {
                        scores.remove("one10");
                        scores.addProperty("one10", scoresArray[0]);

                        scores.remove("two10");
                        scores.addProperty("two10", scoresArray[1]);

                        scores.remove("three10");
                        scores.addProperty("three10", scoresArray[2]);
                    } else if (gameSize == 20) {
                        scores.remove("one20");
                        scores.addProperty("one20", scoresArray[0]);

                        scores.remove("two20");
                        scores.addProperty("two20", scoresArray[1]);

                        scores.remove("three20");
                        scores.addProperty("three20", scoresArray[2]);
                    } else if (gameSize == 50) {
                        scores.remove("one50");
                        scores.addProperty("one50", scoresArray[0]);

                        scores.remove("two50");
                        scores.addProperty("two50", scoresArray[1]);

                        scores.remove("three50");
                        scores.addProperty("three50", scoresArray[2]);
                    } else {
                        scores.remove("oneAll");
                        scores.addProperty("oneAll", scoresArray[0]);

                        scores.remove("twoAll");
                        scores.addProperty("twoAll", scoresArray[1]);

                        scores.remove("threeAll");
                        scores.addProperty("threeAll", scoresArray[2]);
                    }

                     */

                    StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.d("Response", response);
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.toString());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String> ();

                            if (gameSize == 10) {
                                params.put("one10", scoresArray[0]);

                                params.put("two10", scoresArray[1]);

                                params.put("three10", scoresArray[2]);
                            } else if (gameSize == 20) {
                                params.put("one20", scoresArray[0]);

                                params.put("two20", scoresArray[1]);

                                params.put("three20", scoresArray[2]);
                            } else if (gameSize == 50) {
                                params.put("one50", scoresArray[0]);

                                params.put("two50", scoresArray[1]);

                                params.put("three50", scoresArray[2]);
                            } else {
                                params.put("oneAll", scoresArray[0]);

                                params.put("twoAll", scoresArray[1]);

                                params.put("threeAll", scoresArray[2]);
                            }

                            return params;
                        }
                    };

                    queue.add(putRequest);
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
