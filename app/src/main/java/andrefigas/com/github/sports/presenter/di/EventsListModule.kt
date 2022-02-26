package andrefigas.com.github.sports.presenter.di

import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.EventListPresenterImpl
import andrefigas.com.github.sports.singleton.AppContract
import dagger.Module
import dagger.Provides

@Module
class EventsListModule {

    @Provides
    fun providePresenter(app: AppContract): EventListPresenterContract = EventListPresenterImpl(app)

}