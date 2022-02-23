package andrefigas.com.github.sports.model.entities

import com.google.gson.annotations.SerializedName

class Event(
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    private val teamsStr: String,
    @SerializedName("tt")
    private val time: Long){
    companion object{
        const val SEPARATOR = "-"
    }

    val teams : List<String>
    get() = teamsStr.split(SEPARATOR).map { it.trim() }
}