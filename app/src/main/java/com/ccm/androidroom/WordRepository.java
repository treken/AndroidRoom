package com.ccm.androidroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import com.ccm.androidroom.WordRoomDatabase;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // Add a constructor that gets a handle to the database and initializes the member variables.
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    /* Add a wrapper for getAllWords(). Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    /* Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
     */
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    // the AsyncTask
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}