package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import io.reactivex.rxjava3.core.Single

interface EventsListModelContract {
    fun provideCategories(): Single<List<Category>>
    fun toggleEventFavourite(event: Event): Single<Event>
}