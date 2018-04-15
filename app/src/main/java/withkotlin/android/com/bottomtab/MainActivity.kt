package withkotlin.android.com.bottomtab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity(),BottomTab.BottomTabItemClick {
    override fun setOnClickListener(v: View) {
        Toast.makeText(this,v.tag.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<BottomTab>(R.id.bottom_tab).setOnClick(this)
    }
}
