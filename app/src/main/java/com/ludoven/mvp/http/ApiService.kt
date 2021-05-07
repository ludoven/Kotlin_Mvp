package com.ludoven.mvp.http

import com.ludoven.mvp.base.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    /**
     * 版本更新
     */
    @GET("/wxarticle/chapters/json  ")
    fun getData(): Observable<String>

}