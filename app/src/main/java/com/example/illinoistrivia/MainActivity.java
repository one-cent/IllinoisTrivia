package com.example.illinoistrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Question> illinoisGeneral;

    public static ArrayList<Question> uiucQuestions;

    public static ArrayList<Question> stateBlank;

    public static ArrayList<Question> sportTeams;

    public static ArrayList<Question> statePolitics;

    public static ArrayList<Question> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        illinoisGeneral = new ArrayList<>();
        uiucQuestions = new ArrayList<>();
        stateBlank = new ArrayList<>();
        sportTeams = new ArrayList<>();
        statePolitics = new ArrayList<>();
        gameList = new ArrayList<>();

        addGenerals();
        addPolitics();
        addSports();
        addStateBlank();
        addUIUC();

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

        q10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q20.setChecked(false);
                q50.setChecked(false);
                all.setChecked(false);
            }
        });

        q20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q10.setChecked(false);
                q50.setChecked(false);
                all.setChecked(false);
            }
        });

        q50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q10.setChecked(false);
                q20.setChecked(false);
                all.setChecked(false);
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q10.setChecked(false);
                q20.setChecked(false);
                q50.setChecked(false);

                general.setChecked(true);
                stateThings.setChecked(true);
                uiuc.setChecked(true);
                sports.setChecked(true);
                politics.setChecked(true);
            }
        });

        Button highScorePage = findViewById(R.id.viewHS);

        highScorePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScores.class);
                startActivity(intent);
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!q10.isChecked() && !q20.isChecked() && !q50.isChecked() && !all.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please select a question amount to play with.");
                    builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();

                } else if ((!uiuc.isChecked() || !stateThings.isChecked() || !general.isChecked() || !politics.isChecked() || !sports.isChecked()) && all.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please make sure all categories are selected.");
                    builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    ArrayList<Question> questionPool = new ArrayList<>();
                    if (general.isChecked()) {
                        questionPool.addAll(illinoisGeneral);
                    }
                    if (sports.isChecked()) {
                        questionPool.addAll(sportTeams);
                    }
                    if (politics.isChecked()) {
                        questionPool.addAll(statePolitics);
                    }
                    if (stateThings.isChecked()) {
                        questionPool.addAll(stateBlank);
                    }
                    if (uiuc.isChecked()) {
                        questionPool.addAll(uiucQuestions);
                    }
                    if (q10.isChecked() && questionPool.size() < 10) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    } else if (q20.isChecked() && questionPool.size() < 20) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    } else if (q50.isChecked() && questionPool.size() < 50) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Please select more categories.");
                        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    } else {
                        int gamesSize = 0;

                        if (q10.isChecked()) {
                            gamesSize = 10;
                        } else if (q20.isChecked()) {
                            gamesSize = 20;
                        } else if (q50.isChecked()) {
                            gamesSize = 50;
                        } else {
                            gamesSize = questionPool.size();
                        }

                        buildGameQuestions(gamesSize, questionPool);
                        Intent intent = new Intent(MainActivity.this, Game.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public static void addGenerals() {
        //1 - 5
        illinoisGeneral.add(new Question("When was Illinois founded?", "-", "-", "1818", "-", 3));
        illinoisGeneral.add(new Question("How many counties are in Illinois?", "102", "-", "-", "-", 1));
        illinoisGeneral.add(new Question("How many presidents are from Illinois?", "-", "-", "2", "-", 3));
        illinoisGeneral.add(new Question("What is the second largest city in Illinois?", "Aurora", "-", "-", "-", 1));
        illinoisGeneral.add(new Question("What is the rough population of Illinois?", "", "", "", "13 Million", 4));
        //6 - 10
        illinoisGeneral.add(new Question("What is Illinois named after?", "-", "-", "-", "A River", 4));
        illinoisGeneral.add(new Question("Illinois is the [blank] most populous state:", "5th", "-", "-", "-", 1));
        illinoisGeneral.add(new Question("Illinois has the [blank] largest state economy in the US:", "-", "5th", "-", "-", 2));
        illinoisGeneral.add(new Question("The Illinois state flag has which words on it?", "-", "-", "State Sovereignty National Union", "-", 3));
        illinoisGeneral.add(new Question("The Illinois state flag has which animal on it?", "-", "Eagle", "-", "-", 2));
    }


    public static void addUIUC(){
        //1 - 5
        uiucQuestions.add(new Question("When was the university founded?", "", "", "", "", 2));
        uiucQuestions.add(new Question("How many students attend UIUC?", "", "", "", "", 2));
        uiucQuestions.add(new Question("What is the university's famous chant?", "", "", "", "", 2));
        uiucQuestions.add(new Question("When was 'Hail to the Orange' written?", "", "", "", "", 2));
        uiucQuestions.add(new Question("Which of the following words DOES NOT appear on UIUC's seal?", "", "", "", "", 2));
        //6 - 10
        uiucQuestions.add(new Question("What is the university's motto?", "", "", "", "", 2));
        uiucQuestions.add(new Question("Who is the chancellor of the university?", "", "", "", "", 2));
        uiucQuestions.add(new Question("Which of the following companies WAS NOT created by a UIUC graduate?", "", "", "", "", 2));
        uiucQuestions.add(new Question("What does UGL stand for?", "", "", "", "", 2));
        uiucQuestions.add(new Question("What piece of art do UIUC students Traditionally Touch for Good Luck?", "", "", "", "", 2));
    }

    public static void addStateBlank(){
        //1 - 5
        stateBlank.add(new Question("What is the state animal?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state amphibian?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state bird?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state reptile?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state vegetable?", "", "", "", "", 2));
        //6 - 10
        stateBlank.add(new Question("What is the state fish?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state flower?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state fossil?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state fruit?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state insect?", "", "", "", "", 2));
        //11-15
        stateBlank.add(new Question("What is the state mineral?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state motto?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state slogan?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state nickname?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state pie?", "", "", "", "", 2));
        //16-20
        stateBlank.add(new Question("What is the state prairie grass?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state reptile?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state snack food?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state song?", "", "", "", "", 2));
        stateBlank.add(new Question("What is the state tree?", "", "", "", "", 2));
    }

    public static void addSports() {
        //1 - 5
        sportTeams.add(new Question("How many years was the Cubs' championship drought?", "", "", "", "", 2));
        sportTeams.add(new Question("When did the White Sox last win the series?", "", "", "", "", 2));
        sportTeams.add(new Question("When did the Bears last win the Super Bowl?", "", "", "", "", 2));
        sportTeams.add(new Question("How many years between 1990 and 2000 were the Bulls the NBA champions?", "", "", "", "", 2));
        sportTeams.add(new Question("The Curse of the [blank] was supposedly the cause of the cubs championship drought?", "", "", "", "", 2));
        //6 - 10
        sportTeams.add(new Question("The Bears play at [blank] Field?", "", "", "", "", 2));
        sportTeams.add(new Question("What is the name of Chicago's professional soccer team?", "", "", "", "", 2));
        sportTeams.add(new Question("WThe Cubs play at [blank] Field?", "", "", "", "", 2));
        sportTeams.add(new Question("Where do the White Sox play?", "", "", "", "", 2));
        sportTeams.add(new Question("Who are the Bears' greatest rivals?", "", "", "", "", 2));
    }

    public static void addPolitics() {
        //1 - 5
        statePolitics.add(new Question("Who is the state Speaker of the House?", "", "", "", "", 2));
        statePolitics.add(new Question("Who is the state governor?", "", "", "", "", 2));
        statePolitics.add(new Question("Who is the secretary of State?", "", "", "", "", 2));
        statePolitics.add(new Question("Which party maintains a majority in the state house?", "", "", "", "", 2));
        statePolitics.add(new Question("Which party maintains a majority in the state senate?", "", "", "", "", 2));
        //6-10
        statePolitics.add(new Question("filler?", "", "", "", "", 2));
        statePolitics.add(new Question("filler?", "", "", "", "", 2));
        statePolitics.add(new Question("filler?", "", "", "", "", 2));
        statePolitics.add(new Question("filler?", "", "", "", "", 2));
        statePolitics.add(new Question("filler?", "", "", "", "", 2));
    }

    public static void buildGameQuestions(int size, ArrayList<Question> list) {
        Collections.shuffle(list);
        for (int i = 0; i < size; i++) {
            gameList.add(i, list.get(i));
            list.remove(i);
        }
        Collections.shuffle(gameList);
    }
}
