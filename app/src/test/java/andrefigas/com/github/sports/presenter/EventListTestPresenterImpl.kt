package andrefigas.com.github.sports.presenter

import andrefigas.com.github.sports.singleton.AppTest
import andrefigas.com.github.sports.model.EventsListModelContract
import andrefigas.com.github.sports.model.di.DaggerEventListTestModelComponent
import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class EventListTestPresenterImpl(appTest: AppTest) : EventListPresenterContract {

    @Inject
    lateinit var model : EventsListModelContract

    init {
        DaggerEventListTestModelComponent.builder().endpoints(appTest.endPoints).build().inject(this)
    }

    override fun provideEvents(): Single<List<Category>> {
        return model.provideCategories()
    }

    override fun toggleEventFavourite(event: Event): Single<Event> {
        TODO("Not yet implemented")
    }


}