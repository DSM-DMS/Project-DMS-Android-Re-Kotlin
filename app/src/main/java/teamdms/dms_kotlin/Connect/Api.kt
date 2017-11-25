package team_dms.dms.Connect

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import team_dms.dms.Model.MealModel
import team_dms.dms.Model.MypagelModel

/**
 * Created by root1 on 2017. 11. 23..
 */
interface Api {

    //급식 데이터 파싱
    @GET("meal/{date}")
    fun loadMeal(@Path("date")data: String): Call<MealModel>

    //마이페이지 데이터, 신청화면 메인 로드
    @GET("mypage")
    fun loadMyInfo(@Header("Authorization")token: String): Call<MypagelModel>

    //연장 map
    @GET("extension/map/{time}")
    fun loadStudyMap(@Path("time")time: Int, @Query("class")classNum: Int): Call<Array<Any>>

    //연장 신청
    @POST("extension/{time}")
    @FormUrlEncoded
    fun applyStudy(@Header("Authorization")token: String, @Path("time")time: Int, @Field("class")classNum: Int, @Field("seat")seatNum: Int): Call<Void>

    //잔류 조회
    @GET("stay")
    fun loadStayState(@Header("Authorization")token: String): Call<JsonObject>

    //잔류 신청
    @POST("stay")
    @FormUrlEncoded
    fun applyStay(@Header("Authorization")token: String, @Field("value")state: Int): Call<Void>

    //외출 신청
    @POST("goingout")
    @FormUrlEncoded
    fun applyOut(@Header("Authorization")token: String, @Field("sat")satState: Boolean, @Field("sun")sunState: Boolean): Call<Void>

    //로그인
    @POST("auth/student")
    @FormUrlEncoded
    fun signIn(@Field("id")id: String, @Field("pw")pw: String): Call<JsonObject>

    //회원가입
    @POST("signup")
    @FormUrlEncoded
    fun signUp()
}