package com.example.lawadvat.ui.become

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentSecondBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.ArrayList
import java.util.Locale

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        binding.backBtn.setOnClickListener {

            if (array != null) {
                move_back(array)
            } else if (array1 != null) {
                move_back(array1)
            }

        }

        binding.saveBtn.setOnClickListener {

            if (array != null) {
                if (array?.size!! > 6) {
                    alternative(array)
                } else if (array.size == 5) {
                    forwar_data(array)
                } else {
                    move_on(array)
                }
            } else if (array1 != null) {
                alternative(array1)
            }
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.layoutLocation.setEndIconOnClickListener {
                getLocation()
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

    fun alternative(array: ArrayList<String>) {
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

    private fun move_back(array: java.util.ArrayList<String>) {
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
        if (country.isEmpty()) {
            binding.layoutCountry.error = "please fill this field"
        } else if (province.isEmpty())
        {
            binding.layoutProvince.error = "please fill this field"

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
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val array1: java.util.ArrayList<String>? = arguments?.getStringArrayList("key1")
        val array: java.util.ArrayList<String>? = arguments?.getStringArrayList("key")
        if (array1 != null) {
            in_it(array1)
        } else if (array?.size!! > 6) {
            in_it(array)
        }
    }

    private fun in_it(array1: java.util.ArrayList<String>) {
        binding.itemCountry.setText(array1[5]).toString()
        binding.itemProvince.setText(array1[6]).toString()
        binding.itemCity.setText(array1[7]).toString()
        binding.itemLocation.setText(array1[8]).toString()

    }

    @SuppressLint("SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ){
                    return
                }
                    /*{
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }*/
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
/*
                    Handler(Looper.getMainLooper()).postDelayed({
                        //val mainIntent = Intent(this, Menu::class.java)
                        //startActivity(mainIntent)
                        finish()
                    }, task.isComplete as Long)
*/
                    if (location != null) {
                        val geocoder = Geocoder(requireContext(), Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                        binding.apply {
                            itemCountry.setText( list[0].countryName.toString())
                            itemProvince.setText(list[0].adminArea.toString())
                            itemCity.setText("\n${list[0].locality}")
                            itemLocation.setText("\n${list[0].getAddressLine(0).trimStart()}")
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}