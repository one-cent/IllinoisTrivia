package com.example.illinoistrivia;

import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsList {
    public static ArrayList<Question> illinoisGeneral;

    public static ArrayList<Question> uiuc;

    public static ArrayList<Question> stateBlank;

    public static ArrayList<Question> sportTeams;

    public static ArrayList<Question> statePolitics;

    public static ArrayList<Question> gameList;

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
        illinoisGeneral.add(new Question("The Illinois state flag has which words on it?", "-", "-", "State Sovereignty National Union", "-", 4));
        illinoisGeneral.add(new Question("The Illinois state flag has which animal on it?", "-", "Eagle", "-", "-", 2));
    }


    public static void addUIUC(){
        //1 - 5
        uiuc.add(new Question("When was the university founded?", "", "", "", "", 2));
        uiuc.add(new Question("How many students attend UIUC?", "", "", "", "", 2));
        uiuc.add(new Question("What is the university's famous chant?", "", "", "", "", 2));
        uiuc.add(new Question("When was 'Hail to the Orange' written?", "", "", "", "", 2));
        uiuc.add(new Question("Which of the following words DOES NOT appear on UIUC's seal?", "", "", "", "", 2));
        //6 - 10
        uiuc.add(new Question("What is the university's motto?", "", "", "", "", 2));
        uiuc.add(new Question("Who is the chancellor of the university?", "", "", "", "", 2));
        uiuc.add(new Question("Which of the following companies WAS NOT created by a UIUC graduate?", "", "", "", "", 2));
        uiuc.add(new Question("What does UGL stand for?", "", "", "", "", 2));
        uiuc.add(new Question("What piece of art do UIUC students Traditionally Touch for Good Luck?", "", "", "", "", 2));
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
        uiuc.add(new Question("The Bears play at [blank] Field?", "", "", "", "", 2));
        uiuc.add(new Question("What is the name of Chicago's professional soccer team?", "", "", "", "", 2));
        uiuc.add(new Question("WThe Cubs play at [blank] Field?", "", "", "", "", 2));
        uiuc.add(new Question("Where do the White Sox play?", "", "", "", "", 2));
        uiuc.add(new Question("Who are the Bears' greatest rivals?", "", "", "", "", 2));
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

    /*public static void buildGameQuestions(boolean pick1, boolean pick2, boolean pick3, boolean pick4, boolean pick5, int numb) {
        Random random = new Random();
        List<Question> current;

        if (pick1) {
            current = illinoisGeneral;
        } else if (pick2) {
            current = uiuc;
        } else if (pick3) {
            current = stateBlank;
        } else if (pick4) {
            current = sportTeams;
        } else {
            current = statePolitics;
        }

        int totalLists = 0;

        if (pick1) {
            totalLists++;
        } if (pick2) {
            totalLists++;
        } if (pick3) {
            totalLists++;
        } if (pick4) {
            totalLists++;
        } if (pick5) {
            totalLists++;
        }

        ArrayList<Question>[] viables = new ArrayList[totalLists];

        if (pick1) {
            viables[totalLists - 1] = illinoisGeneral;
            totalLists--;
        } if (pick2) {
            viables[totalLists - 1] = uiuc;
            totalLists--;
        } if (pick3) {
            viables[totalLists - 1] = stateBlank;
            totalLists--;
        } if (pick4) {
            viables[totalLists - 1] = sportTeams;
            totalLists--;
        } if (pick5) {
            viables[totalLists - 1] = statePolitics;
        }

        int externalCount = viables.length;

        while (gameList.size() < numb) {
            int randInt = random.nextInt(current.size());
            for (int i = 0; i < gameList.size(); i++) {
                if (current.get(randInt).equals(gameList.get(i))) {

                }
            }
        }
    }*/

    public static void buildGameQuestions() {
        for (int i = 0; i < illinoisGeneral.size(); i++) {
            gameList.add(i, illinoisGeneral.get(i));
        }
    }
}
