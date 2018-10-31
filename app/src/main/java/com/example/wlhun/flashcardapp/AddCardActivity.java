package com.example.wlhun.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        if(getIntent().getBooleanExtra("edit", false)){
            Log.i("Editable" , "yup");
            EditText tempQuestion = (EditText) findViewById(R.id.editTextQuestion);
            EditText tempAnswer = (EditText) findViewById(R.id.editTestAnswer);
            tempQuestion.setText(getIntent().getStringExtra("previousQuestion"), TextView.BufferType.EDITABLE);
            tempAnswer.setText(getIntent().getStringExtra("previousAnswer"), TextView.BufferType.EDITABLE);
        }
        findViewById(R.id.backAddButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("question", "");
                data.putExtra("answer", "");
                data.putExtra("changed", false);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("question", ((EditText) findViewById(R.id.editTextQuestion)).getText().toString());
                data.putExtra("answer", ((EditText) findViewById(R.id.editTestAnswer)).getText().toString());
                data.putExtra("changed", true);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
