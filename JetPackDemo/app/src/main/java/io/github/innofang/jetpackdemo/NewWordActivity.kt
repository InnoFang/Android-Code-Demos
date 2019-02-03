package io.github.innofang.jetpackdemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "io.github.innofang.jetpackdemo.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        val wordEditText = findViewById<EditText>(R.id.word_edit_text)
        val saveButton = findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(wordEditText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = wordEditText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
