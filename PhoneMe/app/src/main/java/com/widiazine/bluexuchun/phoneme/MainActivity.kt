package com.widiazine.bluexuchun.phoneme

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载布局
        setContentView(R.layout.activity_main)
        // 1.寻找关心的控件

        var bt_click = findViewById<Button>(R.id.bt_del)
        bt_click.setOnClickListener {
            handlePermission()
        }
    }

    /**
     * 打电话
     */
    fun callPhone(){
        // 2.获取输入的信息
        var bt_phone = findViewById<EditText>(R.id.et_phone)
        var phone = bt_phone.text.toString().trim()
        if(phone == ""){
            Toast.makeText(this@MainActivity,"请输入手机号码",Toast.LENGTH_SHORT).show()
        }else {
            println(phone)
            // 引申出 url统一资源定位符 http:// ftp://
            //       uri统一资源标识符(包含url) mailto:  tel://

            // 第一种方式 不涉及权限问题
            // 创建一个意图对象
//          var intent = Intent()

            // 设置动作的数据 parse 解码转换
//          var data = Uri.parse("tel:"+phone)
//          intent.setData(data)

            // 开启一个界面，根据程序员制定的行为来做事情
//          startActivity(intent)

            // 第二种方式 涉及权限问题
            var intent = Intent()

            intent.setAction(Intent.ACTION_CALL)

            var data = Uri.parse("tel:" + phone)

            intent.setData(data)

            startActivity(intent)

        }
    }

    /**
     * 动态申请权限
     */

    fun handlePermission():Boolean{
        // 如果不授予权限
        if(ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CALL_PHONE),1)
            return false
        }else{
            callPhone()
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPhone()
            }else{
                Toast.makeText(this@MainActivity,"Permission Denied",Toast.LENGTH_SHORT).show()
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
