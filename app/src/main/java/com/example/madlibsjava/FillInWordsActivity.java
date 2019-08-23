package com.example.madlibsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

import cs193a.stanford.edu.hw3_madlibs.Story;

public class FillInWordsActivity extends AppCompatActivity {

    private String selectedStory;
    private cs193a.stanford.edu.hw3_madlibs.Story storyVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_words);

        Intent intent = getIntent();
        selectedStory = intent.getStringExtra("selectedstory");

        setupScanner();
    }

    private void setupScanner() {
        int fileId = getResources().getIdentifier(selectedStory, "raw", getPackageName());
        Scanner reader = new Scanner(getResources().openRawResource(fileId));

        storyVariable = new Story(reader);
        modifyViews();
    }

    private void modifyViews() {
        TextView wordsLeft = findViewById(R.id.wordsLeftTB);
        TextView whatToType = findViewById(R.id.whatToTypeTV);

        String wordsLeftString = storyVariable.getPlaceholderRemainingCount()+" word(s) left.";
        String whatToTypeString = "Please type an "+storyVariable.getNextPlaceholder()+":";

        wordsLeft.setText(wordsLeftString);
        whatToType.setText(whatToTypeString);
    }


    public void getWordClick(View view) {
        EditText myEditText = findViewById(R.id.enterWordsET);
        String wordEntered = myEditText.getText().toString();
        storyVariable.fillInPlaceholder(wordEntered);
        myEditText.setText("");
        modifyViews();
        checkIfCompleted();
    }

    private void checkIfCompleted() {
        if(storyVariable.getPlaceholderRemainingCount() == 0) {
            Intent intent = new Intent(this, ShowStoryActivity.class);
            intent.putExtra("thestory", storyVariable.toString());
            startActivity(intent);
        }
    }
}
