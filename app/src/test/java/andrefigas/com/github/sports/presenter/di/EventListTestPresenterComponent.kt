package andrefigas.com.github.sports.presenter.di

import andrefigas.com.github.sports.SportsUnitTest
import andrefigas.com.github.sports.singleton.AppTest
import dagger.BindsInstance
import dagger.Component

@Component(modules = [EventsListTestModule::class])
interface EventListTestPresenterComponent {

    fun inject(tests: SportsUnitTest)

    @Component.Builder
    interface Builder {

        fun build(): EventListTestPresenterComponent

        @BindsInstance
        fun base(base: AppTest): Builder
    }

}