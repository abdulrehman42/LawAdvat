package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentFifthBinding

class FifthFragment : Fragment() {
   lateinit var binding:FragmentFifthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFifthBinding.inflate(layoutInflater)
        binding.backBtn.setOnClickListener {
            back_data()
        }
        binding.saveBtn.setOnClickListener {
            forward_data()
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
    private fun forward_data() {
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
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
            if (array != null) {
                move_on(array,high,medium,enter_mediumee,low)
            } else if (array1 != null) {
                move_on(array1,high,medium,enter_mediumee,low)
            }
        }
    }
    private fun move_on(
        array: ArrayList<String>,
        high: String,
        medium: String,
        enter_mediumee: String,
        low: String
    ) {
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
    private fun move_back(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val forthFragment=ForthFragment()
        forthFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,forthFragment)?.addToBackStack(null)
            ?.commit()
    }
    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array1!=null)
        {
            binding.itemFirst.setText(array1?.get(15).toString())
            binding.itemSecond.setText(array1?.get(16).toString())
            binding.itemThird.setText(array1?.get(17).toString())
            binding.itemForth.setText(array1?.get(18).toString())
        }
    }
}