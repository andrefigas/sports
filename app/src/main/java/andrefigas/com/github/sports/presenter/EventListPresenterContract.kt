package andrefigas.com.github.sports.presenter

import andrefigas.com.github.sports.model.entities.Category
import io.reactivex.rxjava3.core.Single

interface EventListPresenterContract {
    fun provideEvents(): Single<List<Category>>
}