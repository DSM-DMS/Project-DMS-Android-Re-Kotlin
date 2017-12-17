package teamdms.dms_kotlin.Fragment

import android.os.*
import android.support.v4.app.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_apply_main.view.*
import kotlinx.android.synthetic.main.view_apply_list_child.view.*
import kotlinx.android.synthetic.main.view_apply_list_parent.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*


/**
 * Created by dsm2016 on 2017-12-15.
 */

class ApplyMainFragment : Fragment() {

    var rootView: View? = null
    lateinit var inflater: LayoutInflater


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_apply_main, container, false)
        applyListLoad()
        return rootView
    }

    private fun applyListLoad(){
        with(rootView!!){
            expandable_apply_list_layout.hello()
        }

    }

    private fun applyGoingout(token: String,sat : Boolean, sun: Boolean){
        Connector.api.applyOut(token,sat,sun).enqueue(object : Res<Void>(activity){
            override fun callBack(code: Int, body: Void?) {
                when(code){
                    200 -> Util.showToast(activity,"신청이 완료되었습니다.")
                    else -> Util.showToast(activity, "오류 : $code")
                }
            }
        })
    }


    private fun createParentView(text: String, backgroundColor: Int): View {
        var view= LayoutInflater.from(context).inflate(R.layout.view_apply_list_parent, null)

        view.tv_apply_list_parent_title.text = text
        view.tv_apply_list_parent_title.setBackgroundColor(backgroundColor)

        return view!!
    }


    private fun createChildView(backgroundColor: Int, image: Int, listener: View.OnClickListener,goingCheck : Boolean): View{
        var view : View

            when(goingCheck){
                true->{
                    view = LayoutInflater.from(context).inflate(R.layout.view_apply_list_child_goingout, null)

                    with(view!!) {
                        view.layout_apply_list_child.setBackgroundColor(backgroundColor)
                        view.iv_apply_list_child.setImageResource(image)
                        view.ib_apply_list_child_enter.setOnClickListener(listener)
                    }
                }
                else -> {
                    view = LayoutInflater.from(context).inflate(R.layout.view_apply_list_child, null)
/*
                    with(view!!) {
                        view.btn_apply_list_child_goingout_apply.setOnClickListener {
                            val sat = view.switch_apply_list_child_goingout_saturday.isChecked
                            val sun = view.switch_apply_list_child_goingout_sunday.isChecked

                            applyGoingout(Util.getToken(activity), sat, sun)
                        }
                    }*/
                }
            }
        return view!!
    }
}

