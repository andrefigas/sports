package andrefigas.com.github.sports.model.dao

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.model.entities.Event
import android.content.Context
import io.reactivex.rxjava3.core.Single


/**
 * @param context was included because usually data persistence libraries requires context
 */
class EventsDAOImpl(private val context: Context) : EventsDAOContract {

    private val favorites: MutableList<Int> = mutableListOf()

    /**
     * @param categories return by services
     * @return categories updated by database information (favorite status)
     */
    override fun provideCategories(categories: List<Category>): Single<List<Category>> {
        val iterable: List<Single<Category>> = categories.map { category ->
            provideEventsByCategory(category)
        }

        return Single.zip(iterable) { dbObjects ->
            dbObjects.map { categoryDb ->
                categoryDb as Category
            }
        }
    }

    override fun toggleEventFavourite(event: Event): Single<Event> {
        event.favorites = !event.favorites
        if (event.favorites) {
            favorites.remove(event.id)
        } else {
            favorites.add(event.id)
        }
        return Single.just(event)
    }

    private fun provideEventsByCategory(category: Category): Single<Category> {
        val iterable: List<Single<Event>> = category.events.map { event ->
            provideEvent(event)
        }

        return Single.zip(iterable) { dbObjects ->
            Category(
                category.id,
                category.description,
                dbObjects.map { dbObject -> dbObject as Event }
            )
        }

    }

    private fun provideEvent(event: Event): Single<Event> {
        event.favorites = favorites.contains(event.id)
        return Single.just(event)
    }

}