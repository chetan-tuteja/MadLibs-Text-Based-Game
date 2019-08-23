package com.example.madlibsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ShowStoryActivity extends AppCompatActivity {

    private String finalStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);

        Intent intent = getIntent();
        finalStory = intent.getStringExtra("thestory");

        setupStory();
    }

    private void setupStory(){
        TextView myTextview = findViewById(R.id.theFinalStoryTV);
        //myTextview.setText(finalStory);

        myTextview.setText(HtmlCompat.fromHtml(finalStory, HtmlCompat.FROM_HTML_MODE_LEGACY));
        myTextview.setMovementMethod(new ScrollingMovementMethod());
    }


    public void makeAnotherStoryClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
