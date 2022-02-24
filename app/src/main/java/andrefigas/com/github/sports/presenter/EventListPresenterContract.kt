package andrefigas.com.github.sports.presenter

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import io.reactivex.rxjava3.core.Single

interface EventListPresenterContract {
    fun provideEvents(): Single<List<Category>>

    fun toggleEventFavourite(event: Event): Single<Event>
}