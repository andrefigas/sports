package andrefigas.com.github.sports.singleton.di

import andrefigas.com.github.sports.singleton.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

}