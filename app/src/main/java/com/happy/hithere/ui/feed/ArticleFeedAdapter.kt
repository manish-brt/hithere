package com.happy.hithere.ui.feed

import android.graphics.Color
import android.graphics.Color.GRAY
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.happy.api.models.entities.Article
import com.happy.hithere.R
import com.happy.hithere.databinding.ListItemArticleBinding
import com.happy.hithere.extensions.loadImage
import com.happy.hithere.extensions.timeStamp

class ArticleFeedAdapter(val onArticleClicked: (articleId: String) -> Unit) : ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
    object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem;
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
) {

//    interface OnArticleClickedListener{
//        fun onArticleClicked()
//    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        val binding = ListItemArticleBinding.inflate(
//            parent.context.getSystemService(LayoutInflater::class.java),
//            parent,
//            false
//        )
//        return ArticleViewHolder(binding.root)

        return ArticleViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_article,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorIV.loadImage(article.author.image, true)
            authorTV.text = article?.author?.username
            dateTV.timeStamp = article.createdAt
            titleTV.text = article?.title
            bodyTV.text = article?.body

            root.setOnClickListener{onArticleClicked(article.slug)}
        }
    }
}