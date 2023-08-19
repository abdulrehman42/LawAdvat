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
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        binding.saveBtn.setOnClickListener {

            if (array!=null) {
                if (array.size > 9) {
                    alternative(array)
                } else if (array.size == 8) {
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
        binding.backBtn.setOnClickListener {

            if (array!=null) {
                move_back(array)
            }
            else if (array1!=null){
                move_back(array1)
            }
        }
        return binding.root
    }
    fun move_on(array: java.util.ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val forthFragment = ForthFragment()
        forthFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, forthFragment)
            ?.addToBackStack(null)
            ?.commit()
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

    private fun forward_data(array: java.util.ArrayList<String>) {
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val general = binding.itemGeneral.text.toString().trim()
        val high = binding.itemHigh.text.toString().trim()
        val supreme = binding.itemSupreme.text.toString().trim()
        if (general.isEmpty()) {
            binding.layoutGeneral.error = "Please fill this field"
        }else if (general.length > 6){
            binding.layoutGeneral.error = "Invalid general license"
        }else if (general.length < 6) {
            binding.layoutGeneral.error = "Invalid general license"
        }else if (high.isEmpty() && supreme.isEmpty())
        {
            array.add(general)
            array.add("--")
            array.add("--")
            val bundle = Bundle()
            bundle.putSerializable("key", array)
            val forthFragment = ForthFragment()
            forthFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, forthFragment)
                ?.addToBackStack(null)
                ?.commit()
        }else if (high.isEmpty()){
            if (supreme.length > 6){
                binding.layoutSupreme.error = "Invalid supreme court license"
            }else if (supreme.length < 6) {
                binding.layoutSupreme.error = "Invalid supreme court license"
            }else {
                array.add(general)
                array.add("--")
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
        }else if ( supreme.isEmpty()){
            if (high.length > 6){
                binding.layoutHigh.error = "Invalid high court license"
            }else if (high.length < 6) {
                binding.layoutHigh.error = "Invalid high court license"
            }else {
                array.add(general)
                array.add(high)
                array.add("--")
                val bundle = Bundle()
                bundle.putSerializable("key", array)
                val forthFragment = ForthFragment()
                forthFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, forthFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
       /* else if (high.isEmpty()) {
            binding.layoutHigh.error = "Please fill this field"
        }else if (high.length > 6){
            binding.layoutHigh.error = "Invalid Highcourt license"
        }else if (high.length < 6) {
            binding.layoutHigh.error = "Invalid Highcourt license"
        }
        else if (supreme.isEmpty()) {
            binding.layoutSupreme.error = "Please fill this field"
        } else if (supreme.length > 6){
            binding.layoutSupreme.error = "Invalid supreme court license"
        }else if (supreme.length < 6) {
            binding.layoutSupreme.error = "Invalid supreme court license"
        }*/
        else {
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
    }
    fun alternative(array:java.util.ArrayList<String>) {
        val n = binding.itemGeneral.text.toString()
        val e = binding.itemHigh.text.toString()
        val num = binding.itemSupreme.text.toString()
        array[9] = n
        array[10] = e
        array[11] = num
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val forthfragment = ForthFragment()
        forthfragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, forthfragment)
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
        } else if (array?.size!! > 9)
        {
            in_it(array)
        }

    }

    private fun in_it(array1: ArrayList<String>) {
        binding.itemGeneral.setText(array1[9].toString())
        binding.itemHigh.setText(array1[10].toString())
        binding.itemSupreme.setText(array1[11].toString())
    }
}