package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordDao;
import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordDataBase;
import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordObject;

@Database(entities = {PagingWordObject.class}, version = 1, exportSchema = false)
public abstract class PagingWordDatabase extends RoomDatabase {
    public abstract PagingWordDao pagingWordDao();

    public static PagingWordDatabase INSTANCE;
    public static synchronized PagingWordDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PagingWordDatabase.class, "paging_word_database")
                    // Wipes and rebuilds instead of migrating
                    // if no Migration object.
                    // Migration is not part of this practical.
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabaseCallBack)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallBack = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new Thread(new PagingWordDatabase.initializeDataRunnable(INSTANCE)).start();
        }
    };

    private static class initializeDataRunnable implements Runnable{

        private final PagingWordDao wordDao;
        private String[] words = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
                "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
                "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
                "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
                "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
                "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
                "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
                "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
                "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
                "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
                "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
                "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
                "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
                "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
                "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
                "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
                "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
                "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
                "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
                "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
                "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
                "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
                "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
                "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
                "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
                "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
                "Celtic Promise"};

        private initializeDataRunnable(PagingWordDatabase db) {
            this.wordDao = db.pagingWordDao();
        }

        @Override
        public void run() {

            for(int i=0; i<words.length;i++){
                wordDao.insert(new PagingWordObject(words[i]));
            }
        }
    }
}
