package andrefigas.com.github.sports.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @Expose
    @SerializedName("i")
    val id : String,
    @Expose
    @SerializedName("d")
    val description : String,
    @Expose
    @SerializedName("e")
    val events : List<Event>
)
