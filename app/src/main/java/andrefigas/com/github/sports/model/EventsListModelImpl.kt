package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.entities.Event
import andrefigas.com.github.sports.model.services.EndPoints
import io.reactivex.rxjava3.core.Single

class EventsListModelImpl(val endPoints: EndPoints) : EventsListModelContract{

    override fun provideCategories() = endPoints.provideCategories()

}