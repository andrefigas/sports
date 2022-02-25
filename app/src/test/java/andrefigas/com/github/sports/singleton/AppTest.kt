package andrefigas.com.github.sports.singleton


import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.singleton.di.DaggerAppTestComponent

import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
open class AppTest() {

    companion object{
        lateinit var INSTANCE : AppTest

        fun setup(){
            INSTANCE = AppTest()
        }

    }

    @Inject
    lateinit var endPoints : EndPoints

    init {
        DaggerAppTestComponent.builder().build().inject(this)
    }

}