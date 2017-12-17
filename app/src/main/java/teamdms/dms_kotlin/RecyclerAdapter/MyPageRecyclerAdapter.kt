package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.view_mypage_bug_report.view.*
import kotlinx.android.synthetic.main.view_mypage_list_content.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*
import teamdms.dms_kotlin.Fragment.*

/**
 * Created by root1 on 2017. 11. 30..
 */
class MyPageRecyclerAdapter(fragment: MyPageFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    lateinit var fragment: MyPageFragment

    init { this.fragment = fragment }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        inflater = LayoutInflater.from(parent?.context)
        context = parent!!.context

        return when(viewType){
            0,3,6 -> {
                val view = inflater.inflate(R.layout.view_mypage_list_no_content, parent, false)
                MyPageListNoContentViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.view_mypage_list_content, parent, false)
                MyPageListContentViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(position % 3 == 0) return

        val contentHolder = holder as MyPageListContentViewHolder
        val haveToken = !(Util.getToken(context) == "JWT ")

        when(position){
            1 -> {
                if (haveToken){
                    contentHolder.bind("로그아웃", { _ ->
                        Util.removeToken(context)
                        Util.showToast(context, "로그아웃 성공")
                        fragment.setStateData(null)
                        notifyDataSetChanged()
                    })
                }else{
                    contentHolder.bind("로그인", { _ ->
                        context.startActivity(Intent(context, SignInActivity::class.java))
                    })
                }
            }
            2 -> contentHolder.bind("비밀번호 변경", { _ ->
//                context.startActivity(Intent(this))
            })
            4 -> contentHolder.bind("버그 신고", { _ ->
                    val editBugView = inflater.inflate(R.layout.view_mypage_bug_report, null)
                    Util.showDialog(context, "버그 신고")
                            .setPositiveButton("신고", { dialog, _ ->
                                with(editBugView){
                                    if(sendBugReport(edit_mypage_bug_report.text.toString())){
                                        dialog.dismiss()
                                    }else{ Util.showToast(context, "버그를 입력하세요") }
                                }
                            })
                            .setView(editBugView)
                            .create().show()
                    })
            5 -> contentHolder.bind("개발자 소개", { _ -> Util.showDialog(context, "버그 신고")
                    .setMessage("다음 업데이트 때에\n더 멋진 모습으로 뵙겠습니다.")
                    .setPositiveButton("확인", { dialog, _ -> dialog.dismiss() })
                    .create().show()})
        }
    }

    override fun getItemCount(): Int = 7

    override fun getItemViewType(position: Int): Int = position

    private fun sendBugReport(message: String): Boolean{
        if(message.isEmpty()) return false
        Connector.api.sendBugReport(Util.getToken(context), "", message)
                .enqueue(object : Res<Void>(context){
                    override fun callBack(code: Int, body: Void?) {
                        if(code == 201){ Util.showToast(context, "전송 성공") }
                        else { Util.showToast(context, "전송 실패 : $code")}
                    }
                })
        return true
    }

}

class MyPageListNoContentViewHolder(view: View): RecyclerView.ViewHolder(view)

class MyPageListContentViewHolder(view: View): RecyclerView.ViewHolder(view){

    lateinit var rootView: View

    init { rootView = view }

    fun bind(title: String, onClick: (Any) -> Unit){
        with(rootView){ text_mypage_list_title.text = title }
        rootView.setOnClickListener(onClick)
    }

}