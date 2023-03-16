package com.example.firebaserealtimecrud.Activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimecrud.Adapter.UserAdapter
import com.example.firebaserealtimecrud.Modals.usermodal
import com.example.firebaserealtimecrud.R
import com.google.firebase.database.*

class FetchActivity : AppCompatActivity() {
    private lateinit var userrecyclerview:RecyclerView
    private lateinit var load:TextView
    //create list for users
    private lateinit var userlist:ArrayList<usermodal>
    //database reference
    private lateinit var dbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        userrecyclerview=findViewById(R.id.rview)
        userrecyclerview.layoutManager=LinearLayoutManager(this)
        userrecyclerview.setHasFixedSize(true)
        load=findViewById(R.id.loadingdata)


        //create arraylistof
        userlist=arrayListOf<usermodal>()

        //getEmployeeData
        getuserData()



    }

    private fun getuserData() {
        //first recyclerview not visible but loading data visible
        userrecyclerview.visibility= View.GONE
        load.visibility=View.VISIBLE

        //database instance made
        dbref=FirebaseDatabase.getInstance().getReference("Users")

        //
        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              //to avoid duplicate values
                userlist.clear()
                //if data exists then proceed with data : here snapshot indicates dta
                if(snapshot.exists()){
                    //then hitting data one by one
                    for(usersnap in snapshot.children){
                        //referencing user modal class to take values
                        val userdata= usersnap.getValue(usermodal::class.java)
                        //add username to the data
                        userlist.add(userdata!!)

                    }

                    //for showcasing data in recycler view
                    val mAdapter:UserAdapter=UserAdapter(userlist)
                    userrecyclerview.adapter=mAdapter

                    //now making loading data invisible and showcasing the list
                    load.visibility=View.GONE
                    userrecyclerview.visibility=View.VISIBLE

                 mAdapter.setOnItemClickListener(object:UserAdapter.OnItemClickListener{
                     override fun OnItemClicked(position: Int) {
                         //going to next details activity
                         val intent= Intent(this@FetchActivity,Details::class.java)
                           //passing the values to details activity
                         intent.putExtra("userId",userlist[position].empId)
                         intent.putExtra("userName",userlist[position].userName)
                         intent.putExtra("userage",userlist[position].userage)
                         intent.putExtra("userdesc",userlist[position].userdesc)
                         startActivity(intent)
                     }

                 })


                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })




    }
}