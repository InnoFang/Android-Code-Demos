package io.github.innofang.jetpackdemo

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val newWordFAB = findViewById<FloatingActionButton>(R.id.new_word_fab)
        newWordFAB.setOnClickListener {
            startActivityForResult(
                Intent(this, NewWordActivity::class.java), NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this,
            Observer<List<Word>> { words -> words?.let { adapter.words = it } })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}
