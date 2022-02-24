package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import andrefigas.com.github.sports.model.services.EndPoints
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class EventsListModelImpl(
    val endPoints: EndPoints,
    val eventsDAOContract: EventsDAOContract
) : EventsListModelContract {

    override fun provideCategories(): Single<List<Category>> = endPoints.provideCategories()
        .flatMap { categories ->
            eventsDAOContract.provideCategories(categories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    override fun toggleEventFavourite(event: Event): Single<Event> {
        return eventsDAOContract.toggleEventFavourite(event)
    }

}