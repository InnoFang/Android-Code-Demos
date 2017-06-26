package io.innofang.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private val infoTextView: TextView by lazy { findViewById(R.id.text_view) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getStringExtra("value")?.let { infoTextView.text = it }
    }
}
