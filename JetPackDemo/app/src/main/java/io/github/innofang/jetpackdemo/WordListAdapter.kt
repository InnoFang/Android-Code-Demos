package io.github.innofang.jetpackdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordListAdapter(val context: Context): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val wordTextView: TextView = itemView.findViewById(R.id.text_view)

        fun bindHolder(word: String) {
            wordTextView.text = word
        }
    }

    private val layoutInfalter = LayoutInflater.from(context)
    var words: List<Word> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder
        = WordViewHolder(layoutInfalter.inflate(R.layout.item_word, parent, false))

    override fun getItemCount(): Int
        = words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int)
        = holder.bindHolder(words[position].word)
}