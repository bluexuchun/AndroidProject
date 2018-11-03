package com.widiazine.bluexuchun.sendsms

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


/**
 * 动态申请权限
 */
open class BaseActivity:AppCompatActivity() {
    val REQUEST_CODE_PERMISSION = 1
    /**
     * 请求Storage权限
     */
    open fun requestStoragePermissions(){

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        // 检查读写权限
        val permissionCheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) //已经具备该权限
        {
            // TODO写自己的代码
        }else if(permissionCheck == PackageManager.PERMISSION_DENIED){
            //当用户此前曾拒绝过该权限，则需要给出请求该权限的说明。当用户没有勾选下次不提示，则返回true,不然返回false
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_PERMISSION)
            }else{//如果用户此前没有拒绝过，首次调用时，则无需提示，直接请求权限。当用户此前拒绝过并且勾选下次不提示，也会返回false。
                ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_PERMISSION)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == REQUEST_CODE_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){//第0个权限请求成功
                Toast.makeText(this,"权限请求成功",Toast.LENGTH_SHORT).show()
                allowSuccess()
            }else{//权限拒绝
                Toast.makeText(this,"权限请求失败",Toast.LENGTH_SHORT).show()
            }
        }
    }

    open fun allowSuccess(){

    }

    open fun allowDeny(){

    }
}