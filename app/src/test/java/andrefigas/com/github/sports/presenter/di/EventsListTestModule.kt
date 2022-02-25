package andrefigas.com.github.sports.presenter.di

import andrefigas.com.github.sports.singleton.AppTest
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.EventListTestPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class EventsListTestModule {

    @Provides
    fun providePresenter(appTest: AppTest): EventListPresenterContract = EventListTestPresenterImpl(appTest)

}