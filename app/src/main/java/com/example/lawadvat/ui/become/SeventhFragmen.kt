package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSecondBinding
import com.example.lawadvat.databinding.FragmentSeventhBinding


class SeventhFragmen : Fragment() {
    lateinit var binding: FragmentSeventhBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSeventhBinding.inflate(layoutInflater)
        binding.backBtn.setOnClickListener {
            back_data()
        }
        binding.saveBtn.setOnClickListener {
            //forward_data()
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
    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
        {
            binding.itemName.setText(array1[0].toString())
            binding.itemEmail.setText(array1[1].toString())
            binding.itemNumber.setText(array1[2].toString())
            binding.itemCnic.setText(array1 [3].toString())
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
            binding.itemFirststudy.setText(array1?.get(19).toString())
            binding.itemSecondstudy.setText(array1?.get(20).toString())
            binding.itemThirdstudy.setText(array1?.get(21).toString())
            binding.itemForthstudy.setText(array1?.get(22).toString())
        }
    }
}