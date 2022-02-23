package andrefigas.com.github.sports.model.dao

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import io.reactivex.rxjava3.core.Single

interface EventsDAOContract {
    fun toggleEventFavourite(event: Event): Single<Event>
    fun provideCategories(categories: List<Category>): Single<List<Category>>
}