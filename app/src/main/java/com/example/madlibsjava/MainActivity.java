package com.example.madlibsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSpinner();
    }

    private void setupSpinner() {
        ArrayList<String> storyList = new ArrayList<>();
        Scanner reader = new Scanner(getResources().openRawResource(R.raw.storynames));
        while(reader.hasNextLine()) {
            storyList.add(reader.nextLine());
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storyList);
        Spinner mySpinner = findViewById(R.id.storyPickSpinner);
        mySpinner.setAdapter(myAdapter);
    }

    public void getStartedClick(View view) {
        Spinner mySpinner = findViewById(R.id.storyPickSpinner);
        String selectedStory = mySpinner.getSelectedItem().toString();

        Intent intent = new Intent(this, FillInWordsActivity.class);
        intent.putExtra("selectedstory", selectedStory);
        startActivity(intent);
    }
}
