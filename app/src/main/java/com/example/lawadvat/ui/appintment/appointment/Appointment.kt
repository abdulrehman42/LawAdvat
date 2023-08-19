package com.example.lawadvat.ui.appintment.appointment

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lawadvat.databinding.ActivityAppointmentBinding
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.mail.SendMailTask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener
import java.text.SimpleDateFormat


class Appointment : AppCompatActivity() {
    lateinit var binding:ActivityAppointmentBinding
    val user_name=FirebaseAuth.getInstance().currentUser
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
        binding.dayDatePicker.setStartDate(25,7,2023)
        binding.dayDatePicker.setEndDate(25,7,2050)
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
    @SuppressLint("QueryPermissionsNeeded")
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
            val email=intent.getStringExtra(Variablecontain.email)
            val database = FirebaseDatabase.getInstance()
            val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
            val user_img= FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
            val myRef = database.getReference(user).child(name.toString())
            val myRef1=database.getReference(name.toString()).child(user)
            myRef.setValue(Appoint(
                name_lawyer =name, lawyer_img = img,
                fee =fee, lawyer_location = location,
                client_name = user,

                client_img = user_img, check = "false", time ="$hour:$minute:$value", date = date.toString(), lawyer_email = email))
            myRef1.setValue(Appoint(
                name_lawyer =name, lawyer_img = img,
                fee =fee, lawyer_location = location,
                client_name = user,
                client_img = user_img, check = "false",
                time ="$hour:$minute:$value", date = date.toString(),lawyer_email = email))
            val msg="Hi $name,\n Congratulations! Your new appointment has arrived." +
                    "\n ${user_name?.displayName.toString()} has sent you a appointment request.\n open the Lawadvat App"
            send_Email(email.toString(),
                msg
            )

            finish()
        }
    }
    private fun send_Email(
        Receiver: String,
        Message: String
    ) {
        Thread {
            try {

                val sm = SendMailTask(
                    this@Appointment,
                    "abdulrehman4241566@gmail.com",
                    Receiver,
                    "Appointment Confirmation",
                    Message,
                    "dtzxlvtlubqpigny"
                )
                sm.sendMail()
                /*Toast.makeText(this, "Mail send.", Toast.LENGTH_SHORT).show()*/
            } catch (e: Exception) {
                Log.e("SendMail", e.message, e)
            }
        }.start()
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