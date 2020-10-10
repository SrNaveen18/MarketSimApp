package com.example.marketsimapp.di

import com.example.marketsimapp.ui.publicrepo.PublicRepoRepository
import com.example.marketsimapp.ui.publicrepo.PublicRepoViewModel
import com.example.marketsimapp.ui.publirepodetails.PublicRepoDetailsViewModel
import com.example.marketsimapp.webservices.ApiStories
import com.example.marketsimapp.webservices.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { PublicRepoViewModel(get()) }
    viewModel { PublicRepoDetailsViewModel() }
}

val repositoryModule = module {
    factory { PublicRepoRepository(get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient().newBuilder().addNetworkInterceptor(interceptor).build()
}

fun provideApi(retrofit: Retrofit): ApiStories = retrofit.create(ApiStories::class.java)


