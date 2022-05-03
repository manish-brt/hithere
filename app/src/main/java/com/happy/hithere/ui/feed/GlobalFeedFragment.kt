package com.happy.hithere.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.happy.hithere.R
import com.happy.hithere.databinding.FragmentFeedBinding

class GlobalFeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private lateinit var _viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter { openArticle(it) }

        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        _binding?.feedRv?.layoutManager = LinearLayoutManager(context)
        _binding?.feedRv?.adapter = feedAdapter

        return _binding?.root
//        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.fetchGlobalFeed()
        _viewModel.feed.observe({ lifecycle }) {
            feedAdapter.submitList(it)
        }
    }

    fun openArticle(articleId: String) {
        findNavController().navigate(
            R.id.action_globalFeed_openArticle, bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}