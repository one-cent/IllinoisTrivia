package com.example.illinoistrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionsList.addGenerals();
        QuestionsList.addPolitics();
        QuestionsList.addSports();
        QuestionsList.addStateBlank();
        QuestionsList.addUIUC();

        final RadioButton q10 = findViewById(R.id.tenQuestions);
        final RadioButton q20 = findViewById(R.id.twentyQuestions);
        final RadioButton q50 = findViewById(R.id.fiftyQuestions);
        final RadioButton all = findViewById(R.id.allQuestions);

        final CheckBox general = findViewById(R.id.generalFactsCheck);
        final CheckBox uiuc = findViewById(R.id.schoolFactsCheck);
        final CheckBox stateThings = findViewById(R.id.stateThingCheck);
        final CheckBox sports = findViewById(R.id.stateTeamsCheck);
        final CheckBox politics = findViewById(R.id.statePoliticsCheck);

        Button startGame = findViewById(R.id.startGame);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                general.setChecked(true);
                stateThings.setChecked(true);
                uiuc.setChecked(true);
                sports.setChecked(true);
                politics.setChecked(true);
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q10.isChecked() && !q20.isChecked() && !q50.isChecked() && !all.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please select a question amount to play with.");
                    return;
                } else {
                    int questionPool = 0;
                    if (general.isChecked()) {
                        questionPool += QuestionsList.illinoisGeneral.size();
                    } else if (sports.isChecked()) {
                        questionPool += QuestionsList.sportTeams.size();
                    } else if (politics.isChecked()) {
                        questionPool += QuestionsList.statePolitics.size();
                    } else if (stateThings.isChecked()) {
                        questionPool += QuestionsList.stateBlank.size();
                    } else if (uiuc.isChecked()) {
                        questionPool += QuestionsList.uiuc.size();
                    }

                    if (q10.isChecked() && questionPool < 10) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        return;
                    } else if (q20.isChecked() && questionPool < 20) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        return;
                    } else if (q50.isChecked() && questionPool < 50) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        return;
                    }


                }
            }
        });

    }
}
