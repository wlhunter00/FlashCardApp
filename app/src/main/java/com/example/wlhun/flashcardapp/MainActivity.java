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
               findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
               findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchAddCard = new Intent(MainActivity.this, AddCardActivity.class);
                launchAddCard.putExtra("type","add");
                MainActivity.this.startActivityForResult(launchAddCard, 100);
            }
        });
        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tempQuestion = (TextView) findViewById(R.id.flashcard_question) ;
                TextView tempAnswer = (TextView) findViewById(R.id.flashcard_answer) ;
                Intent launchEditCard = new Intent(MainActivity.this, AddCardActivity.class);
                launchEditCard.putExtra("type", "edit");
                launchEditCard.putExtra("previousQuestion",tempQuestion.getText() );
                launchEditCard.putExtra("previousAnswer", tempQuestion.getText());
                MainActivity.this.startActivityForResult(launchEditCard, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView tempQuestion = (TextView) findViewById(R.id.flashcard_question);
        TextView tempAnswer = (TextView) findViewById(R.id.flashcard_answer);
        String question1 = "";
        String answer1 = "";
        if (requestCode == 100) {
            question1 = data.getExtras().getString("question");
            answer1 = data.getExtras().getString("answer");
        }
        tempQuestion.setText(question1);
        tempAnswer.setText(answer1);
    }
}
