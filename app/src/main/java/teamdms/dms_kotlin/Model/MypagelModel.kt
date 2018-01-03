package team_dms.dms.Model

import com.google.gson.annotations.*
import team_dms.dms.Base.Util

/**
 * Created by root1 on 2017. 11. 23..
 */
class MypagelModel {

    @SerializedName("extension_11_class")
    var class11Num: Int? = null

    @SerializedName("extension_12_class")
    var class12Num: Int? = null

    @SerializedName("extension_11_seat")
    var seat11Num: Int? = null

    @SerializedName("extension_12_seat")
    var seat12Num: Int? = null

    @SerializedName("goingout_sat")
    var outSatState= false

    @SerializedName("goingout_sun")
    var outSunState= false

    @SerializedName("name")
    var name = ""

    @SerializedName("number")
    var number = 10
    //지은이 학번 ? ㅗ 공과 사는 구분하자 병찬아

    @SerializedName("stay_value")
    var stayState: Int = 4

    fun getStudyState(): String{
        return "11 : " + when(class11Num){
            is Int -> "${Util.classNameArr[class11Num!! - 1]}"
            else -> "신청없음"
        } + "\n12 : " + when(class12Num){
            is Int -> "${Util.classNameArr[class12Num!! - 1]}"
            else -> "신청없음"
        }
    }

    fun getStayState(): String{
        val stayStateStrArr = arrayOf("금요귀가", "토요귀가", "토요귀사", "잔류")
        return stayStateStrArr[stayState - 1]
    }

}