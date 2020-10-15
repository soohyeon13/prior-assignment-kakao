package kr.ac.jejunu.priorassignment.di.Interceptor

import kr.ac.jejunu.priorassignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class SearchClient {
    companion object {
        fun searchHttpClient(): OkHttpClient {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            val clientBuilder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(httpLoggingInterceptor)
            }
            clientBuilder.addInterceptor(SearchInterceptor)
            clientBuilder.readTimeout(120, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
            clientBuilder.connectTimeout(120, TimeUnit.SECONDS)
            return clientBuilder.build()
        }
    }
}

object SearchInterceptor : Interceptor {
    private const val APP_KEY = "KakaoAK ${BuildConfig.App_Key}"
    private const val CONTENT_TYPE = "application/json;charset=UTF-8"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", APP_KEY)
            .addHeader("Content-Type", CONTENT_TYPE)
            .build()
        return chain.proceed(newRequest)
    }

}
