package com.happy.hithere.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happy.api.HithereClient
import com.happy.api.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val api = HithereClient.publicApi

    private val _article = MutableLiveData<Article>()
    var article: LiveData<Article> = _article

    fun fetchArticle(slug: String) = viewModelScope.launch {
        val res = api.getArticlesBySlug(slug)
        res.body()?.articles.let { _article.postValue(it) }
    }
}