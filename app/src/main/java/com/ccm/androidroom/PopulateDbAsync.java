package com.ccm.androidroom;

import android.os.AsyncTask;

import com.ccm.androidroom.Word;
import com.ccm.androidroom.WordDao;
import com.ccm.androidroom.WordRoomDatabase;

class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final WordDao mDao;

    PopulateDbAsync(WordRoomDatabase db) {
        mDao = db.wordDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Word word = new Word("Hello");
        mDao.insert(word);
        word = new Word("World");
        mDao.insert(word);
        return null;
    }
}