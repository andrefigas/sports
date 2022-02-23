package andrefigas.com.github.sports.singleton.di

import andrefigas.com.github.sports.singleton.App
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun context(context: Context): Builder
    }

}