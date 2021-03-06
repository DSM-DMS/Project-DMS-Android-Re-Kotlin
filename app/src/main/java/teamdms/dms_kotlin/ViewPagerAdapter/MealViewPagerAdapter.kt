package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.*
import teamdms.dms_kotlin.Fragment.*
import java.util.*

/**
 * Created by root1 on 2017. 11. 26..
 */
class MealViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    val today = Date()
    val calendar = Calendar.getInstance()

    override fun getItem(position: Int): Fragment {
        calendar.time = today
        calendar.add(Calendar.DATE, position)
        return MealContentFragment.newInstance(calendar.time)
    }

    override fun getCount(): Int {
        return 30
    }

}