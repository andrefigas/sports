package andrefigas.com.github.sports

import andrefigas.com.github.sports.utils.RecyclerViewMatcher.Companion.withRecyclerView
import andrefigas.com.github.sports.view.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @JvmField
    @Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    private fun waitForContent(){
        while (mActivityRule.activity.findViewById<RecyclerView>(R.id.rv_main_content).adapter == null) {
            Thread.sleep(1000)
        }
    }

    @Test
    fun validateCategory() {
        waitForContent()

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(0, R.id.tv_event_category_name)
        ).check(matches(withText("SOCCER")))

    }

    @Test
    fun toggleCategoryCollapse() {
        waitForContent()

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(0, R.id.sw_event_category_toggle)
        ).check(matches(isNotChecked()))

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(0, R.id.sw_event_category_toggle)
        ).perform(click())

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(0, R.id.sw_event_category_toggle)
        ).check(matches(isChecked()))

    }

    @Test
    fun validateEvent() {
        waitForContent()

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(1, R.id.tv_event_item_counter)
        ).check(matches(withText("8 months")))

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(1, R.id.tv_event_item_teams)
        ).check(matches(withText("Medeama SC\nVS\nDreams FC")))
    }

    @Test
    fun toggleFavorite() {
        waitForContent()

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(1, R.id.sw_event_item_favorite)
        ).check(matches(isNotChecked()))

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(1, R.id.sw_event_item_favorite)
        ).perform(click())

        onView(
            withRecyclerView(R.id.rv_main_content)
                .atPositionOnView(1, R.id.sw_event_item_favorite)
        ).check(matches(isChecked()))

    }


}