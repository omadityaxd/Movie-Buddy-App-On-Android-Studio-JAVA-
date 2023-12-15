package com.example.moviebuddyfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movie_database";
    private static final int DATABASE_VERSION = 1;

    // Define the table and columns
    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DIRECTORS = "directors";
    public static final String COLUMN_CASTS = "casts";
    public static final String COLUMN_RELEASE_DATE = "release_date";

    // Define the table creation SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DIRECTORS + " TEXT, " +
                    COLUMN_CASTS + " TEXT, " +
                    COLUMN_RELEASE_DATE + " TEXT);";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a new movie record
    public Boolean addOne(String title, String directors, String casts, String releaseDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DIRECTORS, directors);
        values.put(COLUMN_CASTS, casts);
        values.put(COLUMN_RELEASE_DATE, releaseDate);

        // Insert the data into the table
        long newRowId = db.insert(TABLE_NAME, null, values);

        // Close the database connection
        db.close();

        return newRowId>0;
    }

    public ArrayList<Movie> getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<Movie> list = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Movie movie = new Movie();
            movie.id=cursor.getInt(0);
            movie.title=cursor.getString(1);
            movie.directors=cursor.getString(2);
            movie.casts=cursor.getString(3);
            movie.releaseDate=cursor.getString(4);
            list.add(movie);
        }
        return list;
    }


}

