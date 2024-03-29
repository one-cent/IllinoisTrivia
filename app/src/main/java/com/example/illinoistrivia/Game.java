package com.example.illinoistrivia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    public int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpQuestion();
    }

    public void setUpQuestion() {
        if (MainActivity.gameList.size() == 0) {
            Intent out = new Intent(Game.this, EndScreen.class);
            out.putExtra("score", score);
            System.out.println("going to send out intent - Score = " + score);
            startActivity(out);
            finish();
        } else {
            setContentView(R.layout.activity_game);

            final TextView scoreVal = findViewById(R.id.score);
            TextView QuestionText = findViewById(R.id.questionText);

            final RadioButton a1 = findViewById(R.id.answer1);
            final RadioButton a2 = findViewById(R.id.answer2);
            final RadioButton a3 = findViewById(R.id.answer3);
            final RadioButton a4 = findViewById(R.id.answer4);

            Button submit = findViewById(R.id.submitAnswer);

            final Question current = MainActivity.gameList.get(0);

            final TextView timer = findViewById(R.id.timer);

            long mils = 10000;
            final CountDownTimer countDownTimer = new CountDownTimer(mils, 1000) {
                int temp = 10;

                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setText("Time Left: " + temp);
                    temp--;
                }

                @Override
                public void onFinish() {
                    if (a1.isChecked() && current.correctAnswer == 1) {
                        score++;
                    } else if (a2.isChecked() && current.correctAnswer == 2) {
                        score++;
                    } else if (a3.isChecked() && current.correctAnswer == 3) {
                        score++;
                    } else if (a4.isChecked() && current.correctAnswer == 4) {
                        score++;
                    }
                    timer.setText("Time Left: 0");
                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setMessage("You ran out of time! Any checked answer has been submitted!");
                    builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.gameList.remove(0);
                            setUpQuestion();
                        }
                    });
                    builder.show();
                }
            };

            scoreVal.setText("Score: " + score);
            QuestionText.setText(current.questionText);

            countDownTimer.start();
            a1.setText(current.answer1);
            a2.setText(current.answer2);
            a3.setText(current.answer3);
            a4.setText(current.answer4);

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a2.setChecked(false);
                    a3.setChecked(false);
                    a4.setChecked(false);
                }
            });

            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a1.setChecked(false);
                    a3.setChecked(false);
                    a4.setChecked(false);
                }
            });

            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a2.setChecked(false);
                    a1.setChecked(false);
                    a4.setChecked(false);
                }
            });

            a4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a2.setChecked(false);
                    a3.setChecked(false);
                    a1.setChecked(false);
                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!a1.isChecked() && !a2.isChecked() && !a3.isChecked() && !a4.isChecked()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                        builder.setMessage("Please select an answer");
                        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                        builder.show();
                    } else {
                        if (a1.isChecked() && current.correctAnswer == 1) {
                            score++;
                        } else if (a2.isChecked() && current.correctAnswer == 2) {
                            score++;
                        } else if (a3.isChecked() && current.correctAnswer == 3) {
                            score++;
                        } else if (a4.isChecked() && current.correctAnswer == 4) {
                            score++;
                        }
                        MainActivity.gameList.remove(0);
                        countDownTimer.cancel();
                        setUpQuestion();
                    }
                }
            });
        }
    }
}
