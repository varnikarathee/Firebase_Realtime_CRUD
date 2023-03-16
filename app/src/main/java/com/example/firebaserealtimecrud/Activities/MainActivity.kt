package com.example.firebaserealtimecrud.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firebaserealtimecrud.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    //create variables before oncreate
    private lateinit var btnInsert: Button
    private lateinit var btnFetch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase: DatabaseReference= FirebaseDatabase.getInstance().getReference()


        btnInsert= findViewById(R.id.insert)
        btnFetch= findViewById(R.id.fetch)

        btnInsert.setOnClickListener{
            val intent= Intent(this, InsertActivity::class.java )
            startActivity(intent)
        }

        btnFetch.setOnClickListener{
            val fintent= Intent(this, FetchActivity::class.java)
            startActivity(fintent)
        }




    }
}