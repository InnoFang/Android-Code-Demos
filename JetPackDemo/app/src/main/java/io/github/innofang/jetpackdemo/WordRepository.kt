package io.github.innofang.jetpackdemo

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {

    private lateinit var wordDao: WordDao
    private lateinit var allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) = InsertAsyncTask(wordDao).execute(word)

    private class InsertAsyncTask(val wordDao: WordDao) : AsyncTask<Word, Unit, Unit>() {
        override fun doInBackground(vararg params: Word?) {
            wordDao.insert(params[0]!!)
        }
    }
}