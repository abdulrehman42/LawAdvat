package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSixthBinding


class SixthFragment : Fragment() {
    lateinit var binding:FragmentSixthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSixthBinding.inflate(layoutInflater)
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
        val high = binding.itemFirst1.text.toString().trim()
        val medium = binding.itemSecond2.text.toString().trim()
        val enter_mediumee = binding.itemThird3.text.toString().trim()
        val low = binding.itemForth4.text.toString().trim()
        if (high.isEmpty()) {
            binding.layoutFirst1.error = "Please fill this field"
        } else if (medium.isEmpty()) {
            binding.layoutSecond2.error = "Please fill this field"
        } else if (enter_mediumee.isEmpty()) {
            binding.layoutThird3.error = "Please fill this field"
        }else if (low.isEmpty()) {
            binding.layoutForth4.error = "Please fill this field"
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
        val seventhFragment = SeventhFragmen()
        seventhFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, seventhFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    private fun move_back(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val fifthFragment=FifthFragment()
        fifthFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,fifthFragment)?.addToBackStack(null)
            ?.commit()
    }
    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array1!=null)
        {
            binding.itemFirst1.setText(array1?.get(19).toString())
            binding.itemSecond2.setText(array1?.get(20).toString())
            binding.itemThird3.setText(array1?.get(21).toString())
            binding.itemForth4.setText(array1?.get(22).toString())
        }
    }

}