package andrefigas.com.github.sports.singleton


import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.singleton.di.DaggerAppTestComponent
import androidx.annotation.NonNull
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AppTest{

    companion object{
        lateinit var INSTANCE : AppTest

        fun setup(){
            INSTANCE = AppTest()
            setUpRxSchedulers()
        }

        private fun setUpRxSchedulers() {

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
                        { obj: Runnable -> obj.run() },
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

    }

    @Inject
    lateinit var endPoints : EndPoints

    init {
        DaggerAppTestComponent.builder().build().inject(this)
    }

}