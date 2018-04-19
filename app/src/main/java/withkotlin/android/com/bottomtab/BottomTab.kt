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
    private var lable_selected_color = 0
    private lateinit var messageView: TextView
    private var messageIndex = 0

    open interface BottomTabItemClick {
        fun setOnClickListener(v: View)
    }

    private lateinit var bottomTabItemClick: BottomTabItemClick


    fun setOnClick(bottomTabItemClick: BottomTabItemClick) {
        this.bottomTabItemClick = bottomTabItemClick
    }

    constructor (context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BottomTab)
        iconNumber = typedArray.getInt(R.styleable.BottomTab_tabNum, -1)
        messageIndex = typedArray.getInt(R.styleable.BottomTab_message_index, -1)
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
        if (showLabel) {
            labels.add(typedArray.getString(R.styleable.BottomTab_label1))
            labels.add(typedArray.getString(R.styleable.BottomTab_label2))
            labels.add(typedArray.getString(R.styleable.BottomTab_label3))
            labels.add(typedArray.getString(R.styleable.BottomTab_label4))
        }

        var topLine = View(context).apply {
            setBackgroundColor(Color.parseColor("#f34649"))
        }

        var rl: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                1).apply {
            addRule(RelativeLayout.ABOVE)
        }

        addView(topLine, rl)

        var tabLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        for (i in 0 until icon.size) {
            var layout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                tag = i
            }
            layouts.add(layout)
            layout.setOnClickListener({
                bottomTabItemClick.setOnClickListener(it)
                layouts.forEach {
                    if (it.tag == layout.tag) {
                        ((it.getChildAt(0) as FrameLayout).getChildAt(0) as ImageView).setImageResource(icon_selected[it.tag as Int])
                        (it.getChildAt(1) as TextView).setTextColor(lable_selected_color)
                    } else {
                        ((it.getChildAt(0) as FrameLayout).getChildAt(0) as ImageView).setImageResource(icon[it.tag as Int])
                        (it.getChildAt(1) as TextView).setTextColor(lable_color)
                    }
                }
            })
            var iv = ImageView(context)
            var frameLayout = FrameLayout(context)
            var tv = TextView(context).apply {
                text = labels[i]
            }

            if (i == 0) {
                iv.setImageResource(icon_selected[i])
                lable_selected_color.let {
                    tv.setTextColor(it)
                }
            } else {
                iv.setImageResource(icon[i])

                lable_color.let {
                    tv.setTextColor(it)
                }
            }

            var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT).apply {
                weight = 1.0f
                gravity = Gravity.CENTER
            }
            //frameLayout add view
            frameLayout.addView(iv)
            var fp: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.RIGHT
                topMargin = 10
            }

            if (i == messageIndex) {
                messageView = TextView(context)
                messageView.setTextColor(Color.WHITE)
                messageView.setBackgroundColor(Color.RED)
                messageView.textSize = 10.0f
                frameLayout.addView(messageView, fp)
            }
            layout.addView(frameLayout, lp)

            if (showLabel) layout.addView(tv, lp)

            tabLayout.addView(layout, lp)
        }

        var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        addView(tabLayout, lp)
        typedArray.recycle()
    }

    fun setMessageCount(count: Int) {
            messageView.apply {
                text = if(count>99)
                    "99+"
                else
                    count.toString()
            }

    }

    constructor (context: Context) : this(context, null)
}
