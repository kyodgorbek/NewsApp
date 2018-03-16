package com.edgar.yodgorbekkomilo.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 2/28/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // Contacts Table Columns names
    public static final String articleID = "id";
    public static final String articleName = "name";
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String FavoritesArticle = "favoritemovie";
    // Contacts table name
    private static final String FavoriteArticle = "favoritesmovie";


    public DatabaseHandler(Context context) {
        super(context, FavoriteArticle, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + FavoriteArticle + "("
                + articleID + " INTEGER PRIMARY KEY," + articleName + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);



   /*     String CREATE_CONTACTS_TABLE = "CREATE TABLE " + FavoritesMovie + "("
                + movieID + " INTEGER PRIMARY KEY," + movieName + " TEXT,"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
   */
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteArticle);

        // Create tables again
        onCreate(db);
    }


    public void insertFavArticle(String id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(articleID, id); // Contact Name
        values.put(articleName, name); // Contact Phone Number

        // Inserting Row
        db.insert(FavoriteArticle, null, values);
        db.close(); // Closing database connection
    }

    public List<FavoriteArticle> getAllFavouriteMovies() {
        List<FavoriteArticle> list = new ArrayList<FavoriteArticle>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FavoriteArticle;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FavoriteArticle article = new FavoriteArticle();
                article.getTitle();
                article.setTitle(cursor.getString(1));
                // Adding movie to list
                list.add(article);
            } while (cursor.moveToNext());
        }

        return list;
    }


}

