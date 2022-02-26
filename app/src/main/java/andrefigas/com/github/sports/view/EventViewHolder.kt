package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import andrefigas.com.github.sports.model.entities.Event
import android.os.CountDownTimer
import android.view.View
import android.widget.CompoundButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.event_item.view.*

class EventViewHolder(private val listener: EventAdapterListener, itemView: View) : BaseHolder<Event>(itemView), CompoundButton.OnCheckedChangeListener {

    private lateinit var event: Event
    private lateinit var countDownTimer: CountDownTimer
    private var disposable : Disposable? = null

    companion object{
        const val second = 1000L
        const val minute = second * 60L
        const val hour = minute * 60L
        const val day = hour * 24L
        const val month = day * 30L
        const val year = month * 12L
    }

    override fun bind(event: Event){
        this.event = event
        itemView.tv_event_item_teams.text = event.teams.joinToString(separator = "\n${itemView.context.getString(R.string.teams_separator)}\n") { it }

        itemView.sw_event_item_favorite.setOnCheckedChangeListener(null)
        itemView.sw_event_item_favorite.isChecked = event.favorites
        itemView.sw_event_item_favorite.setOnCheckedChangeListener(this)
        itemView.tv_event_item_starts_in.visibility = View.VISIBLE
        (itemView as CardView).setCardBackgroundColor(ContextCompat.getColor(itemView.context, CategoryDesignUtils.getColorByCategoryId(event.categoryID)))
    }

    override fun onCheckedChanged(button: CompoundButton?, checked: Boolean) {
        if(disposable?.isDisposed == false){
            itemView.sw_event_item_favorite.setOnCheckedChangeListener(null)
            itemView.sw_event_item_favorite.isChecked = !checked
            itemView.sw_event_item_favorite.setOnCheckedChangeListener(this)
            return
            //cancel action and undo
        }

        disposable = listener.onEventToggled(event)
    }

    fun getCounterLabel(milliseconds: Long) : String{

        val context = itemView.context
        val resources = context.resources

        return when {
            milliseconds <  0 -> {
                context.getString(R.string.done)
            }
            milliseconds < second -> {
                context.getString(R.string.now)
            }
            milliseconds < minute -> {
                resources.getQuantityString(R.plurals.seconds,
                    (milliseconds / second).toInt(), milliseconds / second)
            }
            milliseconds < hour -> {
                resources.getQuantityString(R.plurals.minutes,
                    (milliseconds / minute).toInt(), milliseconds / minute)
            }
            milliseconds < day -> {
                resources.getQuantityString(R.plurals.hours,
                    (milliseconds / hour).toInt(), milliseconds / hour)
            }
            milliseconds < month -> {
                context.resources.getQuantityString(R.plurals.days,
                    (milliseconds / day).toInt(), milliseconds / day)
            }
            milliseconds < year -> {
                resources.getQuantityString(R.plurals.months,
                    (milliseconds / month).toInt(), milliseconds / month)
            }
            else -> {
                resources.getQuantityString(R.plurals.years,
                    (milliseconds / year).toInt(), milliseconds)
            }
        }
    }

    fun release(){
        disposable?.dispose()
    }

    fun turnOnCountDown(){
        countDownTimer = object : CountDownTimer(event.time * second - System.currentTimeMillis(), 1000){
            override fun onTick(miliseconts: Long) {
                itemView.tv_event_item_counter.text = getCounterLabel(miliseconts )
                itemView.tv_event_item_starts_in.visibility =  if (miliseconts < second) View.INVISIBLE else View.VISIBLE
            }

            override fun onFinish() {
                itemView.tv_event_item_counter.text =  itemView.context.getString(R.string.done)
                itemView.tv_event_item_starts_in.visibility =  View.INVISIBLE
            }

        }
        countDownTimer.start()
    }

    fun turnOffCountDown(){
        countDownTimer .cancel()
    }



}