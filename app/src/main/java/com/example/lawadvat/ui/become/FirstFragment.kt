package com.example.lawadvat.ui.become

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar


class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    val list = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        binding.saveBtn.setOnClickListener {
            in_it()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (arguments?.getSerializable("key1") != null) {
            initalize(array)
        }
    }

    private fun initalize(array: java.util.ArrayList<String>?) {
        binding.itemName.setText(array?.get(0).toString())
        binding.itemEmail.setText(array?.get(1).toString())
        binding.itemNumber.setText(array?.get(2).toString())
        binding.itemCnic.setText(array?.get(3).toString())
        if (array?.get(4)?.equals("Male") == true)
        {
            binding.male.isChecked=true
        }
        else
        {
            binding.female.isChecked=true
        }
    }

    private fun in_it() {
        val name = binding.itemName.text.toString().trim()
        val email = binding.itemEmail.text.toString().trim()
        val phone = binding.itemNumber.text.toString().trim()
        val cnic = binding.itemCnic.text.toString().trim()
        val genderbtn: RadioGroup = binding.radioGroup
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
        //binding.male.isChecked=true
        val gender = binding.radioGroup.checkedRadioButtonId
        if (name.isEmpty()) {
            binding.layoutName.error = "Please fill this field"
        } else if (email.isEmpty()) {
            binding.layoutEmail.error = "Please fill this field"
        } else if (phone.isEmpty()) {
            binding.layoutNumber.error = "Please fill this field"
        }
        else if (phone.length> 11)
        {
            binding.layoutNumber.error = "Invalid Phone number"

        }else if (phone.length< 11)
        {
            binding.layoutNumber.error = "Invalid Phone number"

        }
        else if (cnic.isEmpty()) {
            binding.cnicLayout.error = "Please fill this field"
        }else if (cnic.length > 13)
        {
            binding.cnicLayout.error = "Invalid CNIC Number"
        }
        else if (cnic.length<13)
        {
            binding.cnicLayout.error = "Invalid CNIC Number"
        }
        else if (genderbtn.checkedRadioButtonId==-1)
        {
            Snackbar.make(requireView(),"Your Gender", Snackbar.LENGTH_SHORT).show()

        }
        else {
            val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
            if (array1!=null)
            {
                val radio: RadioButton = binding.root.findViewById(genderbtn.checkedRadioButtonId)
                val bundle = Bundle()
                bundle.putSerializable("key", list)
                val secondFragment = SecondFragment()
                secondFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, secondFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
            }
            val radio: RadioButton = binding.root.findViewById(genderbtn.checkedRadioButtonId)
            list.add(name)
            list.add(email)
            list.add(phone)
            list.add(cnic)
            list.add(radio.text.toString())
            val bundle = Bundle()
            bundle.putSerializable("key", list)
            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, secondFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
