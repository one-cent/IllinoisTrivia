package com.example.illinoistrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.w3c.dom.Text;

import com.google.firebase.*;

public class HighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        final Button button = findViewById(R.id.backToMainMenu);

        final TextView one10 = findViewById(R.id.firstPlaceTen);
        final TextView two10 = findViewById(R.id.secondPlaceTen);
        final TextView three10 = findViewById(R.id.thirdPlaceTen);

        final TextView one20 = findViewById(R.id.firstPlaceTwenty);
        final TextView two20 = findViewById(R.id.secondPlaceTwenty);
        final TextView three20 = findViewById(R.id.thirdPlaceTwenty);

        final TextView one50 = findViewById(R.id.firstPlaceFifty);
        final TextView two50 = findViewById(R.id.secondPlaceFifty);
        final TextView three50 = findViewById(R.id.thirdPlaceFifty);

        final TextView oneAll = findViewById(R.id.firstPlaceAll);
        final TextView twoAll = findViewById(R.id.secondPlaceAll);
        final TextView threeAll = findViewById(R.id.thirdPlaceAll);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://illinoistrivia.firebaseio.com/scores/scoreList.json";

        final JsonParser parser = new JsonParser();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject object;
                        one10.setText("Response is: "+ response);
                        object = (JsonObject) parser.parse(response);

                        one10.setText(object.get("one10").getAsString());
                        two10.setText(object.get("two10").getAsString());
                        three10.setText(object.get("three10").getAsString());

                        one20.setText(object.get("one20").getAsString());
                        two20.setText(object.get("two20").getAsString());
                        three20.setText(object.get("three20").getAsString());

                        one50.setText(object.get("one50").getAsString());
                        two50.setText(object.get("two50").getAsString());
                        three50.setText(object.get("three50").getAsString());

                        oneAll.setText(object.get("oneAll").getAsString());
                        twoAll.setText(object.get("twoAll").getAsString());
                        threeAll.setText(object.get("threeAll").getAsString());
                        Log.d("Tag", "String: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                button.setText(error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScores.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
