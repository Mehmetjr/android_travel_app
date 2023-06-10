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
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentForgotPassBinding
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPass : Fragment() {

    private lateinit var navControl: NavController
    private lateinit var binding : FragmentForgotPassBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPassBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()

        binding.btnResetpass.setOnClickListener {
            val pass = binding.edtForgotMail.text.toString()
            auth.sendPasswordResetEmail(pass)
                .addOnSuccessListener {
                Toast.makeText(context,"Lütfen mailinizi kontrol edin",Toast.LENGTH_LONG).show()
                    navControl.navigate(R.id.action_forgotPass_to_signin)
                }
                .addOnFailureListener {
                    Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()

                }
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view:View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }


}