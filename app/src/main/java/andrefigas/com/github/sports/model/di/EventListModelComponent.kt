package andrefigas.com.github.sports.model.di

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.services.EndPoints
import andrefigas.com.github.sports.presenter.EventListPresenterImpl
import dagger.BindsInstance
import dagger.Component

@Component(modules = [EventsListModelModule::class])
interface EventListModelComponent {

    fun inject(presenter: EventListPresenterImpl)

    @Component.Builder
    interface Builder {

        fun build(): EventListModelComponent
        @BindsInstance
        fun endpoints(endpoints: EndPoints): Builder
        @BindsInstance
        fun eventsDAO(eventsDAOContract: EventsDAOContract): Builder
    }

}