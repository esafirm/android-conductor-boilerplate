package nolambda.androidstarter.network

import io.reactivex.Single
import nolambda.androidstarter.data.UserAgent
import retrofit2.http.GET

interface ApiService {

    @GET("user-agent")
    fun getUserAgent(): Single<UserAgent>

}
