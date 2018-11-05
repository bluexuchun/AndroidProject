package com.widiazine.bluexuchun.sendsms

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * 发送短信
 */
class MainActivity : AppCompatActivity() {

    var phone:EditText? = null
    var content:EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取控件
        phone = findViewById<EditText>(R.id.et_phone)

        content = findViewById<EditText>(R.id.et_content)

        var send = findViewById<Button>(R.id.bt_click)

        send.setOnClickListener {
            if(hasWriteExternalStoragePermission()){
                sendSms()
            }else{
                applyWriteExternalStoragePermission()
            }
        }

    }

    fun sendSms(){
        var phoneNumber = phone?.text.toString().trim()
        var message = content?.text.toString().trim()

        if(phoneNumber == "" || message == ""){
            Toast.makeText(this@MainActivity,"手机号或内容不能为空",Toast.LENGTH_SHORT).show()
        }else{
            println("phoneNumber:${phoneNumber},发送内容是${message}")
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(phoneNumber, null, message,null, null)
        }
    }

    /**
     * 申请权限
     */
    public fun applyWriteExternalStoragePermission(){
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this@MainActivity,permissions,0)
    }

    /**
     * 检查是否有写磁盘的权限
     */
    public fun hasWriteExternalStoragePermission():Boolean{
        val result = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // 用户同意权限
            Toast.makeText(this@MainActivity,"Permission Allow",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@MainActivity,"Permission Denied",Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}
