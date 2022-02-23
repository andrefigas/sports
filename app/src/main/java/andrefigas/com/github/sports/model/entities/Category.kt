package andrefigas.com.github.sports.model.entities

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("i")
    val id : String,
    @SerializedName("d")
    val description : String,
    @SerializedName("e")
    val events : List<Event>
)
