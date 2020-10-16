package kr.ac.jejunu.priorassignment.di

import kr.ac.jejunu.priorassignment.data.ImageRepositoryImpl
import kr.ac.jejunu.priorassignment.data.ImageService
import kr.ac.jejunu.priorassignment.di.Interceptor.SearchClient
import kr.ac.jejunu.priorassignment.domain.ImageRepository
import kr.ac.jejunu.priorassignment.ui.main.adapter.MainRecyclerViewAdapter
import kr.ac.jejunu.priorassignment.ui.main.viewmodel.MainImageSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val KAKAO_IMAGE_SEARCH_BASE_URL = "https://dapi.kakao.com/"

var dataModule = module {
    single<ImageRepository> { ImageRepositoryImpl(get()) }
    single<ImageService> {
        Retrofit.Builder()
            .baseUrl(KAKAO_IMAGE_SEARCH_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(SearchClient.searchHttpClient())
            .build()
            .create(ImageService::class.java)
    }
}

var viewModelModule = module {
    viewModel { MainImageSearchViewModel(get()) }
}

var adapterModule = module {
    factory { MainRecyclerViewAdapter() }
}

var mainModules = listOf(viewModelModule, dataModule, adapterModule)