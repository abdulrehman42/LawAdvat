package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentForthBinding


class ForthFragment : Fragment() {
    lateinit var binding:FragmentForthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentForthBinding.inflate(layoutInflater)
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
            if (array?.size!! > 12) {
                alternative(array)
            }else if (array.size <12)
            {
                forward_data(array)
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
    fun alternative(array:java.util.ArrayList<String>) {
        val n = binding.itemDesc.text.toString()
        val e = binding.itemExperience.text.toString()
        val num = binding.itemFee.text.toString()
        array[12] = n
        array[13] = e
        array[14] = num
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val fifthFragment = FifthFragment()
        fifthFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, fifthFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    private fun forward_data(array: java.util.ArrayList<String>) {
        val des = binding.itemDesc.text.toString().trim()
        val exp = binding.itemExperience.text.toString().trim()
        val fee = binding.itemFee.text.toString().trim()
        if (des.isEmpty()) {
            binding.layoutDes.error = "Please fill this field"
        } else if (exp.isEmpty()) {
            binding.layoutExperice.error = "Please fill this field"
        } else if (fee.isEmpty()) {
            binding.layoutFee.error = "Please fill this field"
        } else {
            array.add(des)
            array.add(exp)
            array.add(fee)
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val fifthFragment = FifthFragment()
            fifthFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, fifthFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun move_back(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val thirdFragment=ThirdFragment()
        thirdFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,thirdFragment)?.addToBackStack(null)
            ?.commit()
    }
    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
        {
            in_it(array1)
        } else if (array?.size!! > 12)
        {
            in_it(array)
        }
    }
    private fun in_it(array1: ArrayList<String>) {
        binding.itemDesc.setText(array1[12])
        binding.itemExperience.setText(array1[13])
        binding.itemFee.setText(array1[14])
    }
}