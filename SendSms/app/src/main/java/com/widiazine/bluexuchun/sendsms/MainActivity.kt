package com.widiazine.bluexuchun.sendsms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * 发送短信
 */
class MainActivity : BaseActivity() {

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
//            sendSms()
            requestStoragePermissions()
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

    override fun allowSuccess() {
        super.allowSuccess()
        sendSms()
    }
}
