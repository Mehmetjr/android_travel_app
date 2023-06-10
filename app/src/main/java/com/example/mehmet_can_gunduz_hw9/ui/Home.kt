package com.example.mehmet_can_gunduz_hw9.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mehmet_can_gunduz_hw9.R
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentHomeBinding
import com.example.mehmet_can_gunduz_hw9.models.Place
import com.example.mehmet_can_gunduz_hw9.models.Places
import com.example.mehmet_can_gunduz_hw9.utils.PlaceAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Home : Fragment() {


    private lateinit var auth:FirebaseAuth
    private lateinit var authId: String
    private lateinit var database : DatabaseReference
    private lateinit var navControl:NavController
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter: PlaceAdapter
    private lateinit var builder : AlertDialog.Builder
    companion object{
        lateinit var placesList: MutableList<Places>
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        addEvents()
        getData()

    }
    private fun addEvents() {
        binding.btnAdd.setOnClickListener {
            navControl.navigate(R.id.action_home2_to_add22)
        }
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            navControl.navigate(R.id.action_home2_to_signin)

        }

        binding.RecyclerView.setOnLongClickListener {
            builder.setTitle("Alert!")
                .setMessage("Do you want to exit ?")
                .setCancelable(true) // dialog box in cancellable

                .setPositiveButton("Evet"){dialogInterface,it ->
                    deleteData()
                    dialogInterface.dismiss()
                }
                .setNegativeButton("Hayır"){dialogInterface,it ->

                    dialogInterface.cancel()
                }.show()

             true
        }


    }

    private fun deleteData() {
database.addListenerForSingleValueEvent(object :ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {
        snapshot.ref.removeValue().addOnCompleteListener {
            Toast.makeText(requireContext(),"Silindi",Toast.LENGTH_SHORT).show()
        } .addOnFailureListener {
            Toast.makeText(requireContext(),"",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCancelled(error: DatabaseError) {
        Toast.makeText(requireContext(),error.toString(),Toast.LENGTH_SHORT).show()
    }
})

    }

    private fun init(view: View){
        builder = AlertDialog.Builder(context)
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        authId = auth.currentUser!!.uid
        database =  Firebase.database.reference.child("Places")
            .child(authId)

        binding.RecyclerView.setHasFixedSize(true)
        binding.RecyclerView.layoutManager = LinearLayoutManager(context)
        placesList = mutableListOf()
        adapter = PlaceAdapter(placesList)
        binding.RecyclerView.adapter = adapter


    }

    private fun getData(){
        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    snapshot.children.forEach {
                        val placeVal = it.getValue(Places::class.java)
                        val placeData = Place(it.key!!, placeVal!!)
                        placesList.add(placeVal!!)

                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }





}