package com.happy.hithere.data

import com.happy.api.HithereClient

object ArticlesRepo {

    val api = HithereClient.publicApi
    val authApi = HithereClient.authApi

    suspend fun getGlobalFeed() = api.getArticles().body()?.articles

    suspend fun getMyFeed() = authApi.getFeedArticles().body()?.articles
}