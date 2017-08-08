package com.example.ideas.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideas.R;
import com.example.ideas.db.DatabaseHelper;
import com.example.ideas.models.Idea;

public class SetIdeaActivity extends AppCompatActivity {

    private int rating;
    private String ideaStr;
    private String labelStr;
    private Idea idea;
    private TextView textViewIdea;
    private EditText editTextLabel;
//    private TextView textViewLabel;
    private RatingBar ratingBar;
    private Button btnSubmit;
    private DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_idea);

        Intent in = getIntent();
        idea = (Idea)in.getSerializableExtra("Idea");
        ideaStr = idea.toString();
        labelStr = idea.getLabel();

        textViewIdea = (TextView) findViewById(R.id.textViewIdea);
        editTextLabel = (EditText) findViewById(R.id.editTextLabel);
//        textViewLabel = (TextView) findViewById(R.id.textViewLabel);
        // rating = ratingBar.getNumStars();


        if (ideaStr != null && !ideaStr.isEmpty()) {
            textViewIdea.setText(ideaStr);
        }

//        if (labelStr.length() != 0) {
        if (labelStr != null && !labelStr.isEmpty()) {
            editTextLabel.setText(labelStr);
        }

        addListenerOnRating();

        dbh = new DatabaseHelper(this);
    }

    public void addListenerOnRating() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        // will set in XML
//        ratingBar.setStepSize(1.0f);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating = ratingBar.getNumStars();
            }
        });
    }

    public void handleBtnSubmitClick(View view) {
        Toast.makeText(this, "Idea added!", Toast.LENGTH_SHORT).show();

        if (ideaStr != null && !ideaStr.isEmpty()) {
            idea.setIdea(ideaStr);
        }

        if (labelStr != null && !labelStr.isEmpty()) {
            idea.setLabel(labelStr);
        }

        idea.setRating(rating);

        dbh.addIdea(idea);

        Intent intent = new Intent(SetIdeaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
