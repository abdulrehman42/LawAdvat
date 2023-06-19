package com.example.lawadvat.ui.setting
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lawadvat.MainActivity
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSettingBinding
import com.example.lawadvat.ui.become.FirstFragment
import com.example.lawadvat.ui.signup.Signup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class Setting : Fragment() {
    private lateinit var binding:FragmentSettingBinding
    private lateinit var viewModel: SettingViewModel
    lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.teal_700);
        binding= FragmentSettingBinding.inflate(inflater)
        requireActivity().actionBar?.title = "Profile"
        val prefs = requireActivity().getSharedPreferences("MY_PREFS_NAME", AppCompatActivity.MODE_PRIVATE)
        val check = prefs.getBoolean("lawyer",false)
        binding.become.isVisible = !check
        viewModel = ViewModelProvider(this)[SettingViewModel::class.java]
        auth=FirebaseAuth.getInstance()
        binding.displayNameSetting.text=auth.currentUser?.displayName
        Glide.with(this)
            .load(auth.currentUser?.photoUrl)
            .into(binding.profileSetting)

        binding.logout.setOnClickListener{
            auth.signOut()
            val editor =
                requireActivity().getSharedPreferences("MY_PREFS_NAME", AppCompatActivity.MODE_PRIVATE).edit()
            editor.putBoolean("lawyer", false)
            editor.apply()
            val intent=Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
        }
        binding.feedback.setOnClickListener{
            sendEmail()
        }
        binding.join.setOnClickListener{
            if (auth.currentUser!=null)
            {
                Snackbar.make(requireView(),"Already Signin",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }
        binding.become.setOnClickListener{
            if (auth.currentUser!=null)
            {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main,FirstFragment())?.addToBackStack(null)
                    ?.commit()
                //Snackbar.make(requireView(),"we working on it",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }
        /*binding.complain.setOnClickListener{
            if (auth.currentUser!=null)
            {
                Snackbar.make(requireView(),"we working on it",Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                val intent=Intent(requireActivity(),Signup::class.java)
                startActivity(intent)
            }
        }*/
        return binding.root
    }

    private fun sendEmail() {

        val selectorIntent = Intent(Intent.ACTION_SENDTO)
        val urlString = "mailto:" + Uri.encode("abdulrehman4241566@gmail.com") + "?subject=" + Uri.encode("Your valueable feedback") + "&body=" + Uri.encode("")
        selectorIntent.data = Uri.parse(urlString)

        /* val emailIntent = Intent(Intent.ACTION_SEND)
         emailIntent.putExtra(Intent.EXTRA_EMAIL, "hammadrfq3@gmail.com")
         emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your valuable feedback")
         emailIntent.putExtra(Intent.EXTRA_TEXT, "")
         emailIntent.selector = selectorIntent*/

        try {
            startActivity(Intent.createChooser(selectorIntent, "Send email..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show()
        }
    }



}