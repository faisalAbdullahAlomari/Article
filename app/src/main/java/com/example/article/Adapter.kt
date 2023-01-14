package com.example.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val Manager: FragmentManager)
    : RecyclerView.Adapter<Adapter.DataHolder>() {

    private val articlesList = mutableListOf<Article>()

    fun setItems(articleList: List<Article>) {
        this.articlesList.clear()
        this.articlesList.addAll(articleList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val Holder = DataHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.custom_layout,parent,false))
        return Holder
    }

    override fun onBindViewHolder(holder: DataHolder,position: Int) {
        val article = articlesList[position]
        holder.articleTitle.text = article.title

        holder.itemView.setOnClickListener {
            Manager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ArticleFragment::class.java,
                    bundleOf(
                        "title" to article.title,
                        "article" to article.article,
                    )
                )
                .addToBackStack("ArticleFragment")
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    class DataHolder(item: View): RecyclerView.ViewHolder(item){
        val articleTitle: TextView = item.findViewById(R.id.tvArticleTitle)

    }
}