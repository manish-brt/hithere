package com.happy.hithere.data

import com.happy.api.HithereClient
import com.happy.api.models.entities.LoginData
import com.happy.api.models.entities.User
import com.happy.api.models.entities.UserCreds
import com.happy.api.models.entities.UserUpdate
import com.happy.api.models.requests.LoginRequest
import com.happy.api.models.requests.SignupRequest
import com.happy.api.models.requests.UserUpdateRequest
import com.happy.api.models.responses.UserResponse

object UserRepo {

    val api = HithereClient.publicApi
    val authApi = HithereClient.authApi

    suspend fun login(email: String, password: String): User? {
        val res = api.loginUser(LoginRequest(LoginData(email, password)))

        HithereClient.authToken = res.body()?.user?.token

        return res.body()?.user
    }

    suspend fun signup(username: String, email: String, password: String): User? {
        val res = api.signUpUser(SignupRequest(UserCreds(email, password, username)))

        HithereClient.authToken = res.body()?.user?.token

        return res.body()?.user
    }

    suspend fun updateUser(
        bio: String?,
        username: String?,
        image: String?,
        email: String?,
        password: String?
    ): User? {
        val res = authApi.updateCurrentUser(
            UserUpdateRequest(
                UserUpdate(
                    bio,
                    email,
                    image,
                    username,
                    password
                )
            )
        )
        return res.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        HithereClient.authToken = token
        return authApi.getCurrentUser().body()?.user
    }
}