package andrefigas.com.github.sports

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.singleton.AppTest
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.di.DaggerEventListTestPresenterComponent
import androidx.annotation.NonNull
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SportsUnitTest{

    companion object{
        const val SOCCER_DESCRIPTION = "SOCCER"
    }

    fun setUpRxSchedulers() {

        val immediate: Scheduler = object : Scheduler() {
            override fun scheduleDirect(
                @NonNull run: Runnable,
                delay: Long,
                @NonNull unit: TimeUnit
            ): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(
                    Executor { obj: Runnable -> obj.run() },
                    true, false
                )
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }


    @Inject
    lateinit var presenter : EventListPresenterContract

    @Before
    fun setup() {
        AppTest.setup()
        DaggerEventListTestPresenterComponent.builder().base(AppTest.INSTANCE).build().inject(this)
        setUpRxSchedulers()
    }

    @Test
    fun validateList() {

        val testObserver = TestObserver<List<Category>>()
        presenter.provideEvents().subscribe(testObserver)

        testObserver.await().assertValue{ categories ->
            categories[0].description == SOCCER_DESCRIPTION
        }
    }
}