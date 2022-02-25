package andrefigas.com.github.sports.view

import andrefigas.com.github.sports.R
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

object CategoryDesignUtils {

    private val FOOT_ID = "FOOT"
    private val BASK_ID = "BASK"
    private val TENN_ID = "TENN"
    private val TABL_ID = "TABL"
    private val VOLL_ID = "VOLL"
    private val ESPS_ID = "ESPS"
    private val ICEH_ID = "ICEH"
    private val BCHV_ID = "BCHV"
    private val BADM_ID = "BADM"

    @ColorRes
    fun getColorByCategoryId(categoryId : String) : Int{
        return when(categoryId){
                    FOOT_ID -> R.color.foot
                    BASK_ID -> R.color.bask
                    TENN_ID -> R.color.tenn
                    TABL_ID -> R.color.tabl
                    VOLL_ID -> R.color.voll
                    ESPS_ID -> R.color.esps
                    ICEH_ID -> R.color.iceh
                    BCHV_ID -> R.color.bchv
                    BADM_ID -> R.color.badm
                    else -> R.color.other
        }
    }

    @ColorRes
    fun getDarkColorByCategoryId(categoryId : String) : Int{
        return when(categoryId){
            FOOT_ID -> R.color.foot_dark
            BASK_ID -> R.color.bask_dark
            TENN_ID -> R.color.tenn_dark
            TABL_ID -> R.color.tabl_dark
            VOLL_ID -> R.color.voll_dark
            ESPS_ID -> R.color.esps_dark
            ICEH_ID -> R.color.iceh_dark
            BCHV_ID -> R.color.bchv_dark
            BADM_ID -> R.color.badm_dark
            else -> R.color.other_dark
        }
    }

    @DrawableRes
    fun getIconByCategoryId(categoryId : String) : Int{
        return when(categoryId){
            FOOT_ID -> R.drawable.ic_foot
            BASK_ID -> R.drawable.ic_bask
            TENN_ID -> R.drawable.ic_tenn
            TABL_ID -> R.drawable.ic_tabl
            VOLL_ID -> R.drawable.ic_voll
            ESPS_ID -> R.drawable.ic_esps
            ICEH_ID -> R.drawable.ic_iceh
            BCHV_ID -> R.drawable.ic_bchv
            BADM_ID -> R.drawable.ic_badm
            else -> R.drawable.ic_other
        }
    }
}