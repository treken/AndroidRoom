package com.ccm.androidroom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/* If the table has more than one row and has a conflict you can use
 * @Insert(onConflict = OnConflictStrategy.REPLACE) annotation to resolve it
 */

@Dao // Annotate that it's a Dao class for Room
public interface WordDao {

    // No need for SQLite with this annotation
    @Insert
    void insert(Word word);

    /* SQL querry as a string parameter
     * Generic annot. for deleting multiple entities
     * Method to delete all the words*/
    @Query("DELETE FROM word_table")
    void deleteAll();

    /* When the LiveData changes, the observer is notified and onChanged() is executed.
     * You will then update the cached data in the adapter, and the adapter will update what the user sees.
     */
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords(); // Change method signature with LiveData
}
