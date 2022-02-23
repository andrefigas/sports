package andrefigas.com.github.sports.presenter.di

import andrefigas.com.github.sports.singleton.App
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.EventListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class EventsListModule {

    @Provides
    fun providePresenter(app: App): EventListPresenterContract = EventListPresenterImpl(app)

}