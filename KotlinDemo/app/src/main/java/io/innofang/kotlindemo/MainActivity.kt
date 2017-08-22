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

    private val textView by lazy {
        find<TextView>(R.id.text_view).apply {
            text = INITIALIZED
        }
    }

    private val clickButton by lazy { find<Button>(R.id.button) }
    private val intentButton by lazy { find<Button>(R.id.intent_button) }
    private val rvButton by lazy { find<Button>(R.id.rv_button) }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.button -> normalClickListener()
            R.id.intent_button -> intentClickListener()
            R.id.rv_button -> rvClickListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()
    }

    private fun initEvent() {

        with(clickButton) {
            text = "Click Me"
            setOnClickListener(onClickListener)
        }

        intentButton.setOnClickListener(onClickListener)

        rvButton.setOnClickListener(onClickListener)
    }

    fun normalClickListener() {
        if (textView.text == INITIALIZED)
            textView.text = "0"
        else
            textView.text = (Integer.valueOf(textView.text.toString()) + 1).toString()
    }

    fun intentClickListener() {
        val intent = Intent(MainActivity@ this, DetailActivity::class.java).apply {
            putExtra("value", textView.text.toString())
        }
        startActivity(intent)
    }

    fun rvClickListener() {
        if (textView.text.toString() != INITIALIZED) {
            with(Intent(MainActivity@ this, RvActivity::class.java)){
                putExtra("value", textView.text.toString())
            }.let {
                startActivity(it)
            }
        } else {
            toast("Do not have the number")
        }
    }

    inline fun <reified T : View> Activity.find(@IdRes id: Int): T = findViewById(id) as T


    fun Context.toast(text: String = "", time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, time).show()
    }

}
