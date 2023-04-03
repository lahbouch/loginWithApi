package com.example.loginwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var methodChoosenStatus : TextView
    lateinit var resultStatus : TextView
    lateinit var roleStatus : TextView
    lateinit var username : TextView
    lateinit var pwd : TextView
    lateinit var pb : ProgressBar
    private val GET_METHOD = 0
    private val POST_METHOD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        methodChoosenStatus = findViewById(R.id.methodChoosenStatus)
        username = findViewById(R.id.username)
        pwd = findViewById(R.id.password)
        resultStatus = findViewById(R.id.resultStatus)
        pb = findViewById(R.id.progressBar)
        roleStatus = findViewById(R.id.roleStatus)

    }

    fun getCall(view: View) {
        Log.e("TAG", "getCall: " )
        methodChoosenStatus.text = "GET Method"
        methodChoosenStatus.visibility = View.VISIBLE
        pb.visibility = View.VISIBLE
        val username = username.text.toString()
        val pwd = pwd.text.toString()
        AuthTask(this,pb,resultStatus,roleStatus,GET_METHOD).execute(username,pwd)
    }

    fun postCall(view: View) {
        Log.e("TAG", "getCall: " )
        methodChoosenStatus.text = "POST Method"
        methodChoosenStatus.visibility = View.VISIBLE
        pb.visibility = View.VISIBLE
        val username = username.text.toString()
        val pwd = pwd.text.toString()
        AuthTask(this,pb,resultStatus,roleStatus,POST_METHOD).execute(username,pwd)
    }
}