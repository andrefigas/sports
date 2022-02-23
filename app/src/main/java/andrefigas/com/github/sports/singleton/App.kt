package andrefigas.com.github.sports.singleton

import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.singleton.di.DaggerAppComponent
import android.app.Application
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var endPoints: EndPoints

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().build().inject(this)
    }
}