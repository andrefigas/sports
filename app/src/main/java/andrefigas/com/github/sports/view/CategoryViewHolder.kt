package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.model.entities.Category
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.event_category.view.*


class CategoryViewHolder(val listener: EventAdapterListener, itemView: View) :
    BaseHolder<Category>(itemView) {

    override fun bind(category: Category) {
        itemView.tv_event_category_name.text = category.description
        itemView.sw_event_category_toggle.setOnCheckedChangeListener(null)
        itemView.sw_event_category_toggle.isChecked = category.collapsed
        itemView.sw_event_category_toggle.setOnCheckedChangeListener { compoundButton, checked ->
            category.collapsed = !category.collapsed
            listener.onCategoryToggled(category)
        }

        (itemView as CardView).setCardBackgroundColor(ContextCompat.getColor(itemView.context, CategoryDesignUtils.getDarkColorByCategoryId(category.id)))
    }

}