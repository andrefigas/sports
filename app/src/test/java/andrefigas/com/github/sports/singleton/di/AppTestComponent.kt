package andrefigas.com.github.sports.singleton.di

import andrefigas.com.github.sports.singleton.AppTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppTestModule::class])
interface AppTestComponent {

    fun inject(app: AppTest)

    /*@Component.Builder
    interface Builder {

        fun build(): AppTestComponent
    }*/

}