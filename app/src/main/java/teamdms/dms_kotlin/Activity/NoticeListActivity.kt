package teamdms.dms_kotlin.Activity

import android.os.*
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_dormitory_faq.*
import kotlinx.android.synthetic.main.activity_notice_list.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.DormitoryNoticeAdapter

/**
 * Created by root1 on 2017. 12. 5..
 */
class NoticeListActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)
    }


    private fun back(){
        ib_noticeList_back.setOnClickListener { finish() }
    }


    private fun loadData(){
        Connector.api.loadFaq().enqueue(object : Res<JsonArray>(this){
            override fun callBack(code: Int, body: JsonArray?) {
                when(code){

                }


            }


        })
    }

    private fun setAdpater() : DormitoryNoticeAdapter? {

        return null

    }

}