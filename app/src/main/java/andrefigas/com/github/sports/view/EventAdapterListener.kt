package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import io.reactivex.rxjava3.disposables.Disposable

interface EventAdapterListener {

    fun onEventToggled(event : Event) : Disposable

    fun onCategoryToggled(category: Category)

}