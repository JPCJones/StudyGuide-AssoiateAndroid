package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {WordObject.class}, version = 1, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordDataBase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized WordDataBase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    WordDataBase.class, "word_database")
                    // Wipes and rebuilds instead of migrating
                    // if no Migration object.
                    // Migration is not part of this practical.
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabaseCallBack)
                    .build();
        }
        return INSTANCE;
    }

private static RoomDatabase.Callback roomDatabaseCallBack = new RoomDatabase.Callback(){

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        // If you want to keep data through app restarts,
        // comment out the following block
        databaseWriteExecutor.execute(() -> {
            // Populate the database in the background.
            // If you want to start with more words, just add them.
            String[] words = {"dolphin", "crocodile", "cobra"};

            WordDao dao = INSTANCE.wordDao();

            for(int i=0; i<words.length;i++){
                dao.insert(new WordObject(words[i]));
            }
        });

       // new Thread(new initializeDataRunnable(INSTANCE)).start();
    }
};

    private static class initializeDataRunnable implements Runnable{

        private final WordDao wordDao;
        private String[] words = {"dolphin", "crocodile", "cobra"};

        private initializeDataRunnable(WordDataBase db) {
            this.wordDao = db.wordDao();
        }

        @Override
        public void run() {

            for(int i=0; i<words.length;i++){
                wordDao.insert(new WordObject(words[i]));
            }
        }
    }

}
