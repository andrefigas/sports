package andrefigas.com.github.sports.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Event(
    @Expose
    @SerializedName("i")
    val id: Int,
    @Expose
    @SerializedName("d")
    private val teamsStr: String,
    @Expose
    @SerializedName("tt")
    val time: Long,
    @Expose
    @SerializedName("si")
    val categoryID: String,
    var favorites : Boolean = false){
    companion object{
        const val SEPARATOR = " - "
    }

    val teams : List<String>
    get() = teamsStr.split(SEPARATOR).map { it.trim() }
}