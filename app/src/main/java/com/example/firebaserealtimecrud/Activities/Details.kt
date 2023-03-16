package com.example.firebaserealtimecrud.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.TelephonyCallback.CallStateListener
import android.widget.Button
import android.widget.TextView

import com.example.firebaserealtimecrud.R;

public class Details : AppCompatActivity() {
    private lateinit var userId:TextView
    private lateinit var userName: TextView
    private lateinit var userAge:TextView
    private lateinit var userDesc:TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button


    private fun openUpdateDialog(
      userId:String,
        userName:String
    ){

    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initView()
        setValuesToViews()
        btnUpdate.setOnClickListener{
            openUpdateDialog(
               intent.getStringExtra("userId").toString(),
                       intent.getStringExtra("userName").toString()

            )
        }
    }

    private fun initView() {
        userId = findViewById(R.id.tvEmpId)
        userName = findViewById(R.id.tvEmpName)
        userAge = findViewById(R.id.tvEmpAge)
        userDesc= findViewById(R.id.tvEmpSalary)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
        userId.text=intent.getStringExtra("userId")
        userName.text=intent.getStringExtra("userName")
        userAge.text=intent.getStringExtra("userage")
        userDesc.text=intent.getStringExtra("userdesc")


    }

}