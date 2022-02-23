package andrefigas.com.github.sports.model.di

import andrefigas.com.github.sports.model.EventsListModelContract
import andrefigas.com.github.sports.model.EventsListModelImpl
import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.services.EndPoints
import dagger.Module
import dagger.Provides

@Module
class EventsListModelModule {

    @Provides
    fun provideModel(endPoints: EndPoints, eventsDAOContract: EventsDAOContract): EventsListModelContract = EventsListModelImpl(endPoints, eventsDAOContract)

}