package andrefigas.com.github.sports.presenter.di

import andrefigas.com.github.sports.singleton.AppContract
import andrefigas.com.github.sports.view.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [EventsListModule::class])
interface EventListPresenterComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): EventListPresenterComponent

        @BindsInstance
        fun application(app: AppContract): Builder
    }

}