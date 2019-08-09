package com.ldhcjs.kotlinokhttpretrofitrxjava

import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.concurrent.TimeUnit

object KotlinOKHttpRetrofitRxJavaManager {
    val CONNECT_TIMEOUT: Long = 15
    val WRITE_TIMEOUT: Long = 15
    val READ_TIMEOUT: Long = 15
    val API_URL: String = "https://api.github.com/"
    var mOKHttpClient: OkHttpClient
    var mRetrofit: Retrofit
    var mKotlinRetrofitInterface: KotlinRetrofitInterface

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        mOKHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        }.build()

        mRetrofit = Retrofit.Builder().apply {
            baseUrl(API_URL)
            client(mOKHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        mKotlinRetrofitInterface = mRetrofit.create()
    }

    fun getInstance(): KotlinRetrofitInterface {
        return mKotlinRetrofitInterface
    }
}