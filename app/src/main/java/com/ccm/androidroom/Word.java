package com.ccm.androidroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table") // class declaration to indicate that it's an entity
public class Word {

    @PrimaryKey // Entity needs a primarey key
    @NonNull // Denotes that a parameter, field, or method return value can never be null.
    @ColumnInfo(name = "word") // Specify the name of the column in the table
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;} // Constructor

    public String getWord(){return this.mWord;}  // Getter method
}