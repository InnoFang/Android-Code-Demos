package io.github.innofang.jetpackdemo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class WordViewModel(application: Application): AndroidViewModel(application) {

    private var repository: WordRepository = WordRepository(application)
    var allWords: LiveData<List<Word>>

    init {
        allWords = repository.allWords
    }

    fun insert(word: Word) = repository.insert(word)

}