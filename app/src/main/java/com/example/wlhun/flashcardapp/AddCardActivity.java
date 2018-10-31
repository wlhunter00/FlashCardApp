package com.example.wlhun.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        boolean edited = false;

        if(getIntent().getBooleanExtra("edit", false)){
            Log.i("Editable" , "yup");
            EditText tempQuestion = (EditText) findViewById(R.id.editTextQuestion);
            EditText tempAnswer = (EditText) findViewById(R.id.editTestAnswer);
            tempQuestion.setText(getIntent().getStringExtra("previousQuestion"), TextView.BufferType.EDITABLE);
            tempAnswer.setText(getIntent().getStringExtra("previousAnswer"), TextView.BufferType.EDITABLE);
            edited = true;
        }
        findViewById(R.id.backAddButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("question", "");
                data.putExtra("answer", "");
                data.putExtra("changed", false);
                data.putExtra("edited", false);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        final boolean finalEdited = edited;
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("question", ((EditText) findViewById(R.id.editTextQuestion)).getText().toString());
                data.putExtra("answer", ((EditText) findViewById(R.id.editTestAnswer)).getText().toString());
                data.putExtra("changed", true);
                data.putExtra("edited", finalEdited);
                setResult(RESULT_OK, data);
                if(((EditText) findViewById(R.id.editTestAnswer)).getText().toString().equals("") || ((EditText) findViewById(R.id.editTextQuestion)).getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Must put in both an Answer and Question!", Toast.LENGTH_SHORT).show();
                }
                else {
                    finish();
                }
            }
        });
    }
}
