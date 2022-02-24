package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event

interface EventAdapterListener {

    fun onEventToggled(event : Event)

    fun onCategoryToggled(category: Category)

}