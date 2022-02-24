package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.model.entities.Event
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.event_item.view.*

class EventViewHolder(itemView: View) : BaseHolder<Event>(itemView) {

    override fun bind(event: Event){
        itemView.tv_event_item_teams.text = event.teams.joinToString(separator = "\n") { it }
        itemView.sw_event_item_favorite.isChecked = event.favorites
    }

}