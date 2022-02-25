package andrefigas.com.github.sports.model.di

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.presenter.EventListPresenterImpl
import andrefigas.com.github.sports.presenter.EventListTestPresenterImpl
import dagger.BindsInstance
import dagger.Component

@Component(modules = [EventsListTestModelModule::class])
interface EventListTestModelComponent {

    fun inject(presenter: EventListTestPresenterImpl)

    @Component.Builder
    interface Builder {

        fun build(): EventListTestModelComponent
        @BindsInstance
        fun endpoints(endpoints: EndPoints): Builder
    }

}