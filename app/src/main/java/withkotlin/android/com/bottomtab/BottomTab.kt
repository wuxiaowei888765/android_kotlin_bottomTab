package withkotlin.android.com.bottomtab

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Created by wuxiaowei on 2018/4/12.
 */
class BottomTab(context: Context, attributeSet: AttributeSet?, defStyle: Int) : RelativeLayout(context, attributeSet, defStyle) {

    private var iconNumber = 0
    private var showLabel = false
    private var showIcon = true

    private var icon = ArrayList<Int>()
    private var icon_selected = ArrayList<Int>()
    private var labels = ArrayList<String>()
    private var layouts = ArrayList<LinearLayout>()

    private var lable_color = 0;
    private var lable_selected_color = 0;

    open interface BottomTabItemClick{
         fun setOnClickListener(v:View)
    }

    private lateinit var bottomTabItemClick:BottomTabItemClick


    fun setOnClick(bottomTabItemClick:BottomTabItemClick ){
       this.bottomTabItemClick = bottomTabItemClick
    }

    constructor (context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BottomTab)
        iconNumber = typedArray.getInt(R.styleable.BottomTab_tabNum, -1)
        showIcon = typedArray.getBoolean(R.styleable.BottomTab_showIcon, true)
        showLabel = typedArray.getBoolean(R.styleable.BottomTab_showLabel, false)
        lable_color = typedArray.getColor(R.styleable.BottomTab_label_color, -1)
        lable_selected_color = typedArray.getColor(R.styleable.BottomTab_label_selected_color, -1)
        icon.add(typedArray.getResourceId(R.styleable.BottomTab_icon1, -1))
        icon.add(typedArray.getResourceId(R.styleable.BottomTab_icon2, -1))
        icon.add(typedArray.getResourceId(R.styleable.BottomTab_icon3, -1))
        icon.add(typedArray.getResourceId(R.styleable.BottomTab_icon4, -1))
        icon_selected.add(typedArray.getResourceId(R.styleable.BottomTab_icon1_selected, -1))
        icon_selected.add(typedArray.getResourceId(R.styleable.BottomTab_icon2_selected, -1))
        icon_selected.add(typedArray.getResourceId(R.styleable.BottomTab_icon3_selected, -1))
        icon_selected.add(typedArray.getResourceId(R.styleable.BottomTab_icon4_selected, -1))
        if(showLabel){
            labels.add(typedArray.getString(R.styleable.BottomTab_label1))
            labels.add(typedArray.getString(R.styleable.BottomTab_label2))
            labels.add(typedArray.getString(R.styleable.BottomTab_label3))
            labels.add(typedArray.getString(R.styleable.BottomTab_label4))
        }

        var topLine = View(context)
        var rl: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                1)
        rl.addRule(RelativeLayout.ABOVE)
        topLine.setBackgroundColor(Color.parseColor("#f34649"))
        addView(topLine,rl)

        var tabLayout = LinearLayout(context)
        tabLayout.orientation = LinearLayout.HORIZONTAL
        tabLayout.gravity = Gravity.CENTER


        for (i in 0 until icon.size) {
            var layout = LinearLayout(context)
            layout.orientation = LinearLayout.VERTICAL
            layout.gravity = Gravity.CENTER
            layout.tag = i
            layouts.add(layout)
            layout.setOnClickListener({
                bottomTabItemClick.setOnClickListener(it)
                for (item in layouts) {
                    if(item.tag ==layout.tag){
                        (item.getChildAt(0) as ImageView).setImageResource(icon_selected[item.tag as Int])
                        (item.getChildAt(1) as TextView).setTextColor(lable_selected_color)
                    }else{
                        (item.getChildAt(0) as ImageView).setImageResource(icon[item.tag as Int])
                        (item.getChildAt(1) as TextView).setTextColor(lable_color)
                    }
                }
            })
            var iv = ImageView(context)
            var tv = TextView(context)
            tv.text = labels[i]
            if(i==0){
                iv.setImageResource(icon_selected[i])
                tv.setTextColor(lable_selected_color)
            }else{
                iv.setImageResource(icon[i])
                tv.setTextColor(lable_color)
            }

            var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            lp.weight = 1.0f
            lp.gravity = Gravity.CENTER
            layout.addView(iv,lp)
            if(showLabel){
                layout.addView(tv,lp)
            }
            tabLayout.addView(layout, lp)
        }

        var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        addView(tabLayout,lp)

        typedArray.recycle()
    }

    constructor (context: Context) : this(context, null)
}
