package com.ccm.androidroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ccm.androidroom.Word;
import com.ccm.androidroom.WordDao;

/* Annotate the class to be a Room database, declare the entities that belong
 * in the database and set the version number. Listing the entities will
 * create tables in the database.
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    /* Define the DAOs that work with the database. Provide
     * an abstract "getter" method for each @Dao.
     */
    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context) {
        /* a singleton to prevent having multiple instances of
         * the database opened at the same time.
         */
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    /* Add the code to get a database. This code uses
                     * Room's database builder to create a RoomDatabase
                     * object in the application context from the WordRoomDatabase
                     * class and names it "word_database".
                     */
                    INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database" )
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
