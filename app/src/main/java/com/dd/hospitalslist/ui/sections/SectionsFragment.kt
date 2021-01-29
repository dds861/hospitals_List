package com.dd.hospitalslist.ui.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dd.hospitalslist.HospitalApplication
import com.dd.hospitalslist.databinding.SectionsFragmentBinding

class SectionsFragment : Fragment() {

    companion object {
        fun newInstance() = SectionsFragment()
    }

    private val viewModel: SectionsViewModel by activityViewModels {
        SectionsViewModelFactory(((activity?.application) as HospitalApplication).repository)
    }

    private lateinit var binding: SectionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SectionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btn.setOnClickListener {
            val action = SectionsFragmentDirections.actionSectionsFragmentToHospitalFragment()
            findNavController().navigate(action)
        }

    }


}