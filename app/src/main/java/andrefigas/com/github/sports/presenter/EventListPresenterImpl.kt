package andrefigas.com.github.sports.presenter

import andrefigas.com.github.sports.model.EventsListModelContract
import andrefigas.com.github.sports.model.di.DaggerEventListModelComponent
import andrefigas.com.github.sports.singleton.App
import javax.inject.Inject

class EventListPresenterImpl(app: App) : EventListPresenterContract {

    @Inject
    lateinit var model: EventsListModelContract

    init {
        DaggerEventListModelComponent.builder().endpoints(app.endPoints).build().inject(this)
    }

    override fun provideEvents() = model.provideCategories()

}