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
                if (array?.size!! > 19) {
                    alternative(array)
                } else if (array.size == 18) {
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
    fun move_on(array: java.util.ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val seventhFragmen = SeventhFragmen()
        seventhFragmen.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, seventhFragmen)
            ?.addToBackStack(null)
            ?.commit()
    }
    fun alternative(array:java.util.ArrayList<String>) {
        val n = binding.itemFirst1.text.toString()
        val e = binding.itemSecond2.text.toString()
        val num = binding.itemThird3.text.toString()
        val c=binding.itemForth4.text.toString()
        array[19] = n
        array[20] = e
        array[21] = num
        array[22]=c
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val seventhFragmen = SeventhFragmen()
        seventhFragmen.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, seventhFragmen)
            ?.addToBackStack(null)
            ?.commit()
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

        val high = binding.itemFirst1.text.toString().trim()
        val medium = binding.itemSecond2.text.toString().trim()
        val enter_mediumee = binding.itemThird3.text.toString().trim()
        val low = binding.itemForth4.text.toString().trim()
        if (high.isEmpty()) {
            binding.layoutFirst1.error = "Please fill this field"
        }else if (medium.isEmpty() && enter_mediumee.isEmpty()&& low.isEmpty()) {
            array.add(high)
            array.add("--")
            array.add("--")
            array.add("--")
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val seventhFragment = SeventhFragmen()
            seventhFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, seventhFragment)
                ?.addToBackStack(null)
                ?.commit()
        }else if (medium.isEmpty()){
            array.add(high)
            array.add("--")
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
        } else if (enter_mediumee.isEmpty()){
            array.add(high)
            array.add(medium)
            array.add("--")
            array.add(low)
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val seventhFragment = SeventhFragmen()
            seventhFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, seventhFragment)
                ?.addToBackStack(null)
                ?.commit()
        }else if(low.isEmpty()){
            array.add(high)
            array.add(medium)
            array.add(enter_mediumee)
            array.add("--")
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val seventhFragment = SeventhFragmen()
            seventhFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, seventhFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
        /*else if(medium.isEmpty()){
            array.add(high)
            array.add("--")
            array.add(enter_mediumee)
            array.add(low)
        }else if(enter_mediumee.isEmpty()){
            array.add(high)
            array.add(medium)
            array.add("--")
            array.add(low)
        }else if(low.isEmpty()){
            array.add(high)
            array.add(medium)
            array.add(enter_mediumee)
            array.add("--")
        }*/
//        else {
//            array.add(high)
//            array.add(medium)
//            array.add(enter_mediumee)
//            array.add(low)
//            val bundle = Bundle()
//            bundle.putSerializable("key", array)
//            val seventhFragment = SeventhFragmen()
//            seventhFragment.arguments = bundle
//            fragmentManager?.beginTransaction()
//                ?.replace(R.id.nav_host_fragment_activity_main, seventhFragment)
//                ?.addToBackStack(null)
//                ?.commit()
//        }

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
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
        {
            in_it(array1)
        } else if (array?.size!! > 19)
        {
            in_it(array)
        }
       
    }

    private fun in_it(array1: ArrayList<String>) {
        binding.itemFirst1.setText(array1?.get(19).toString())
        binding.itemSecond2.setText(array1?.get(20).toString())
        binding.itemThird3.setText(array1?.get(21).toString())
        binding.itemForth4.setText(array1?.get(22).toString())
    }

}