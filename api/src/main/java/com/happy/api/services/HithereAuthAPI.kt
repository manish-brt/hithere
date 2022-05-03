package com.happy.api.services

import com.happy.api.models.requests.UserUpdateRequest
import com.happy.api.models.responses.ArticleResponse
import com.happy.api.models.responses.ArticlesResponse
import com.happy.api.models.responses.ProfileResponse
import com.happy.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface HithereAuthAPI {
    @GET("users")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("users/login")
    suspend fun updateCurrentUser(
        @Body userUpdateRequest: UserUpdateRequest
    ): Response<UserResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @DELETE("profiles/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

//    @POST("articles")
//    suspend fun createArticle(
//        @Body article: UpsertArticleRequest
//    ) :Response<ArticleResponse>
}