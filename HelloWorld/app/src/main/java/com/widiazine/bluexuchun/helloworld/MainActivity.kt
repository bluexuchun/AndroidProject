package com.widiazine.bluexuchun.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置内容ui 把界面加载出来
        setContentView(R.layout.activity_main)
        // 1、找到界面上的按钮
        var bt_click = findViewById<Button>(R.id.bt_click)
        // 2、点击按钮的时候触发相应点击事件
        bt_click.setOnClickListener(View.OnClickListener {
            // context默认的上下文，代表的是显示在哪个页面上
            Toast.makeText(this@MainActivity,"我被点击了",Toast.LENGTH_SHORT).show()
        })
    }

    // ? 代表某个变量的值 可以为null的时候 必须在声明的类型后添加？来标识该引用可为空
    // 第二种点击方式
//    fun click(v: View?){
//        when(v?.id){
//            R.id.bt_click ->
//                Toast.makeText(this,"我被点击了",Toast.LENGTH_SHORT).show()
//        }
//    }


}
