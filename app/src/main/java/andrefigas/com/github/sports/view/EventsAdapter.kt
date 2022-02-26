package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable

class EventsAdapter(
    val presenter: EventListPresenterContract,
    private val categories: List<Category>
) : RecyclerView.Adapter<BaseHolder<*>>(), EventAdapterListener {

    companion object {
        const val TYPE_CATEGORY = 1
        const val TYPE_EVENT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CATEGORY -> {
                CategoryViewHolder(this, inflater.inflate(R.layout.event_category, parent, false))
            }
            TYPE_EVENT -> {
                EventViewHolder(this, inflater.inflate(R.layout.event_item, parent, false))
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<*>, position: Int) {
        val data = getDataToBind(position)
        when (holder) {
            is CategoryViewHolder -> {
                holder.bind(data as Category)
            }
            is EventViewHolder -> {
                holder.bind(data as Event)
            }
        }
    }

    override fun onEventToggled(event: Event): @NonNull Disposable {
        return presenter.toggleEventFavourite(event).doOnSuccess { toggledEvent ->
            categories.first { category ->
                category.id == event.categoryID
            }.events.first { evt ->
                evt.id == event.id
            }.favorites = toggledEvent.favorites
        }.subscribe()
    }

    override fun onViewAttachedToWindow(holder: BaseHolder<*>) {
        super.onViewAttachedToWindow(holder)
        when (holder) {
            is EventViewHolder -> {
                holder.turnOnCountDown()
                holder.release()
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        when (holder) {
            is EventViewHolder -> {
                holder.turnOffCountDown()
            }
        }
    }

    override fun onCategoryToggled(category: Category) {
        categories.first { it.id == category.id }.collapsed = category.collapsed
        if (category.collapsed) {
            notifyItemRangeRemoved(getCategoryPosition(category) + 1, category.events.size)
        } else {
            notifyItemRangeInserted(getCategoryPosition(category) + 1, category.events.size)
        }
    }

    override fun getItemCount(): Int {
        return categories.filter { category -> !category.collapsed }
            .flatMap { category -> category.events }.size + categories.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (getDataToBind(position)) {
            is Category -> {
                TYPE_CATEGORY
            }
            is Event -> {
                TYPE_EVENT
            }
            else -> throw IllegalArgumentException()
        }
    }

    @Nullable
    private fun getDataToBind(position: Int): Any? {
        var i = 0
        categories.forEach { category ->
            if (i == position) return category
            i++
            if (!category.collapsed) {
                category.events.forEach { event ->
                    if (i == position) return event
                    i++
                }
            }
        }

        return null
    }

    private fun getCategoryPosition(category: Category): Int {
        var i = 0
        categories.forEach { cat ->
            if (category.id == cat.id) return i
            i++
            if (!cat.collapsed) {
                repeat(cat.events.size) {
                    i++
                }
            }
        }

        return -1
    }

}