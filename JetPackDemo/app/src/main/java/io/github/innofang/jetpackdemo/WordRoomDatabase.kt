package io.github.innofang.jetpackdemo

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase
import android.os.AsyncTask
import android.support.annotation.NonNull



@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            WordRoomDatabase::class.java,
                            "word_database")
                            .addCallback(rootDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        private val rootDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { PopulateDbAsync(it).execute() }
            }
        }
    }

    private class PopulateDbAsync(db: WordRoomDatabase): AsyncTask<Unit, Unit, Unit>() {
        val wordDao = db.wordDao()

        override fun doInBackground(vararg params: Unit?) {
            wordDao.deleteAll()
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World")
            wordDao.insert(word)
        }
    }
}
