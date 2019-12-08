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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        Button submit = findViewById(R.id.submitHighScore);
        Button mainMenu = findViewById(R.id.mainMenu);

        final EditText name = findViewById(R.id.nameEnter);

        TextView scoreHold = findViewById(R.id.scoreDisplay);

        Intent get = getIntent();
        final int score = get.getIntExtra("score", 0);

        String scoreString = String.valueOf(score);

        scoreHold.setText(scoreString);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Doesn't do anything yet -- need to implement some sort of online interface.
                AlertDialog.Builder alert = new AlertDialog.Builder(EndScreen.this);
                alert.setMessage("Your score has been submitted!");
                alert.show();

                // Instantiate the RequestQueue.
                final RequestQueue queue = Volley.newRequestQueue(EndScreen.this);
                String url ="https://illinoistrivia.firebaseio.com/scores/scoreList.json";

                final JsonParser parser = new JsonParser();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                JsonObject object;
                                object = (JsonObject) parser.parse(response);

                                if (gameSize == 10) {
                                    String first = object.get("one10").getAsString();
                                    String second = object.get("two10").getAsString();
                                    String third = object.get("three10").getAsString();

                                    String[] firstA = first.split(" ");
                                    String[] secondA = second.split(" ");
                                    String[] thirdA = third.split(" ");

                                    int firstInt = Integer.parseInt(firstA[1]);
                                    int secondInt = Integer.parseInt(secondA[1]);
                                    int thirdInt = Integer.parseInt(thirdA[1]);
                                    int[] scores = new int[] {firstInt, secondInt, thirdInt};

                                    if (score > scores[0]) {
                                        String temp1 = first;
                                        String temp2 = second;
                                        first = name.getText() + ": " + score;
                                        second = temp1;
                                        third = temp2;
                                    } else if (score > scores[1]) {
                                        String temp1 = second;
                                        second = name.getText() + ": " + score;
                                        third = temp1;
                                    } else if (score > scores[2]) {
                                        third = name.getText() + ": " + score;
                                    }

                                    String url1 = "https://illinoistrivia.firebaseio.com/scores/scoreList/one10";
                                    String url2 = "https://illinoistrivia.firebaseio.com/scores/scoreList/two10";
                                    String url3 = "https://illinoistrivia.firebaseio.com/scores/scoreList/three10";

                                    final String f = first;
                                    final String s = second;
                                    final String t = third;

                                    StringRequest submit1 = new StringRequest(Request.Method.PUT, url1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("one10", f);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit2 = new StringRequest(Request.Method.PUT, url2, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("two10", s);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit3 = new StringRequest(Request.Method.PUT, url3, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("three10", t);
                                            return MyData;
                                        }
                                    };

                                    queue.add(submit1);
                                    queue.add(submit2);
                                    queue.add(submit3);
                                } else if (gameSize == 20) {
                                    String first = object.get("one20").getAsString();
                                    String second = object.get("two20").getAsString();
                                    String third = object.get("three20").getAsString();

                                    String[] firstA = first.split(" ");
                                    String[] secondA = second.split(" ");
                                    String[] thirdA = third.split(" ");

                                    int firstInt = Integer.parseInt(firstA[1]);
                                    int secondInt = Integer.parseInt(secondA[1]);
                                    int thirdInt = Integer.parseInt(thirdA[1]);
                                    int[] scores = new int[] {firstInt, secondInt, thirdInt};

                                    if (score > scores[0]) {
                                        String temp1 = first;
                                        String temp2 = second;
                                        first = name.getText() + ": " + score;
                                        second = temp1;
                                        third = temp2;
                                    } else if (score > scores[1]) {
                                        String temp1 = second;
                                        second = name.getText() + ": " + score;
                                        third = temp1;
                                    } else if (score > scores[2]) {
                                        third = name.getText() + ": " + score;
                                    }

                                    String url1 = "https://illinoistrivia.firebaseio.com/scores/scoreList/one20";
                                    String url2 = "https://illinoistrivia.firebaseio.com/scores/scoreList/two20";
                                    String url3 = "https://illinoistrivia.firebaseio.com/scores/scoreList/three20";

                                    final String f = first;
                                    final String s = second;
                                    final String t = third;

                                    StringRequest submit1 = new StringRequest(Request.Method.PUT, url1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("one20", f);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit2 = new StringRequest(Request.Method.PUT, url2, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("two20", s);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit3 = new StringRequest(Request.Method.PUT, url3, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("three20", t);
                                            return MyData;
                                        }
                                    };

                                    queue.add(submit1);
                                    queue.add(submit2);
                                    queue.add(submit3);
                                } else if (gameSize == 50) {
                                    String first = object.get("one50").getAsString();
                                    String second = object.get("two50").getAsString();
                                    String third = object.get("three50").getAsString();

                                    String[] firstA = first.split(" ");
                                    String[] secondA = second.split(" ");
                                    String[] thirdA = third.split(" ");

                                    int firstInt = Integer.parseInt(firstA[1]);
                                    int secondInt = Integer.parseInt(secondA[1]);
                                    int thirdInt = Integer.parseInt(thirdA[1]);
                                    int[] scores = new int[] {firstInt, secondInt, thirdInt};

                                    if (score > scores[0]) {
                                        String temp1 = first;
                                        String temp2 = second;
                                        first = name.getText() + ": " + score;
                                        second = temp1;
                                        third = temp2;
                                    } else if (score > scores[1]) {
                                        String temp1 = second;
                                        second = name.getText() + ": " + score;
                                        third = temp1;
                                    } else if (score > scores[2]) {
                                        third = name.getText() + ": " + score;
                                    }

                                    String url1 = "https://illinoistrivia.firebaseio.com/scores/scoreList/one50";
                                    String url2 = "https://illinoistrivia.firebaseio.com/scores/scoreList/two50";
                                    String url3 = "https://illinoistrivia.firebaseio.com/scores/scoreList/three50";

                                    final String f = first;
                                    final String s = second;
                                    final String t = third;

                                    StringRequest submit1 = new StringRequest(Request.Method.PUT, url1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("one50", f);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit2 = new StringRequest(Request.Method.PUT, url2, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("two50", s);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit3 = new StringRequest(Request.Method.PUT, url3, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("three50", t);
                                            return MyData;
                                        }
                                    };

                                    queue.add(submit1);
                                    queue.add(submit2);
                                    queue.add(submit3);
                                } else {
                                    String first = object.get("oneAll").getAsString();
                                    String second = object.get("twoAll").getAsString();
                                    String third = object.get("threeAll").getAsString();

                                    String[] firstA = first.split(" ");
                                    String[] secondA = second.split(" ");
                                    String[] thirdA = third.split(" ");

                                    int firstInt = Integer.parseInt(firstA[1]);
                                    int secondInt = Integer.parseInt(secondA[1]);
                                    int thirdInt = Integer.parseInt(thirdA[1]);
                                    int[] scores = new int[] {firstInt, secondInt, thirdInt};

                                    if (score > scores[0]) {
                                        String temp1 = first;
                                        String temp2 = second;
                                        first = name.getText() + ": " + score;
                                        second = temp1;
                                        third = temp2;
                                    } else if (score > scores[1]) {
                                        String temp1 = second;
                                        second = name.getText() + ": " + score;
                                        third = temp1;
                                    } else if (score > scores[2]) {
                                        third = name.getText() + ": " + score;
                                    }

                                    String url1 = "https://illinoistrivia.firebaseio.com/scores/scoreList/oneAll";
                                    String url2 = "https://illinoistrivia.firebaseio.com/scores/scoreList/twoAll";
                                    String url3 = "https://illinoistrivia.firebaseio.com/scores/scoreList/threeAll";

                                    final String f = first;
                                    final String s = second;
                                    final String t = third;

                                    StringRequest submit1 = new StringRequest(Request.Method.PUT, url1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("oneAll", f);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit2 = new StringRequest(Request.Method.PUT, url2, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("twoAll", s);
                                            return MyData;
                                        }
                                    };

                                    StringRequest submit3 = new StringRequest(Request.Method.PUT, url3, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("Works", "Submission worked");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Tag", "Ouch");
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            MyData.put("threeAll", t);
                                            return MyData;
                                        }
                                    };

                                    queue.add(submit1);
                                    queue.add(submit2);
                                    queue.add(submit3);
                                }

                                Log.d("Tag", "String: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
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
