package com.example.lawadvat.ui.appintment.appointment

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.ActivityAppointmentBinding
import com.example.lawadvat.ui.Variablecontain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener
import java.text.SimpleDateFormat


class Appointment : AppCompatActivity() {
    lateinit var binding:ActivityAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.customView=binding.toolbarFirm
        binding.toolbarFirm.setNavigationOnClickListener {
            finish()
        }
        in_it()
        binding.dayDatePicker.setStartDate(1,6,2023)
        binding.dayDatePicker.setEndDate(1,6,2050)
        binding.dayDatePicker.getSelectedDate(OnDateSelectedListener { date ->
            if (date != null) {
                val editor =
                    getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                editor.putString("date", SimpleDateFormat.getDateInstance().format(date).toString())
                editor.apply()
            }
            else{
                val editor =
                    getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
                editor.putString("date",null)
                editor.apply()
            }
        })
        val items = arrayOf("AM","PM")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)
        binding.spinner1.adapter = adapter
        binding.confirmBtn.setOnClickListener {
            check_it()
        }

    }
    private fun check_it() {
        val get =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val date = get.getString("date",null)
        val value=binding.spinner1.selectedItem.toString()
        val hour=binding.hour.text.toString()
        val minute=binding.minute.text.toString()
        if (hour.isEmpty())
        {
           Toast.makeText(this,"Doesn't set hour",Toast.LENGTH_SHORT).show()
        }else if (minute.isEmpty())
        {
            Toast.makeText(this,"Doesn't set minute",Toast.LENGTH_SHORT).show()
        }else if (date==null)
        {
            Toast.makeText(this,"please Select the date",Toast.LENGTH_SHORT).show()

        }
        else
        {
            val name=intent.getStringExtra(Variablecontain.name)
            val img=intent.getStringExtra(Variablecontain.img)
            val location=intent.getStringExtra(Variablecontain.location)
            val fee=intent.getStringExtra(Variablecontain.fee)
            val database = FirebaseDatabase.getInstance()
            val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
            val user_img= FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
            val myRef = database.getReference(user)
            myRef.setValue(Appoint(name_lawyer =name, lawyer_img = img,fee=fee, lawyer_location = location,client_name = user,client_img = user_img ))
            Toast.makeText(this, "$hour:$minute$value $date",Toast.LENGTH_SHORT).show()

            //finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor =
            getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit()
        editor.putString("date",null)
        editor.apply()
    }
    @SuppressLint("SetTextI18n")
    private fun in_it() {
        val name=intent.getStringExtra(Variablecontain.name)
        val img=intent.getStringExtra(Variablecontain.img)
        val location=intent.getStringExtra(Variablecontain.location)
        val fee=intent.getStringExtra(Variablecontain.fee)
        Glide.with(this).load(img).into(binding.profileLawyerImage1)
        binding.profileLawyerName1.text=name.toString()
        binding.location1.text=location.toString()
        binding.fee1.text="Fee:"+fee.toString()+"PKR"
    }
}