package io.innofang.kotlindemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val INITIALIZED = "initialized"

    private val textView by lazy { find<TextView>(R.id.text_view) }
    private val clickButton by lazy { find<Button>(R.id.button) }
    private val intentButton by lazy { find<Button>(R.id.intent_button) }
    private val rvButton by lazy { find<Button>(R.id.rv_button) }

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
            val intent = Intent(MainActivity@ this, DetailActivity::class.java)
            intent.putExtra("value", textView.text.toString())
            startActivity(intent)
        }

        rvButton.setOnClickListener {
            if (!textView.text.toString().equals(INITIALIZED)) {
                val intent = Intent(MainActivity@ this, RvActivity::class.java)
                intent.putExtra("value", textView.text.toString())
                startActivity(intent)
            } else {
                toast("Do not have the number")
            }

        }
    }

    inline fun <reified T : View> Activity.find(@IdRes id: Int): T = findViewById(id) as T


    fun Context.toast(text: String = "", time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, time).show()
    }

}
