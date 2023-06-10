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
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SignUp : Fragment() {


    private lateinit var auth:FirebaseAuth
    private lateinit var navControl:NavController
    private lateinit var binding:FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view:View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }

    private fun registerEvents(){

        binding.txtUptoin.setOnClickListener {
            navControl.navigate(R.id.action_signUp_to_signin)
        }

        binding.btnKayT.setOnClickListener {
            val email = binding.edtSignupEmail.text.toString().trim()
            val pass = binding.edtSignupPassword.text.toString().trim()
            val confPass = binding.edtSignupConfpassword.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() && confPass.isNotEmpty()){
                if (pass==confPass){
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(context,"Kayıt Başarılı",Toast.LENGTH_LONG).show()
                                navControl.navigate(R.id.action_signUp_to_home2)
                            }
                            else{
                                Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
                            }
                        })
                }


            }
        }
    }


}