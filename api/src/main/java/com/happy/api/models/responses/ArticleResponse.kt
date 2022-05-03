package com.happy.api.models.responses


import com.happy.api.models.entities.Article
import com.happy.api.models.entities.Profile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "article")
    val articles: Article,
)