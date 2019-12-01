package com.example.illinoistrivia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (QuestionsList.gameList.size() == 0) {
            finish();
        } else {
            setContentView(R.layout.activity_game);

            TextView QuestionText = findViewById(R.id.questionText);

            final RadioButton a1 = findViewById(R.id.answer1);
            final RadioButton a2 = findViewById(R.id.answer2);
            final RadioButton a3 = findViewById(R.id.answer3);
            final RadioButton a4 = findViewById(R.id.answer4);

            Button submit = findViewById(R.id.submitAnswer);

            Question current = QuestionsList.gameList.get(0);

            QuestionText.setText(current.questionText);
            a1.setText(current.answer1);
            a2.setText(current.answer2);
            a3.setText(current.answer3);
            a4.setText(current.answer4);

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
                    }
                }
            });
        }
    }
}
