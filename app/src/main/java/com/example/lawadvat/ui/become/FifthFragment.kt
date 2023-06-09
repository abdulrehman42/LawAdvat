package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentFifthBinding

class FifthFragment : Fragment() {
   lateinit var binding:FragmentFifthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFifthBinding.inflate(layoutInflater)
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        binding.backBtn.setOnClickListener {
            if (array!=null) {
                move_back(array)
            }
            else if(array1!=null){
                move_back(array1)
            }
        }
        binding.saveBtn.setOnClickListener {
            if (array!=null) {
                if (array?.size!! > 15) {
                    alternative(array)
                } else if (array.size == 14) {
                    forward_data(array)
                }
                else  {
                    forward_data(array)
                }
            }
            else if (array1 != null) {
                alternative(array1)
            }
        }
        return binding.root
    }
    /*private fun back_data() {
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array != null) {
            move_back(array)
        } else if (array1 != null) {
            move_back(array1)
        }
    }*/
    private fun forward_data(array: java.util.ArrayList<String>) {
        val high = binding.itemFirst.text.toString().trim()
        val medium = binding.itemSecond.text.toString().trim()
        val enter_mediumee = binding.itemThird.text.toString().trim()
        val low = binding.itemForth.text.toString().trim()
        if (high.isEmpty()) {
            binding.layoutFirst.error = "Please fill this field"
        } else if (medium.isEmpty()) {
            binding.layoutSecond.error = "Please fill this field"
        } else if (enter_mediumee.isEmpty()) {
            binding.layoutThird.error = "Please fill this field"
        }else if (low.isEmpty()) {
            binding.layoutForth.error = "Please fill this field"
        } else {
            array.add(high)
            array.add(medium)
            array.add(enter_mediumee)
            array.add(low)
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val sixthFragment = SixthFragment()
            sixthFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, sixthFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun move_back(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val forthFragment=ForthFragment()
        forthFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,forthFragment)?.addToBackStack(null)
            ?.commit()
    }
    fun alternative(array:java.util.ArrayList<String>) {
        /*val items = listOf("Murder", "Assault", "Theft", "Robbery", "Offenses","Fraud","Cybercrime","Kidnapping","Personal Injury","Property Disputes","Contract Disputes","Family Law","Divorce","Child Custody","Child Suppor","Adoption","Domestic Violence","Immigration Appeals","Regulatory Compliance","Tax Appeals","Government Agency Disputes","Civil Rights Violations","Freedom of Speech","Employment Law","Pollution","Climate Change","Environmental Cleanup")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)
        binding.itemFirst.setadpter(adapter)*/
        val n = binding.itemFirst.text.toString()
        val e = binding.itemSecond.text.toString()
        val num = binding.itemThird.text.toString()
        val c=binding.itemForth.text.toString()
        array[15] = n
        array[16] = e
        array[17] = num
        array[18]=c
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val sixthFragment = SixthFragment()
        sixthFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, sixthFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    fun move_on(array: java.util.ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val sixthFragment = SixthFragment()
        sixthFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, sixthFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
        {
            in_it(array1)
        } else if (array?.size!! > 15)
        {
            in_it(array)
        }
    }

    private fun in_it(array1: ArrayList<String>) {
        binding.itemFirst.setText(array1?.get(15).toString())
        binding.itemSecond.setText(array1?.get(16).toString())
        binding.itemThird.setText(array1?.get(17).toString())
        binding.itemForth.setText(array1?.get(18).toString())
    }
}