package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.di.DaggerEventListPresenterComponent
import andrefigas.com.github.sports.singleton.App
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivity() : AppCompatActivity() {

    @Inject
    lateinit var presenter: EventListPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerEventListPresenterComponent.builder().application(application as App).build()
            .inject(this)
        presenter.provideEvents().doOnSuccess { categories ->
            println(categories)
        }.doOnError { error ->
            error.printStackTrace()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

}