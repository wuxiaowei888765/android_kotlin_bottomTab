# android_kotlin_bottomTab(持续更新)

<img width="324" height="576" src="https://github.com/wuxiaowei888765/android_kotlin_bottomTab/blob/master/Screenshot1.png?raw=true"/>

### 目前需要持续添加的还有如下：
* 文字显示颜色变化
* 图标右上角可能会出现消息提示
* 中间可能是圆形按钮活着自定义按钮
* 传入数据的方式（如：iocn，lable）
* 支持viewPager
* 添加自定义的布局方式

### 目前实现的功能有：
* 添加icon
* 点击iocn回调
* 添加lable

```xml
<withkotlin.android.com.bottomtab.BottomTab
       android:id="@+id/bottom_tab"
       android:layout_width="match_parent"
       android:layout_height="70dp"
       tab:tabNum="4"
       android:layout_alignParentBottom="true"
       tab:showIcon="true"
       tab:icon1="@drawable/a"
       tab:icon2="@drawable/b"
       tab:icon3="@drawable/c"
       tab:icon4="@drawable/d"
       tab:icon1_selected="@drawable/a_a"
       tab:icon2_selected="@drawable/b_b"
       tab:icon3_selected="@drawable/c_c"
       tab:icon4_selected="@drawable/d_d"
       tab:label1="纸牌"
       tab:label2="货到付款"
       tab:label3="球鞋"
       tab:label4="店铺"
       tab:showLabel="true"/>
