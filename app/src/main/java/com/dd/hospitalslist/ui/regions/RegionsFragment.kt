package com.dd.hospitalslist.ui.regions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dd.hospitalslist.HospitalApplication
import com.dd.hospitalslist.databinding.RegionsFragmentBinding

class RegionsFragment : Fragment() {

    private lateinit var binding: RegionsFragmentBinding
    private val viewModel: RegionsViewModel by viewModels {
        RegionsViewModelFactory(((activity?.application) as HospitalApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}