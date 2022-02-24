package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.di.DaggerEventListPresenterComponent
import andrefigas.com.github.sports.singleton.App
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: EventListPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if(rv_main_content.adapter?.getItemViewType(position) == EventsAdapter.TYPE_CATEGORY){
                    2
                }else 1
            }
        }

        rv_main_content.layoutManager = layoutManager

        DaggerEventListPresenterComponent.builder().application(application as App).build()
            .inject(this)
        presenter.provideEvents().doOnSuccess { categories ->
            rv_main_content.adapter = EventsAdapter(presenter, categories)
        }.doOnError { error ->
            error.printStackTrace()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

}