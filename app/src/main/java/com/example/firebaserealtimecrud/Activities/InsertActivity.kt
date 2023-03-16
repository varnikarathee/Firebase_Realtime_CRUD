package com.example.firebaserealtimecrud.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebaserealtimecrud.R
import com.example.firebaserealtimecrud.Modals.usermodal
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.System.err

class InsertActivity : AppCompatActivity() {
   //LATEINIT MEANS INITIALISE WILL BE LATER
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var description:EditText
    private lateinit var savebtn:Button

    //database reference variable
    private lateinit var dbref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        name= findViewById(R.id.edname)
        age= findViewById(R.id.edage)
        description= findViewById(R.id.eddetail)
        savebtn=findViewById(R.id.savebtn)

        //Now make an instance of database, get reference me jo string hai voh inidcate the data we are saving ie. user's data , employees's data etc
        dbref=FirebaseDatabase.getInstance().getReference("Users")

        //callback to button
        savebtn.setOnClickListener{
            saveUserData()
        }




    }

    private fun saveUserData() {
       //getting values
        val username= name.text.toString()
        val userage= age.text.toString()
        val userdesc=description.text.toString()

        //create error for letting values be empty
        if(username.isEmpty()){
            name.error="Please enter username "
        }
        if(userage.isEmpty()){
            age.error="Please enter age"
        }
        if(userdesc.isEmpty()){
            description.error="Please enter description"
        }

        //for identifying each entry with unique id
        val empId=dbref.push().key!!


        //get the user modal or object

        val user= usermodal(empId,username,userage,userdesc)

        //add this object to database
        dbref.child(empId).setValue(user).addOnCompleteListener {
            Toast.makeText(this,"Data inserted succesfully",Toast.LENGTH_SHORT).show()
         //also clear text from views
            name.text.clear()
            age.text.clear()
            description.text.clear()

        }.addOnFailureListener{
            Toast.makeText(this,"Error: $err.message",Toast.LENGTH_SHORT).show()
        }




    }
}