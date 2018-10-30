package com.example.wlhun.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        if (getIntent().getStringExtra("type").equals("edit")) {
            EditText tempQuestion = (EditText) findViewById(R.id.editTextQuestion);
            EditText tempAnswer = (EditText) findViewById(R.id.editTestAnswer);
            tempQuestion.setText(getIntent().getStringExtra("question"), TextView.BufferType.EDITABLE);
            tempAnswer.setText(getIntent().getStringExtra("answer"), TextView.BufferType.EDITABLE);
        }

        findViewById(R.id.backAddButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Question = ((EditText) findViewById(R.id.editTextQuestion)).getText().toString();
                String Answer = ((EditText) findViewById(R.id.editTestAnswer)).getText().toString();
                Intent data = new Intent();
                data.putExtra("question", Question);
                data.putExtra("answer", Answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
