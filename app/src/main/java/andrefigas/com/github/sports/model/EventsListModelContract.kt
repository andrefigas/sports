package andrefigas.com.github.sports.model

import andrefigas.com.github.sports.model.entities.Category
import io.reactivex.rxjava3.core.Single

interface EventsListModelContract {
    fun provideCategories(): Single<List<Category>>
}