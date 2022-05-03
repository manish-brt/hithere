package com.happy.hithere.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.happy.hithere.R
import com.happy.hithere.databinding.FragmentArticleBinding
import com.happy.hithere.extensions.loadImage
import com.happy.hithere.extensions.showFormattedDate
import com.happy.hithere.extensions.timeStamp

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    lateinit var articlesViewModel: ArticleViewModel
    private var articleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articlesViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)

        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
            Toast.makeText(requireContext(), "Article Id is $articleId", Toast.LENGTH_SHORT).show()
        }
        articleId?.let{
            articlesViewModel.fetchArticle(it)
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesViewModel.article.observe({lifecycle}) {
            _binding?.apply {
                titleTV.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it?.author?.username ?: ""
                dateTextView.timeStamp = it.createdAt
                avatarImageView.loadImage(it.author.image)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}