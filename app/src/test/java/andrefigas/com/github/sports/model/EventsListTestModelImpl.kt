package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import andrefigas.com.github.sports.model.services.EndPoints
import io.reactivex.rxjava3.core.Single

class EventsListTestModelImpl(
    private val endPoints: EndPoints
) : EventsListModelContract {

    override fun provideCategories(): Single<List<Category>> = endPoints.provideCategories()

    override fun toggleEventFavourite(event: Event): Single<Event> {
        return Single.error(Throwable("Not implemented yet"))
    }

}