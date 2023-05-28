package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        binding.backBtn.setOnClickListener {
            if (array!=null) {
                val bundle = Bundle()
                bundle.putSerializable("key1", array)
                val firstFragment = FirstFragment()
                firstFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, firstFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            } else {
                val bundle = Bundle()
                bundle.putSerializable("key1", array1)
                val firstFragment = FirstFragment()
                firstFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, firstFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }

        }
        binding.saveBtn.setOnClickListener {
            forwar_data()
        }
        return binding.root
    }

    private fun forwar_data() {
        val country = binding.itemCountry.text.toString().trim()
        val province = binding.itemProvince.text.toString().trim()
        val city = binding.itemCity.text.toString().trim()
        val location = binding.itemLocation.text.toString().trim()
        if (province.isEmpty())
        {
            binding.layoutProvince.error="please fill this field"
        }
        else if (city.isEmpty()) {
            binding.layoutCity.error = "Please fill this field"
        } else if (location.isEmpty()) {
            binding.layoutLocation.error = "Please fill this field"
        } else {
            //val data:ArrayList<String> = arguments?.getStringArrayList("key") as ArrayList<String>
            val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
            val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
            if (array!=null) {
                array.add(country)
                array.add(province)
                array.add(city)
                array.add(location)
                /*array[5]=country
                array[6]=province
                array[7]=city
                array[8]=location*/

                val bundle = Bundle()
                bundle.putSerializable("key", array)
                val thirdFragment = ThirdFragment()
                thirdFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, thirdFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
                 else if (array1!=null) {
                    array1[5] = country.toString()
                    array1[6] = province
                    array1[7] = city
                    array1[8] = location
                    val bundle = Bundle()
                    bundle.putSerializable("key", array1)
                    val thirdFragment = ThirdFragment()
                    thirdFragment.arguments = bundle
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.nav_host_fragment_activity_main, thirdFragment)
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
        }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        if (array1!=null)
        {
            in_it(array1)
        }
    }

    private fun in_it(array1: ArrayList<String>) {
        binding.itemCountry.setText(array1[5])
        binding.itemProvince.setText(array1[6])
        binding.itemCity.setText(array1[7])
        binding.itemLocation.setText(array1[8])
    }
}