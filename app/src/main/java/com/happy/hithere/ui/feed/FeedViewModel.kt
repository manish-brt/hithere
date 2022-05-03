package com.happy.hithere.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happy.api.models.entities.Article
import com.happy.hithere.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()
    val feed: LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed().let {
            _feed.postValue(it)
            Log.d("FEED", "feed fetched ${it}")
        }
    }

    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed().let {
            _feed.postValue(it)
            Log.d("My FEED", "feed fetched ${it}")
        }
    }
}