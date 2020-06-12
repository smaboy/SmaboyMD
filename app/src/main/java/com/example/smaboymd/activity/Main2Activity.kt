package com.example.smaboymd.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.example.smaboymd.custom.MyListView
import com.example.smaboymd.custom.MyRecycler
import com.example.smaboymd.custom.MyViewPager
import com.example.smaboymd.custom.TitleBarView
import com.google.android.material.textfield.TextInputLayout
import com.simple.spiderman.SpiderMan
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


class Main2Activity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            init()
        } catch (e: Exception) {
            SpiderMan.show(e)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main2
    }

    override fun init() {

        initTitleBar(find(R.id.tbv_title), String.format("%s", "Main2Activity"))

        val username = find<TextInputLayout>(R.id.til_username).apply {
            counterMaxLength = 10
            isCounterEnabled = true
        }
        find<EditText>(R.id.et_username).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.contains("@") == true) {
                    username.error = "邮箱格式错误"
                    username.setErrorIconDrawable(R.mipmap.ic_launcher)
                    username.isErrorEnabled = true
                } else {
                    username.isErrorEnabled = false
                }
            }

        })

        find<MyRecycler>(R.id.recycler).apply {
            layoutManager =
                LinearLayoutManager(this@Main2Activity, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter(this@Main2Activity)
        }

    }

    class MyAdapter(private val mContext: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        companion object {
            /**
             * 竖直滑动列表类型
             */
            const val LIST_VERTICAL_TYPE = 0

            /**
             * 水平滑动列表类型
             */
            const val LIST_HORIZONTAL_TYPE = 1

            /**
             * 不能滑动的文本类型
             */
            const val TEXT_NO_SCROLL_TYPE = 2
        }

        val views = arrayListOf(
            TextView(mContext).apply {
                backgroundColor = Color.BLUE
                text = "测试111"
                gravity = Gravity.CENTER
            },
            TextView(mContext).apply {
                backgroundColor = Color.RED
                text = "测试222"
                gravity = Gravity.CENTER
            },
            TextView(mContext).apply {
                backgroundColor = Color.GREEN
                text = "测试333"
                gravity = Gravity.CENTER
            },LayoutInflater.from(mContext).inflate(R.layout.view_list_v_type, null, false).apply {
                findViewById<MyListView>(R.id.listView).adapter = ArrayAdapter(
                    mContext, android.R.layout.simple_list_item_1,
                    arrayListOf("1", "2", "3", "1", "2", "3", "1", "2", "3")
                )
            }
        )

        private val mAdapter = object : PagerAdapter() {
            override fun instantiateItem(container: ViewGroup, position: Int): Any {

                container.addView(views[position])
                return views[position]
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun getCount(): Int {
                return views.size
            }

            override fun destroyItem(
                container: ViewGroup,
                position: Int,
                `object`: Any
            ) {
                container.removeView(views[position])
            }
        }

        /**
         * 类型集合
         */
        private val typeArray =
            arrayListOf(LIST_VERTICAL_TYPE, LIST_HORIZONTAL_TYPE, TEXT_NO_SCROLL_TYPE)

        inner class ListVerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var listView: MyListView = itemView.find(R.id.listView)
        }

        inner class ListHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView = itemView.find(R.id.text)
        }

        inner class TextNoScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView = itemView.find(R.id.text)
            var viewPager: MyViewPager = itemView.find(R.id.view_pager)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                LIST_VERTICAL_TYPE -> ListVerticalViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.view_list_v_type, parent, false)
                )
                LIST_HORIZONTAL_TYPE -> ListHorizontalViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.view_list_h_type, parent, false)
                )
                else -> TextNoScrollViewHolder(
                    LayoutInflater.from(mContext)
                        .inflate(R.layout.view_text_no_scroll_type, parent, false)
                )
            }
        }

        override fun getItemCount(): Int {
            return typeArray.size
        }

        override fun getItemViewType(position: Int): Int {
            return typeArray[position]
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is ListVerticalViewHolder -> {
                    holder.listView.apply {
                        adapter = ArrayAdapter(
                            mContext, android.R.layout.simple_list_item_1,
                            arrayListOf("1", "2", "3", "1", "2", "3", "1", "2", "3")
                        )
//                        requestDisallowInterceptTouchEvent(true)
                    }

                }
                is ListHorizontalViewHolder -> {
                    holder.textView.text = "我是水平滑动的"
                }
                is TextNoScrollViewHolder -> {
                    holder.textView.text = "哈哈哈我是测试了"
                    holder.viewPager.adapter = mAdapter

                }
            }

        }
    }

}
