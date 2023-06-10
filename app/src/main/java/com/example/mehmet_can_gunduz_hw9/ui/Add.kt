package com.example.mehmet_can_gunduz_hw9.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mehmet_can_gunduz_hw9.R
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentAddBinding
import com.example.mehmet_can_gunduz_hw9.models.Places
import com.example.mehmet_can_gunduz_hw9.ui.Home.Companion.placesList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentAddBinding
    private lateinit var dbRef : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        noteAddEvents()
    }
    private fun init(view: View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference.child("Places").child(auth.currentUser?.uid.toString())

    }
    private fun noteAddEvents(){

        binding.btnAddKayit.setOnClickListener {

            val title = binding.edtTitle.text.toString()
            val city = binding.edtCity.text.toString()
            val note = binding.edtNote.text.toString()

            if (title.isNotEmpty()&&city.isNotEmpty()&&note.isNotEmpty()){

                val fid = dbRef.push().key

                val pl = Places(title, city,note)
                placesList.add(pl)
                dbRef.child(fid!!).setValue(pl).addOnCompleteListener{
                    Toast.makeText(context, "Save Success", Toast.LENGTH_LONG).show()
                    navControl.navigate(R.id.action_add2_to_home2)
                }
            }else{

                Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
            }

        }

    }


}