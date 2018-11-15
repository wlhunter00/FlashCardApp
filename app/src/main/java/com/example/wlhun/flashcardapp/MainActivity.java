package com.example.wlhun.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchAddCard = new Intent(MainActivity.this, AddCardActivity.class);
                launchAddCard.putExtra("edit", false);
                MainActivity.this.startActivityForResult(launchAddCard, 100);
            }
        });
        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempQuestion = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String tempAnswer = ((TextView) findViewById(R.id.flashcard_answer)).getText().toString();
                Intent launchAddCard = new Intent(MainActivity.this, AddCardActivity.class);
                launchAddCard.putExtra("previousQuestion",tempQuestion );
                launchAddCard.putExtra("previousAnswer", tempAnswer);
                launchAddCard.putExtra("edit", true);
                MainActivity.this.startActivityForResult(launchAddCard, 100);
            }
        });
        findViewById(R.id.rightArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size()-1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                if (findViewById(R.id.flashcard_answer).getVisibility() == View.VISIBLE) {
                    flipCard();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        allFlashcards = flashcardDatabase.getAllCards();
        String question1 = "";
        String answer1 = "";
        if (requestCode == 100) {
            question1 = data.getExtras().getString("question");
            answer1 = data.getExtras().getString("answer");
            flashcardDatabase.insertCard(new Flashcard(question1, answer1));
            if (data.getExtras().getBoolean("changed")) {
                changeCard(question1, answer1);
                if(data.getExtras().getBoolean("edited")){
                    Snackbar.make(findViewById(android.R.id.content), "Card Sucessfully Edited!", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Snackbar.make(findViewById(android.R.id.content), "Card Sucessfully Created!", Snackbar.LENGTH_SHORT).show();
                }
            }
            if (findViewById(R.id.flashcard_answer).getVisibility() == View.VISIBLE) {
                flipCard();
            }
        }

    }

    protected void changeCard(String question, String answer) {
        TextView tempQuestion = (TextView) findViewById(R.id.flashcard_question);
        TextView tempAnswer = (TextView) findViewById(R.id.flashcard_answer);
        tempQuestion.setText(question);
        tempAnswer.setText(answer);
    }

    protected void flipCard() {
        if (findViewById(R.id.flashcard_answer).getVisibility() == View.VISIBLE) {
            findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
        }
    }
}
