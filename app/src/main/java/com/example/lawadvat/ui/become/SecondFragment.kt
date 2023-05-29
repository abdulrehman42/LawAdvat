package com.example.lawadvat.ui.become

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSecondBinding
import java.util.ArrayList

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        binding.backBtn.setOnClickListener {

            if (array!=null) {
                move_back(array)
            }
            else if (array1!=null){
              move_back(array1)
            }

        }
        binding.saveBtn.setOnClickListener {

            if (array!=null) {
                if (array?.size!! > 6) {
                    alternative(array)
                } else if (array.size == 5) {
                    forwar_data(array)
                }
                else  {
                    move_on(array)
                }
            }
            else if (array1 != null) {
                    alternative(array1)
            }
        }
        return binding.root
    }

    private fun move_on(array: ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val thirdFragment = ThirdFragment()
        thirdFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, thirdFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    fun alternative(array:ArrayList<String>) {
        val n = binding.itemCountry.text.toString()
        val e = binding.itemProvince.text.toString()
        val num = binding.itemCity.text.toString()
        val c = binding.itemLocation.text.toString()
        array[5] = n
        array[6] = e
        array[7] = num
        array[8] = c
        val bundle = Bundle()
        bundle.putSerializable("key", array)
        val thirdFragment = ThirdFragment()
        thirdFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, thirdFragment)
            ?.addToBackStack(null)
            ?.commit()
        }
    private fun move_back(array:java.util.ArrayList<String>) {
        val bundle = Bundle()
        bundle.putSerializable("key1", array)
        val firstFragment = FirstFragment()
        firstFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment_activity_main, firstFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    private fun forwar_data(array: ArrayList<String>) {
        val country = binding.itemCountry.text.toString()
        val province = binding.itemProvince.text.toString()
        val city = binding.itemCity.text.toString()
        val location = binding.itemLocation.text.toString()
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
            array.add(country)
            array.add(province)
            array.add(city)
            array.add(location)
                val bundle = Bundle()
                bundle.putSerializable("key", array)
                val thirdFragment = ThirdFragment()
                thirdFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment_activity_main, thirdFragment)
                    ?.addToBackStack(null)
                    ?.commit()

                /* else if (array1!=null) {
                    array1[5] = country
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
                }*/
            }
        }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1!=null)
        {
            in_it(array1)
        } else if (array?.size!! > 6)
        {
            in_it(array)
        }
    }

    private fun in_it(array1:java.util.ArrayList<String>) {
            binding.itemCountry.setText(array1[5]).toString()
            binding.itemProvince.setText(array1[6]).toString()
            binding.itemCity.setText(array1[7]).toString()
            binding.itemLocation.setText(array1[8]).toString()

    }
}