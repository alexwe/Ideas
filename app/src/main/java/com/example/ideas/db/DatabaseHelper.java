package com.example.ideas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ideas.models.Idea;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the methods for creating, deleting, and updating the SQLite database.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "SDPCryptogram.db";


    private static final String TABLE_IDEA = "ideas";

    private static final String COLUMN_IDEA = "idea_str";
    private static final String COLUMN_LABEL = "label";
    private static final String COLUMN_RATING = "rating";

    private String CREATE_IDEA_TABLE = "CREATE TABLE " + TABLE_IDEA + "("
            + COLUMN_IDEA + " TEXT,"
            + COLUMN_LABEL + " TEXT,"
            + COLUMN_RATING + " INTEGER"
            + ")";

    private String DROP_IDEA_TABLE = "DROP TABLE IF EXISTS " + TABLE_IDEA;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_IDEA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_IDEA_TABLE);
        onCreate(db);
    }

    public void addIdea(Idea idea) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_IDEA, idea.toString());
        values.put(COLUMN_LABEL, idea.getLabel());
        values.put(COLUMN_RATING, idea.getRating());
    }

    public List<Idea> getAllIdeas() {
        String[] columns = {
                COLUMN_IDEA,
                COLUMN_LABEL,
                COLUMN_RATING,
        };
        String sortOrder = COLUMN_IDEA + " ASC";
        List<Idea> ideaList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_IDEA,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);
        Idea idea = null;
        if (cursor.moveToFirst()) {
            do {
//                String ideaStr = cursor.getString(cursor.getColumnIndex(COLUMN_IDEA));
//                String labelStr = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL));
//                int rating = cursor.getInt(cursor.getColumnIndex(COLUMN_RATING));
//
//                idea = new Idea(ideaStr, labelStr, rating);
                idea = new Idea(cursor.getString(cursor.getColumnIndex(COLUMN_IDEA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LABEL)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return ideaList;
    }
}
