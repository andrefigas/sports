package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.dao.EventsDAOContract
import andrefigas.com.github.sports.model.dao.EventsDAOImpl
import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.services.EndPoints
import io.reactivex.rxjava3.core.Single

class EventsListModelImpl(val endPoints: EndPoints, val eventsDAOContract: EventsDAOContract) : EventsListModelContract {

    override fun provideCategories(): Single<List<Category>> = endPoints.provideCategories().flatMap { categories->
        eventsDAOContract.provideCategories(categories)
    }

}