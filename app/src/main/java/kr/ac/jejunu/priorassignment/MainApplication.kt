package kr.ac.jejunu.priorassignment

import android.app.Application
import kr.ac.jejunu.priorassignment.di.mainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(mainModules)
        }
    }
}