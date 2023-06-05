package com.example.lawadvat.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lawadvat.MainActivity
import com.example.lawadvat.databinding.ActivitySignupBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Signup : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var googleoption:GoogleSignInOptions
    lateinit var googleclient: GoogleSignInClient
    val firebaseAuth1:FirebaseAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signinBtn.setOnClickListener{
            sign_process()
        }
        binding.laterBtn.setOnClickListener{
           finishAffinity()
        }
    }
    private fun sign_process() {
        googleoption= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.example.lawadvat.R.string.default_web_client_id)).requestEmail().build()
        googleclient= GoogleSignIn.getClient(this,googleoption)
        googleclient.revokeAccess()
        sign_in()
    }

    private fun sign_in() {
        val intent: Intent =googleclient.signInIntent
        startActivityForResult(intent,100)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100)
        { val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val googleSignInAccount: GoogleSignInAccount = task.getResult(ApiException::class.java)
                val authCredential: AuthCredential = GoogleAuthProvider.getCredential(
                    googleSignInAccount.idToken, null
                )
                firebaseAuth= FirebaseAuth.getInstance()
                firebaseAuth.signInWithCredential(authCredential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Successfully login", Toast.LENGTH_LONG).show()
                           val user= task.result.user?.displayName.toString()
                            get_profile(user)
                            /*val intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)*/
                        } else {
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                        }


                    }
            }
            catch(e: ApiException)
            {
                Toast.makeText(this,task.exception?.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
    fun get_profile(user: String)
    {
        val databaseRef = FirebaseDatabase.getInstance().reference
        databaseRef.child("Lawyer").child(user).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                    val result: Requirement? =
                        snapshot.getValue(Requirement::class.java)
                    if (result?.layer == true) {
                        val editor =
                            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                        editor.putBoolean("lawyer", true)
                        editor.apply()
                        val intent = Intent(this@Signup, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        val editor =
                            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                        editor.putBoolean("lawyer", false)
                        editor.apply()
                        val intent = Intent(this@Signup, MainActivity::class.java)
                        startActivity(intent)
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Signup,error.message,Toast.LENGTH_SHORT).show()
            }

        })

    }
}