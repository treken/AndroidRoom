package com.ccm.androidroom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

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

    // Here is the code for creating the callback in the WordRoomDatabase class
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen( db );
                    new PopulateDbAsync( INSTANCE ).execute();
                }
            };

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
                            .addCallback( sRoomDatabaseCallback )
                            // Finally, add the callback to the database build sequence
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
