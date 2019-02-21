package com.ccm.androidroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    // Add a private member variable to hold a reference to the repository.
    // private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    // Add a private LiveData member variable to cache the list of words.
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // Add a constructor that gets a reference to the repository and
    // gets the list of words from the repository.
    LiveData<List<Word>> getAllWords() { return mAllWords; }

    // Create a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is completely hidden from the UI.
    public void insert(Word word) { mRepository.insert(word); }
}