package andrefigas.com.github.sports.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}