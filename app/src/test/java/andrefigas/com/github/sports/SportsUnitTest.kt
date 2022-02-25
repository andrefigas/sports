package andrefigas.com.github.sports

import andrefigas.com.github.sports.model.entities.Category
import andrefigas.com.github.sports.presenter.EventListPresenterContract
import andrefigas.com.github.sports.presenter.di.DaggerEventListTestPresenterComponent
import andrefigas.com.github.sports.singleton.AppTest
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class SportsUnitTest {

    companion object {
        const val SOCCER_DESCRIPTION = "SOCCER"
        const val SOCCER_ID = "FOOT"

        const val SOCCER_FIRST_EVENT_ID = 24456069
        const val SOCCER_FIRST_EVENT_TEAM_1 = "Medeama SC"
        const val SOCCER_FIRST_EVENT_TEAM_2 = "Dreams FC"
        const val SOCCER_FIRST_EVENT_TIME = 1668925680L
    }


    @Inject
    lateinit var presenter: EventListPresenterContract

    @Before
    fun setup() {
        AppTest.setup()
        DaggerEventListTestPresenterComponent.builder().base(AppTest.INSTANCE).build().inject(this)

    }

    @Test
    fun validateCategory() {

        val testObserver = TestObserver<List<Category>>()
        presenter.provideEvents().subscribe(testObserver)

        testObserver.await().assertValue { categories ->
            categories[0].id == SOCCER_ID &&
                    categories[0].description == SOCCER_DESCRIPTION &&
                    categories[0].events[0].id == SOCCER_FIRST_EVENT_ID &&
                    categories[0].events[0].time == SOCCER_FIRST_EVENT_TIME &&
                    categories[0].events[0].teams[0] == SOCCER_FIRST_EVENT_TEAM_1 &&
                    categories[0].events[0].teams[1] == SOCCER_FIRST_EVENT_TEAM_2
        }
    }
}