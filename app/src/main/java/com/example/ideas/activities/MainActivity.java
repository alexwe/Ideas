package com.example.ideas.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ideas.R;
import com.example.ideas.models.Idea;

public class MainActivity extends AppCompatActivity {
    private EditText editTextIdea;
    private String ideaStr;
    private Idea idea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextIdea = (EditText) findViewById(R.id.editTextIdea);
    }

    public void handleClickSave(View v) {
        Toast.makeText(this, "SAVE button clicked!", Toast.LENGTH_SHORT).show();

        ideaStr = editTextIdea.getText().toString();
        if (ideaStr.length() == 0) {
            return;
        }

        idea = new Idea(ideaStr);

        Intent intent = new Intent(MainActivity.this, SetIdeaActivity.class);
        intent.putExtra("Idea", idea);
        startActivity(intent);
    }
}
