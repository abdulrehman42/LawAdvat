package com.example.lawadvat.ui.appintment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lawadvat.R
import com.example.lawadvat.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        activity?.window?.statusBarColor = requireActivity().getColor(R.color.white);
        val dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        return binding.root
    }

}