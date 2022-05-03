package com.happy.api

import com.happy.api.models.entities.UserCreds
import com.happy.api.models.requests.SignupRequest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

class HithereClientTests {
    
    @Test
    fun `GET articles`(){

        runBlocking {
            val articles = HithereClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`(){

        runBlocking {
            val articles = HithereClient.publicApi.getArticles(author = "Gerome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tags`(){

        runBlocking {
            val articles = HithereClient.publicApi.getArticles(tag = "welcome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST users - create user`() {

        val userCreds = UserCreds(
            email = "testemail${Random.nextInt(99,999)}@test.com",
            password = "1234567",
            username = "test_user_${Random.nextInt(99,999)}"
        )

        runBlocking {
            val res = HithereClient.publicApi.signUpUser(SignupRequest(userCreds))
            assertEquals(userCreds.username, res.body()?.user?.username)
        }
    }
}