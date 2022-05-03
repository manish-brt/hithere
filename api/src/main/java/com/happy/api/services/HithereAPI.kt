package com.happy.api.services

import com.happy.api.models.entities.User
import com.happy.api.models.entities.UserCreds
import com.happy.api.models.requests.LoginRequest
import com.happy.api.models.requests.SignupRequest
import com.happy.api.models.responses.ArticleResponse
import com.happy.api.models.responses.ArticlesResponse
import com.happy.api.models.responses.TagsResponse
import com.happy.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface HithereAPI {

    @POST("users")
    suspend fun signUpUser(
        @Body userCreds: SignupRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCreds: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favourited") favourited: String? = null,
        @Query("tag") tag: String? = null,

        ): Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticlesBySlug(
        @Path("slug") slug: String? = null,
        ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>
}