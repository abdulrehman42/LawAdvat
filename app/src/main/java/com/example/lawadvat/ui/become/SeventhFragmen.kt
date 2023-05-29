package com.example.lawadvat.ui.become

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSeventhBinding
import com.example.lawadvat.ui.become.entity.Requirement
import com.example.lawadvat.ui.home.HomeFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SeventhFragmen : Fragment() {
    lateinit var binding: FragmentSeventhBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSeventhBinding.inflate(layoutInflater)
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        binding.backBtn.setOnClickListener {
            back_data()
        }
        binding.saveBtn.setOnClickListener {
            if (array != null) {
                alternative(array)
            }
        }
        return binding.root
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
        myRef.setValue(Requirement(img =user_img,name = array[0], email = array[1], phone = array[2],cnic = array[3], gender = array[4],
        general__id = array[5],high_court_id = array[6],supreme_court_id = array[7], country = array[8], province = array[9], city = array[10], office_location = array[11], description = array[12], experince = array[13], fee = array[14], first_specilize = array[15], second_specilize = array[16], third_specilize = array[17], fourth_specilize = array[18], llb = array[19], llm = array[20], bar_of_law = array[21], phd = array[22]))
        /*val fragmentManager:FragmentManager  = requireActivity().supportFragmentManager; // If using AppCompatActivity
        val transaction:FragmentTransaction  = fragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.replace(R.id.nav_host_fragment_activity_main, HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit()*/
        /*fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, HomeFragment())
            ?.addToBackStack(null)
            ?.commit()*/
        getActivity()?.fragmentManager?.popBackStack();
        Snackbar.make(requireView(), "Your data successfully saved", Snackbar.LENGTH_SHORT).show()



    }
    override fun onStart() {
        super.onStart()
        val user_img=FirebaseAuth.getInstance()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
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
            binding.itemGeneral.setText(array1[9].toString())
            binding.itemHigh.setText(array1[10].toString())
            binding.itemSupreme.setText(array1[11].toString())
            binding.itemDesc.setText(array1.get(12).toString())
            binding.itemExperience.setText(array1.get(13).toString())
            binding.itemFee.setText(array1.get(14).toString())
            binding.itemFirst.setText(array1.get(15).toString())
            binding.itemSecond.setText(array1.get(16).toString())
            binding.itemThird.setText(array1.get(17).toString())
            binding.itemForth.setText(array1.get(18).toString())
            binding.itemFirststudy.setText(array1.get(19).toString())
            binding.itemSecondstudy.setText(array1.get(20).toString())
            binding.itemThirdstudy.setText(array1.get(21).toString())
            binding.itemForthstudy.setText(array1.get(22).toString())
        }
    }
}