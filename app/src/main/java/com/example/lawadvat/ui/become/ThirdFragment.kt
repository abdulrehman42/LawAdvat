package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    lateinit var binding: FragmentThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentThirdBinding.inflate(layoutInflater)
        binding.saveBtn.setOnClickListener {
            forward_data()
        }
        binding.backBtn.setOnClickListener {
            back_data()
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
        val secondFragment=SecondFragment()
        secondFragment.arguments=bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main,secondFragment)?.addToBackStack(null)
            ?.commit()
    }

    private fun forward_data() {
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val general = binding.itemGeneral.text.toString().trim()
        val high = binding.itemHigh.text.toString().trim()
        val supreme = binding.itemSupreme.text.toString().trim()
        if (general.isEmpty()) {
            binding.layoutGeneral.error = "Please fill this field"
        } else if (high.isEmpty()) {
            binding.layoutHigh.error = "Please fill this field"
        } else if (supreme.isEmpty()) {
            binding.layoutSupreme.error = "Please fill this field"
        } else {
            if (array != null) {
                move_on(array,general,high,supreme)

            } else if (array1 != null) {
                move_on(array1,general,high,supreme)
            }
        }
    }

    private fun move_on(array: ArrayList<String>, general: String, high: String, supreme: String) {
        array.add(general)
        array.add(high)
        array.add(supreme)
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val forthFragment = ForthFragment()
        forthFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, forthFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array1!=null)
        {
            binding.itemGeneral.setText(array1?.get(9).toString())
            binding.itemHigh.setText(array1?.get(10).toString())
            binding.itemSupreme.setText(array1?.get(11).toString())
        }
    }
}