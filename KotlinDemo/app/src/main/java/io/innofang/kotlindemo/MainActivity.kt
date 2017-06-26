package io.innofang.kotlindemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.innofang.weather.R

class MainActivity : AppCompatActivity() {

    private val INITIALIZED = "initialized"

    private val textView: TextView by lazy { findViewById(R.id.text_view) as TextView }
    private val clickButton: Button by lazy { findViewById(R.id.button) as Button }
    private val intentButton: Button by lazy { findViewById(R.id.intent_button) as Button }
    private val rvButton: Button by lazy { findViewById(R.id.rv_button) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()
    }

    private fun initEvent() {
        textView.text = INITIALIZED
        clickButton.text = "Click Me"
        clickButton.setOnClickListener {
            if (textView.text.equals(INITIALIZED))
                textView.text = "0"
            else
                textView.text = (Integer.valueOf(textView.text.toString()) + 1).toString()
        }

        intentButton.setOnClickListener {
            val intent = Intent(MainActivity@this, DetailActivity::class.java)
            intent.putExtra("value", textView.text.toString())
            startActivity(intent)
        }

        rvButton.setOnClickListener {
            if (!textView.text.toString().equals(INITIALIZED)) {
                val intent = Intent(MainActivity@this, RvActivity::class.java)
                intent.putExtra("value", textView.text.toString())
                startActivity(intent)
            } else {
                toast("Do not have the number")
            }

        }
    }

    fun Context.toast(text: String = "", time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, time).show()
    }
}
