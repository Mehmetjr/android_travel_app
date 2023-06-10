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
import com.example.mehmet_can_gunduz_hw9.databinding.FragmentSigninBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class Signin : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        LoginEvents()
    }

    private fun init(view:View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }

    private fun LoginEvents(){

        binding.txtForgotPass.setOnClickListener {
            navControl.navigate(R.id.action_signin_to_forgotPass)
        }

        binding.txtIntoup.setOnClickListener {
            navControl.navigate(R.id.action_signin_to_signUp)
        }

        binding.btnGiris.setOnClickListener {
            val email = binding.edtSigninEmail.text.toString().trim()
            val pass = binding.edtSigninPassword.text.toString().trim()


            if (email.isNotEmpty() && pass.isNotEmpty()){

                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(context,"Giriş Başarılı", Toast.LENGTH_LONG).show()
                                navControl.navigate(R.id.action_signin_to_home2)
                            }
                            else{
                                Toast.makeText(context,it.toString(), Toast.LENGTH_LONG).show()
                            }
                        })



            }
        }
    }


}