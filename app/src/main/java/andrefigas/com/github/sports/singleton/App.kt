package andrefigas.com.github.sports.singleton

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.singleton.di.DaggerAppComponent
import android.app.Application
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var endPoints: EndPoints

    @Inject
    lateinit var eventsDAOContract: EventsDAOContract

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().context(this).build().inject(this)
    }
}