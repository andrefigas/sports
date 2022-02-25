package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.di.DaggerEventListPresenterComponent
import andrefigas.com.github.sports.singleton.App
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

        DaggerEventListPresenterComponent.builder().application(application as App).build().inject(this)

        prepareList()

        requestData()
    }

    private fun prepareList(){
        val columns = 2
        val layoutManager = GridLayoutManager(this, columns)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (rv_main_content.adapter?.getItemViewType(position) == EventsAdapter.TYPE_CATEGORY) {
                    columns
                } else 1
            }
        }

        rv_main_content.layoutManager = layoutManager
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.dialog_error_title)
            .setMessage(R.string.dialog_error_body)
            .setPositiveButton(
                R.string.dialog_error_try_again
            ) { _, _ -> requestData() }
            .setNegativeButton(R.string.dialog_error_exit) { _, _ -> finish() }
            .create().show()
    }

    private fun requestData() {
        presenter.provideEvents().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categories ->
                    rv_main_content.adapter = EventsAdapter(presenter, categories)
                }
            ) {
                it.printStackTrace()
                showErrorDialog()
            }
    }

}