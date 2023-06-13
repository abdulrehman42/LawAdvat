package com.example.lawadvat.ui.become

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSeventhBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.mail.SendMailTask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SeventhFragmen : Fragment() {
    lateinit var binding: FragmentSeventhBinding
    val user_id=FirebaseAuth.getInstance().currentUser?.uid.toString()
    val items = arrayOf("Murder", "Assault", "Theft", "Robbery", "Offenses","Fraud","Cybercrime","Kidnapping","Personal Injury","Property Disputes","Contract Disputes","Family Law","Divorce","Child Custody","Child Suppor","Adoption","Domestic Violence","Immigration Appeals","Regulatory Compliance","Tax Appeals","Government Agency Disputes","Civil Rights Violations","Freedom of Speech","Employment Law","Pollution","Climate Change","Environmental Cleanup")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSeventhBinding.inflate(layoutInflater)
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val update_profile=arguments?.getParcelable<Requirement>("update")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        binding.itemFirst.setAdapter(adapter)
        binding.itemSecond.setAdapter(adapter)
        binding.itemThird.setAdapter(adapter)
        binding.itemForth.setAdapter(adapter)
        binding.itemFirst.threshold=1
        binding.itemSecond.threshold=1
        binding.itemThird.threshold=1
        binding.itemForth.threshold=1
        binding.backBtn.setOnClickListener {
            back_data()
        }
        binding.saveBtn.setOnClickListener {
           if (update_profile!=null)
           {
               alternative_update(update_profile)
           }
           else if (array != null) {
                alternative(array)
            }
        }
        return binding.root
    }

    private fun alternative_update(update: Requirement) {
        val n = binding.itemName.text.toString()
        val e = binding.itemEmail.text.toString()
        val num = binding.itemNumber.text.toString()
        val c = binding.itemCnic.text.toString()
        val country = binding.itemCountry.text.toString()
        val province = binding.itemProvince.text.toString()
        val city = binding.itemCity.text.toString()
        val location = binding.itemLocation.text.toString()
        val genderbtn: RadioGroup = binding.radioGroup
        val general = binding.itemGeneral.text.toString()
        val high_cout = binding.itemHigh.text.toString()
        val supreme = binding.itemSupreme.text.toString()
        val des = binding.itemDesc.text.toString()
        val experience = binding.itemExperience.text.toString()
        val fee = binding.itemFee.text.toString()
        val first = binding.itemFirst.text.toString()
        val second = binding.itemSecond.text.toString()
        val third = binding.itemThird.text.toString()
        val fourth=binding.itemForth.text.toString()
        val study1 = binding.itemFirststudy.text.toString()
        val study2 = binding.itemSecondstudy.text.toString()
        val study3 = binding.itemThirdstudy.text.toString()
        val study4=binding.itemForthstudy.text.toString()
        genderbtn.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.male -> {
                    binding.male.isChecked = true
                }

                R.id.female -> {
                    binding.female.isChecked = true
                }
            }
        }

        val radio: RadioButton = binding.root.findViewById(genderbtn.checkedRadioButtonId)
        update.name = n
        update.email = e
        update.phone = num
        update.cnic = c
        update.gender=radio.text.toString()
        update.country = country
        update.province = province
        update.city = city
        update.office_location = location
        update.general__id = general
        update.high_court_id = high_cout
        update.supreme_court_id = supreme
        update.description = des
        update.experince = experience
        update.fee = fee
        update.first_specilize = first
        update.second_specilize = second
        update.third_specilize = third
        update.fourth_specilize=fourth
        update.llb = study1
        update.llm = study2
        update.bar_of_law = study3
        update.phd=study4
        val database = FirebaseDatabase.getInstance()
        val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        val user_img=FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        val myRef = database.getReference("Lawyer").child(user)
        myRef.setValue(update)
    }

    private fun back_data() {
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array != null) {
            move_back(array)
        } else if (array1 != null) {
            move_back(array1)
        }
    }
    private fun move_back(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val sixthFragment=SixthFragment()
        sixthFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,sixthFragment)?.addToBackStack(null)
            ?.commit()
    }
    fun alternative(array:java.util.ArrayList<String>) {
        val n = binding.itemName.text.toString()
        val e = binding.itemEmail.text.toString()
        val num = binding.itemNumber.text.toString()
        val c = binding.itemCnic.text.toString()
        val country = binding.itemCountry.text.toString()
        val province = binding.itemProvince.text.toString()
        val city = binding.itemCity.text.toString()
        val location = binding.itemLocation.text.toString()
        val genderbtn: RadioGroup = binding.radioGroup
        val general = binding.itemGeneral.text.toString()
        val high_cout = binding.itemHigh.text.toString()
        val supreme = binding.itemSupreme.text.toString()
        val des = binding.itemDesc.text.toString()
        val experience = binding.itemExperience.text.toString()
        val fee = binding.itemFee.text.toString()
        val first = binding.itemFirst.text.toString()
        val second = binding.itemSecond.text.toString()
        val third = binding.itemThird.text.toString()
        val fourth=binding.itemForth.text.toString()
        val study1 = binding.itemFirststudy.text.toString()
        val study2 = binding.itemSecondstudy.text.toString()
        val study3 = binding.itemThirdstudy.text.toString()
        val study4=binding.itemForthstudy.text.toString()
        genderbtn.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.male -> {
                    binding.male.isChecked = true
                }

                R.id.female -> {
                    binding.female.isChecked = true
                }
            }
        }

        val radio: RadioButton = binding.root.findViewById(genderbtn.checkedRadioButtonId)
        array[0] = n
        array[1] = e
        array[2] = num
        array[3] = c
        array[4]=radio.text.toString()
        array[5] = country
        array[6] = province
        array[7] = city
        array[8] = location
        array[9] = general
        array[10] = high_cout
        array[11] = supreme
        array[12] = des
        array[13] = experience
        array[14] = fee
        array[15] = first
        array[16] = second
        array[17] = third
        array[18]=fourth
        array[19] = study1
        array[20] = study2
        array[21] = study3
        array[22]=study4
        val database = FirebaseDatabase.getInstance()
        val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        val user_img=FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        val myRef = database.getReference("Lawyer").child(user)
    /*    if (array[20].isEmpty()){
            myRef.setValue(Requirement(
                img =user_img,
                name = array[0], email = array[1], phone = array[2],
                cnic = array[3], gender = array[4],
                country = array[5], province = array[6], city = array[7], office_location = array[8], general__id = array[9], high_court_id = array[10], supreme_court_id = array[11], description = array[12], experince = array[13], fee = array[14], first_specilize = array[15], second_specilize = array[16], third_specilize = array[17], fourth_specilize = array[18], llb = array[19], llm = null, bar_of_law = array[21], phd = array[22], id = user_id,
                layer = true))
        }else if (array[21].isEmpty()){
            myRef.setValue(Requirement(
                img =user_img,
                name = array[0], email = array[1], phone = array[2],
                cnic = array[3], gender = array[4],
                country = array[5], province = array[6], city = array[7], office_location = array[8], general__id = array[9], high_court_id = array[10], supreme_court_id = array[11], description = array[12], experince = array[13], fee = array[14], first_specilize = array[15], second_specilize = array[16], third_specilize = array[17], fourth_specilize = array[18], llb = array[19], llm = array[20], bar_of_law = null, phd = array[22], id = user_id,
                layer = true))
        }else if (array[22].isEmpty())
        {
            myRef.setValue(Requirement(
                img =user_img,
                name = array[0], email = array[1], phone = array[2],
                cnic = array[3], gender = array[4],
                country = array[5], province = array[6], city = array[7], office_location = array[8], general__id = array[9], high_court_id = array[10], supreme_court_id = array[11], description = array[12], experince = array[13], fee = array[14], first_specilize = array[15], second_specilize = array[16], third_specilize = array[17], fourth_specilize = array[18], llb = array[19], llm = array[20], bar_of_law = array[21], phd = null, id = user_id,
                layer = true))
        }
        else
        {*/
            myRef.setValue(Requirement(
                img =user_img,
                name = array[0], email = array[1], phone = array[2],
                cnic = array[3], gender = array[4],
                country = array[5], province = array[6], city = array[7], office_location = array[8], general__id = array[9], high_court_id = array[10], supreme_court_id = array[11], description = array[12], experince = array[13], fee = array[14], first_specilize = array[15], second_specilize = array[16], third_specilize = array[17], fourth_specilize = array[18], llb = array[19], llm = array[20], bar_of_law = array[21], phd = array[22], id = user_id,
                layer = true))
            val msg="Hi $n,\n We would like to express our sincere appreciation for registering as a lawyer with LawAdvat. We are delighted to welcome you to our platform, connecting legal professionals with clients in need of legal expertise.\n" +
                    "\n" +
                    "By joining LawAdvat, you have taken a significant step towards expanding your professional reach and providing your legal services to a wider audience. Our platform offers a seamless and user-friendly experience for both lawyers and clients, facilitating efficient communication and fostering meaningful connections.\n" +
                    "\n" +
                    "As a registered lawyer on LawAdvat, you now have the opportunity to showcase your expertise, build your online presence, and engage with potential clients who are actively seeking legal assistance. This presents a remarkable avenue for you to expand your client base and make a positive impact in the legal community.\n" +
                    "\n" +
                    "We are committed to supporting you throughout your journey with LawAdvat. Should you have any questions, encounter any challenges, or require any assistance, our dedicated support team is always here to help. We strive to continuously enhance our platform and provide you with the tools and resources needed to succeed.\n" +
                    "\n" +
                    "Once again, we extend our gratitude for choosing LawAdvat as your platform of choice. We are confident that our partnership will be mutually beneficial, and we look forward to witnessing your achievements as a valued member of our legal community.\n" +
                    "\n" +
                    "If you have any further inquiries or suggestions, please feel free to reach out to us. We value your feedback and are committed to delivering the best possible experience for you and our esteemed clients."

            send_Email(e,
                msg
            )
       // }
         val editor =
            requireActivity().getSharedPreferences("MY_PREFS_NAME", AppCompatActivity.MODE_PRIVATE).edit()
        editor.putBoolean("lawyer", true)
        editor.apply()
        requireActivity().finish()
        //Snackbar.make(requireView(), "Your data successfully saved", Snackbar.LENGTH_SHORT).show()

    }
    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        val user_img=FirebaseAuth.getInstance()
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val update=arguments?.getParcelable<Requirement>("update")
        if (update!=null)
        {
            requireActivity().actionBar?.title="Update Profile"
            binding.topHeading.text = "Update your Information"
            binding.backBtn.isVisible=false
            Glide.with(this)
                .load(user_img.currentUser?.photoUrl)
                .into(binding.profile)
            binding.itemName.setText(update.name)
            binding.itemEmail.setText(update.email)
            binding.itemNumber.setText(update.phone)
            binding.itemCnic.setText(update.cnic)
            if (update.gender?.equals("Male") == true)
            {
                binding.male.isChecked=true
            }
            else
            {
                binding.female.isChecked=true
            }
            binding.itemCountry.setText(update.country)
            binding.itemProvince.setText(update.province)
            binding.itemCity.setText(update.city)
            binding.itemLocation.setText(update.office_location)
            binding.itemGeneral.setText(update.general__id)
            binding.itemHigh.setText(update.high_court_id)
            binding.itemSupreme.setText(update.supreme_court_id)
            binding.itemDesc.setText(update.description)
            binding.itemExperience.setText(update.experince)
            binding.itemFee.setText(update.fee)
            binding.itemFirst.setText(update.first_specilize)
            binding.itemSecond.setText(update.second_specilize)
            binding.itemThird.setText(update.third_specilize)
            binding.itemForth.setText(update.fourth_specilize)
            binding.itemFirststudy.setText(update.llb)
            binding.itemSecondstudy.setText(update.llm)
            binding.itemThirdstudy.setText(update.bar_of_law)
            binding.itemForthstudy.setText(update.phd)

        }else if (array1!=null)
        {
            Glide.with(this)
                .load(user_img.currentUser?.photoUrl)
                .into(binding.profile)
            binding.itemName.setText(array1[0])
            binding.itemEmail.setText(array1[1])
            binding.itemNumber.setText(array1[2])
            binding.itemCnic.setText(array1 [3])
            if (array1?.get(4)?.equals("Male") == true)
            {
                binding.male.isChecked=true
            }
            else
            {
                binding.female.isChecked=true
            }
            binding.itemCountry.setText(array1[5])
            binding.itemProvince.setText(array1[6])
            binding.itemCity.setText(array1[7])
            binding.itemLocation.setText(array1[8])
            binding.itemGeneral.setText(array1[9])
            binding.itemHigh.setText(array1[10])
            binding.itemSupreme.setText(array1[11])
            binding.itemDesc.setText(array1[12])
            binding.itemExperience.setText(array1[13])
            binding.itemFee.setText(array1[14])
            binding.itemFirst.setText(array1[15])
            binding.itemSecond.setText(array1[16])
            binding.itemThird.setText(array1[17])
            binding.itemForth.setText(array1[18])
            binding.itemFirststudy.setText(array1[19])
            binding.itemSecondstudy.setText(array1[20])
            binding.itemThirdstudy.setText(array1[21])
            binding.itemForthstudy.setText(array1[22])
        }
    }
    private fun send_Email(
        Receiver: String,
        Message: String
    ) {
        Thread {
            try {

                val sm = SendMailTask(
                    context,
                    "abdulrehman4241566@gmail.com",
                    Receiver,
                    "Thank You for Registering with LawAdvat",
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
}