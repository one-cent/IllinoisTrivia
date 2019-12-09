package com.example.illinoistrivia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

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

    public static FirebaseAuth auth;

    public static FirebaseUser user;

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
                            EndScreen.gameSize = gamesSize;
                        } else if (q20.isChecked()) {
                            gamesSize = 20;
                            EndScreen.gameSize = gamesSize;
                        } else if (q50.isChecked()) {
                            gamesSize = 50;
                            EndScreen.gameSize = gamesSize;
                        } else {
                            gamesSize = questionPool.size();
                            EndScreen.gameSize = gamesSize;
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
        illinoisGeneral.add(new Question("When was Illinois founded?", "1766", "1654", "1818", "1901", 3));
        illinoisGeneral.add(new Question("How many counties are in Illinois?", "102", "86", "19", "34", 1));
        illinoisGeneral.add(new Question("How many presidents are from Illinois?", "1", "6", "2", "9", 3));
        illinoisGeneral.add(new Question("What is the second largest city in Illinois?", "Aurora", "Chicago", "Peoria", "Naperville", 1));
        illinoisGeneral.add(new Question("What is the rough population of Illinois?", "7 Million", "9 Million", "16 Million", "13 Million", 4));
        //6 - 10
        illinoisGeneral.add(new Question("What is Illinois named after?", "A Crop", "A Horse", "A War Weapon", "A River", 4));
        illinoisGeneral.add(new Question("Illinois is the [blank] most populous state:", "5th", "3rd", "7th", "9th", 1));
        illinoisGeneral.add(new Question("Illinois has the [blank] largest state economy in the US:", "4th", "5th", "7th", "3rd", 2));
        illinoisGeneral.add(new Question("The Illinois state flag has which words on it?", "December 7, 1787", "In God We Trust", "State Sovereignty National Union", "United We Stand, Divided We Fall", 3));
        illinoisGeneral.add(new Question("The Illinois state flag has which animal on it?", "A Horse", "An Eagle", "A Snake", "An Elk", 2));
        //11- 15
        illinoisGeneral.add(new Question("In which city was the Ice Cream Sundae invented?", "Rosemont", "Evanston", "Rockford", "Gurnee", 2));
        illinoisGeneral.add(new Question("Which Illinois Bakery is the largest in the world?", "Little Debbie", "Deer Field", "Nabisco", "None of the above", 3));
        illinoisGeneral.add(new Question("Where in Illinois were Twinkies invented?", "Lake Forest", "Libertyville", "Chicago", "River Forest", 4));
        illinoisGeneral.add(new Question("Approximately how much of Illinois' land is farmland?", "60%", "75%", "80%", "90%", 3));
        illinoisGeneral.add(new Question("Wilmette Illinois is home to a large temple to which minority religion?", "Jain", "Zoroastrian", "Baháʼí", "Pastafarian", 3));
    }


    public static void addUIUC(){
        //1 - 5
        uiucQuestions.add(new Question("When was the university founded?", "1834", "1867", "1892", "1843", 2));
        uiucQuestions.add(new Question("The campus library system possesses the [blank] - largest university library in the UnitedStates!", "It's the largest, duh!", "second", "third", "fourth", 2));
        uiucQuestions.add(new Question("The University is home to which of the following?", "Fastest Supercomputer", "smallest corn husk", "Oldest Penny", "Slowest Wifi", 1));
        uiucQuestions.add(new Question("How many buildings are on campus?", "263", "421", "651", "158", 3));
        uiucQuestions.add(new Question("The University is home to roughly how many undergraduates this fall?", "16,319", "48,756", "51,196", "33,850", 4));
        //6 - 10
        uiucQuestions.add(new Question("What University President requested squirrels be brought to campus?", "Michael Hogan", "James J. Stukel", "Andrew S. Draper", "Stanley O. Ikenberry", 3));
        uiucQuestions.add(new Question("Who is the chancellor of the university?", "Andreas C. Cangellaris", "Robert J. Jones", "Andreeseesen Horowitz", "Abe Saperstein", 2));
        uiucQuestions.add(new Question("How much was tuition at the University of Illinois in 1868 per year?", "$56", "$100", "$204", "$15", 4));
        uiucQuestions.add(new Question("What does UGL stand for?", "Undergraduate Library", "Under Grainger Library", "Ugly", "Urbana's Giant Library", 1));
        uiucQuestions.add(new Question("What  do UIUC students Traditionally Touch for Good Luck?", "A bust of Abraham Lincoln", "Morrow Plots", "The Alma Mater", "Squirrels", 1));
    }

    public static void addStateBlank(){
        //1 - 5
        stateBlank.add(new Question("What is the state animal?", "White-Tailed Deer", "Morgan Horse", "Ring-Tailed Cat", "Boykin Spaniel", 1));
        stateBlank.add(new Question("What is the state amphibian?", "Hyla Exima", "Hyla Cinerea", "Ambystoma Tingrinum", "Bufo Speciosus", 3));
        stateBlank.add(new Question("What is the state bird?", "Cactus Wren", "Lark Bunting", "American Robin", "Northern Cardinal", 4));
        stateBlank.add(new Question("Illinois is home to the largest [blank].", "Fire Hydrant", "Bottle of Ketchup", "Toy Lego", "Penny", 2));
        stateBlank.add(new Question("What is the state vegetable?", "Sweet Corn", "Sweet Potato", "White Potato", "Collard Greens", 1));
        //6 - 10
        stateBlank.add(new Question("What is the state fish?", "Largemouth Bass", "Cutthroat Trout", "Bluegill", "Striped Bass", 3));
        stateBlank.add(new Question("What is the state flower?", "Hibiscus", "Peony", "Wild Prarie Rose", "Purple Violet", 4));
        stateBlank.add(new Question("What is the state fossil?", "Primitive Whale (Basilosaurus Cetoides)", "Tully Monster (Tullimonstrum Gregarium)", "Saber-Toothed Cat (Smilodon Californicus)", "Petrified  Palmwood (Palmoxylon)", 2));
        stateBlank.add(new Question("What is the state fruit?", "Blackberry", "Huckleberry", "Scuppernong Grape", "Goldrush Apple", 4));
        stateBlank.add(new Question("What is the state insect?", "European Mantis", "7-Spotted Ladybug", "Monarch Butterfly", "Say's Firefly", 3));
        //11-15
        stateBlank.add(new Question("What is the state mineral?", "Fluorite", "Hematite", "Sillimanite", "Coal", 1));
        stateBlank.add(new Question("What is the state motto?", "Ditat Deus (Latin, God Enriches)", "Eureka (Greek, I Have Found It)", "Wisdom, Justice, and Moderation", "State Sovereignty, National Union", 4));
        stateBlank.add(new Question("What is the state slogan?", "Still Revolutionary, Full of Surpirses", "Are You Up For Amazing?", "Life Changing, Fields of Opportunity", "The Natural State, The Land of Opportunity", 2));
        stateBlank.add(new Question("What is the state nickname?", "The Prairie State", "The Gem State", "The Hoosier State", "The Old Line State", 1));
        stateBlank.add(new Question("What is the state pie?", "Key Lime Pie", "Pumpkin Pie", "Buttermilk Pie", "Mud Pie", 2));
        //16-20
        stateBlank.add(new Question("What is the state prairie grass?", "Little Bluestem Grass", "Blue Grama Grass", "Big Bluestem", "Bluebunch Wheatgrass", 3));
        stateBlank.add(new Question("How many state capitals has Illinois had?", "1", "2", "3", "4", 3));
        stateBlank.add(new Question("What is the state snack food?", "Popcorn", "Potatoe Chips", "Twizzlers", "Cupcakes", 1));
        stateBlank.add(new Question("What is the state song?", "The Illinois March", "Illinois", "Old Folks at Home", "The Song of Illinois", 2));
        stateBlank.add(new Question("What is the state tree?", "Longlead Pine", "Pandanus", "Loblolly Pine", "White Oak", 4));
    }

    public static void addSports() {
        //1 - 5
        sportTeams.add(new Question("How many years was the Cubs' championship drought?", "97 Years", "8 Years", "71 Years", "108 Years", 4));
        sportTeams.add(new Question("When did the White Sox last win the series?", "2001", "2005", "1998", "2003", 2));
        sportTeams.add(new Question("When did the Bears last win the Super Bowl?", "1974", "2000", "1986", "1993", 3));
        sportTeams.add(new Question("How many years between 1990 and 2000 were the Bulls the NBA champions?", "6", "3", "5", "7", 1));
        sportTeams.add(new Question("The Curse of the [blank] was supposedly the cause of the cubs championship drought?", "Billy Goat", "White Sock", "Missing Spoon", "Boston Balloon", 1));
        //6 - 10
        sportTeams.add(new Question("The Bears play at [blank] Field?", "CenturyLink", "Soldier", "Ford", "Lincoln Financial Field", 2));
        sportTeams.add(new Question("What is the name of Chicago's professional soccer team?", "Chicago Dogs", "Windy City ThunderBolts", "Chicago Fire", "Chicago Lightning", 3));
        sportTeams.add(new Question("WThe Cubs play at [blank] Field?", "Wrigley", "Citi", "Chase", "Tropicana", 1));
        sportTeams.add(new Question("Where do the White Sox play?", "Busch Stadium", "Guaranteed Rate Field", "T-Mobile Park", "Coors Field", 2));
        sportTeams.add(new Question("Who are the Chicago Blackhawks' greatest rivals?", "Colorado Avalanche", "San Jose Sharks", "Pittsburgh Penguins", "Arizona Coyotes", 3));
    }

    public static void addPolitics() {
        //1-5
        statePolitics.add(new Question("Who is the state Speaker of the House?", "David Ralston", "Scott Saiki", "Michael Madigan", "Scott Bedke", 3));
        statePolitics.add(new Question("Who is the state governor?", "Kay Ivey", "J.B. Pritzker", "Ned Lamont", "John Carney", 2));
        statePolitics.add(new Question("Who is the lieutenant governor of Illinois", "Juliana Stratton ", "Jesse White ", "J. B. Pritzker", "Kwame Raoul", 1));
        statePolitics.add(new Question("Who is the Secretary of State of Illinois?", "David Ralston", "Ned Lamont", "Michael Madigan", "Jesse White", 3));
        statePolitics.add(new Question("How many Illinois governors have been charged with crimes during or after their governorships?", "2", "8", "6", "4", 3));
        //6-10
        statePolitics.add(new Question("What was Rod Blagojevich impeacehd for?", "Corruption", "Money Laundering", "DUI", "Affair", 1));
        statePolitics.add(new Question("How many people are in the State House of Representatives?", "82", "243", "118", "108", 3));
        statePolitics.add(new Question("How manny people are in the State Senate", "8 ", "60", "100", "59", 4));
        statePolitics.add(new Question("What is the state capital of Illinois?", "Springfield", "Aurora", "Chicago", "Mundelein", 1));
        statePolitics.add(new Question("What is the majority party of the Illinois House and Senate?", "Republican", "Libertarian", "Democratic", "Green", 3));
    }

    public static void buildGameQuestions(int size, ArrayList<Question> list) {
        Collections.shuffle(list);
        for (int i = 0; i < size; i++) {
            gameList.add(i, list.get(0));
            list.remove(0);
        }
        Collections.shuffle(gameList);
    }


}
