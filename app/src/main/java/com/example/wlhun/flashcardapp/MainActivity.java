package com.example.wlhun.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                launchAddCard.putExtra("previousQuestion",tempAnswer );
                launchAddCard.putExtra("previousAnswer", tempQuestion);
                launchAddCard.putExtra("edit", true);
                MainActivity.this.startActivityForResult(launchAddCard, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String question1 = "";
        String answer1 = "";
        if (requestCode == 100) {
            question1 = data.getExtras().getString("question");
            answer1 = data.getExtras().getString("answer");
            if (data.getExtras().getBoolean("changed")) {
                changeCard(question1, answer1);
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
