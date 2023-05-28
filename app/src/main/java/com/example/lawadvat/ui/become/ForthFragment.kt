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
            if (array != null) {
                move_on(array,des,exp,fee)

            } else if (array1 != null) {
                move_on(array1,des,exp,fee)
            }
        }
    }
    private fun move_on(array: ArrayList<String>, des: String, exp: String, fee: String) {
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
    }
}