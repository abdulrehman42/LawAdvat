package com.example.lawadvat.ui.appintment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.ui.Variablecontain
import com.example.lawadvat.ui.appintment.appointment.Appoint
import com.example.lawadvat.ui.messege.Messege
import com.example.lawadvat.ui.review.ReviewActivity
import com.example.lawadvat.ui.review.Review_entity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AppointmentAdpter(val context: Context?, var list: ArrayList<Appoint?>) :
    RecyclerView.Adapter<AppointmentAdpter.ViewHolder>() {
    var checkit:Boolean=false
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val user = firebaseAuth.currentUser?.displayName.toString()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card_lawyer = view.findViewById<CardView>(R.id.lawyer_appointmen_cardview)
        val linear_lawyer = view.findViewById<LinearLayout>(R.id.lawyer_appointmen_linear)
        val lawyer_btn_linear = view.findViewById<LinearLayout>(R.id.lawyer_btn_linear)
        val profile_lawyer_name1 = view.findViewById<TextView>(R.id.profile_lawyer_name1)
        val client_name1 = view.findViewById<TextView>(R.id.client_name1)
        val location1 = view.findViewById<TextView>(R.id.location1)
        val time1 = view.findViewById<TextView>(R.id.time1)
        val fee1 = view.findViewById<TextView>(R.id.fee1)
        val date1 = view.findViewById<TextView>(R.id.date1)
        val image1 = view.findViewById<ImageView>(R.id.client_lawyer_image1)
        val btnpending = view.findViewById<Button>(R.id.pending)
        val reviewbtn=view.findViewById<TextView>(R.id.review_btn)
        val client_card = view.findViewById<CardView>(R.id.client_appointmen_cardview)
        val linear_client = view.findViewById<LinearLayout>(R.id.client_appointmen_linear)
        val lawyer_text_linear = view.findViewById<LinearLayout>(R.id.lawyer_text_linear)
        val show_answer = view.findViewById<TextView>(R.id.show_answer)
        val image2 = view.findViewById<ImageView>(R.id.profile_lawyer_image2)
        val profile_lawyer_name = view.findViewById<TextView>(R.id.lawyer_name)
        val client_name = view.findViewById<TextView>(R.id.client_name)
        val location = view.findViewById<TextView>(R.id.location)
        val time = view.findViewById<TextView>(R.id.time)
        val fee = view.findViewById<TextView>(R.id.fee)
        val date = view.findViewById<TextView>(R.id.date)
        val profile_lawyer_image = view.findViewById<ImageView>(R.id.profile_lawyer_image2)
        val accept_btn = view.findViewById<Button>(R.id.accept_btn)
        val reject_btn = view.findViewById<Button>(R.id.reject_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_appointment, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*val prefs = context?.getSharedPreferences("MY_PREFS_NAME", AppCompatActivity.MODE_PRIVATE)
        val check = prefs?.getBoolean("review",false)*/
        val l = list[position]
      /*  Handler(Looper.getMainLooper()).postDelayed({
            check_condition(l?.name_lawyer.toString())
        },5000)*/

        holder.reviewbtn.paintFlags = holder.reviewbtn.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        if (l?.name_lawyer!! == user) {
            if (l.check.equals("accept")) {
                holder.client_card.isVisible = false
                holder.linear_client.isVisible = false
                holder.lawyer_btn_linear.isVisible = false
                holder.lawyer_btn_linear.isVisible = false
                holder.show_answer.text = "you have accepted the appointment request"
                holder.profile_lawyer_name1.text = l?.name_lawyer.toString()
                holder.location1.text = l?.lawyer_location.toString()
                holder.client_name1.text = l?.client_name.toString()
                holder.fee1.text = "fee:" + l.fee.toString() + "PKR"
                holder.time1.text = l.time.toString()
                holder.date1.text = l?.date.toString()
                if (context != null) {
                    Glide.with(context).load(l?.client_img).into(holder.image1)
                }
            } else if (l.check.equals("reject")) {
                holder.client_card.isVisible = false
                holder.linear_client.isVisible = false
                holder.lawyer_btn_linear.isVisible = false
                holder.lawyer_btn_linear.isVisible = false
                holder.show_answer.text = "you have rejected the appointment request"
                holder.profile_lawyer_name1.text = l?.name_lawyer.toString()
                holder.location1.text = l?.lawyer_location.toString()
                holder.client_name1.text = l?.client_name.toString()
                holder.fee1.text = "fee:" + l?.fee.toString() + "PKR"
                holder.time1.text = l?.time.toString()
                holder.date1.text = l?.date.toString()
                if (context != null) {
                    Glide.with(context).load(l?.client_img).into(holder.image1)
                }
            } else {
                holder.client_card.isVisible = false
                holder.linear_client.isVisible = false
                holder.lawyer_btn_linear.isVisible = false
                holder.lawyer_btn_linear.isVisible = true
                holder.show_answer.isVisible = false
                holder.profile_lawyer_name1.text = l.name_lawyer.toString()
                holder.location1.text = l.lawyer_location.toString()
                holder.client_name1.text = l?.client_name.toString()
                holder.fee1.text = "fee:" + l?.fee.toString() + "PKR"
                holder.time1.text = l?.time.toString()
                holder.date1.text = l?.date.toString()
                if (context != null) {
                    Glide.with(context).load(l?.client_img).into(holder.image1)
                }

            }

        } else {
            if (l.check.equals("accept")) {
                holder.card_lawyer.isVisible = false
                holder.linear_lawyer.isVisible = false
                holder.profile_lawyer_name.text = l.name_lawyer.toString()
                holder.location.text = l.lawyer_location.toString()
                holder.client_name.text = l.client_name.toString()
                holder.fee.text = "fee:" + l.fee.toString() + "PKR"
                holder.time.text = l.time.toString()
                holder.date.text = l.date.toString()
                if (!checkit){
                    holder.reviewbtn.isVisible=true
                }
                holder.btnpending.text = "Accepted"
                holder.btnpending.setBackgroundColor(R.color.red)
                if (context != null) {
                    Glide.with(context).load(l.lawyer_img).into(holder.image2)
                }

            } else if (l.check.equals("reject")) {
                holder.card_lawyer.isVisible = false
                holder.linear_lawyer.isVisible = false
                holder.profile_lawyer_name.text = l?.name_lawyer.toString()
                holder.location.text = l?.lawyer_location.toString()
                holder.client_name.text = l?.client_name.toString()
                holder.fee.text = "fee:" + l?.fee.toString() + "PKR"
                holder.time.text = l?.time.toString()
                holder.date.text = l?.date.toString()
                holder.btnpending.text = "Rejected"
                holder.btnpending.setBackgroundColor(R.color.black)
                if (context != null) {
                    Glide.with(context).load(l?.lawyer_img).into(holder.image2)
                }
            } else {
                holder.card_lawyer.isVisible = false
                holder.linear_lawyer.isVisible = false
                holder.profile_lawyer_name.text = l?.name_lawyer.toString()
                holder.location.text = l?.lawyer_location.toString()
                holder.client_name.text = l?.client_name.toString()
                holder.fee.text = "fee:" + l?.fee.toString() + "PKR"
                holder.time.text = l?.time.toString()
                holder.btnpending.text = "pending"
                holder.date.text = l?.date.toString()
                if (context != null) {
                    Glide.with(context).load(l?.lawyer_img).into(holder.image2)
                }
            }
        }
        holder.reviewbtn.setOnClickListener {
            move_on(l)
        }
        holder.accept_btn.setOnClickListener {
            if (l?.name_lawyer!!.equals(user)) {
                update_database(
                    l.client_name,
                    l.lawyer_img,
                    l.client_img,
                    l.name_lawyer,
                    l.fee,
                    "accept",
                    l.date,
                    l.lawyer_location,
                    l.time
                )
                Handler(Looper.getMainLooper()).postDelayed({
                    notifyDataSetChanged()
                    Toast.makeText(context," please the page Refresh",Toast.LENGTH_SHORT).show()
                }, 100)
            }

        }
        holder.reject_btn.setOnClickListener {
            update_database(
                l.client_name,
                l.lawyer_img,
                l.client_img,
                l.name_lawyer,
                l.fee,
                "reject",
                l.date,
                l.lawyer_location,
                l.time
            )
            Handler(Looper.getMainLooper()).postDelayed({
               // notifyItemChanged(list.size)
                notifyDataSetChanged()
                Toast.makeText(context," please the page Refresh",Toast.LENGTH_SHORT).show()
            }, 100)
        }

    }

    private fun check_condition(name: String) {
        val database=FirebaseDatabase.getInstance().reference
        database.child("review").child(name).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //num = snapshot.childrenCount.toInt()
                    for (postSnapshot in snapshot.children) {
                        val result: Review_entity? = postSnapshot.getValue(Review_entity::class.java)
                        checkit = result?.send_review_name==user
                    }
                } else {
                    checkit=false
                    Toast.makeText(context, "data not found 2", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "What's wrong with you", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun move_on(l: Appoint) {
        val intent=Intent(context,ReviewActivity::class.java)
        intent.putExtra(Variablecontain.name,l?.name_lawyer)
        intent.putExtra(Variablecontain.img,l?.lawyer_img)
        context?.startActivity(intent)
    }

    private fun update_database(
        clientName: String?,
        lawyerImg: String?,
        clientImg: String?,
        nameLawyer: String,
        fee: String?,
        check: String?,
        date: String?,
        lawyerLocation: String?,
        time: String?
    ) {
        val database = FirebaseDatabase.getInstance().reference
        database.child(user).child(clientName.toString()).setValue(
            Appoint(
                name_lawyer = nameLawyer,
                lawyer_img = lawyerImg,
                fee = fee,
                check = check,
                lawyer_location = lawyerLocation,
                client_name = clientName,
                client_img = clientImg,
                time = time,
                date = date
            )
        )
        database.child(clientName.toString()).child(user).setValue(
            Appoint(
                name_lawyer = nameLawyer,
                lawyer_img = lawyerImg,
                fee = fee,
                check = check,
                lawyer_location = lawyerLocation,
                client_name = clientName,
                client_img = clientImg,
                time = time,
                date = date
            )
        )
    }

}