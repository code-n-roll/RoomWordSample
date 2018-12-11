package com.karanchuk.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {

    var wordDao: WordDao
    var allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getInstance(application)
        wordDao = db.wordDao()
        allWords = wordDao.getAllWords()
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao).execute(word)
    }
}

class InsertAsyncTask(dao: WordDao) : AsyncTask<Word, Unit, Unit>() {

    private val asyncTaskDao: WordDao = dao

    override fun doInBackground(vararg params: Word?) {
        params[0]?.let {
            asyncTaskDao.insert(it)
        }
    }
}
