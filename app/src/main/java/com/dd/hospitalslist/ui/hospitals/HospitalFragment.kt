package com.dd.hospitalslist.ui.hospitals

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.hospitalslist.HospitalApplication
import com.dd.hospitalslist.R
import com.dd.hospitalslist.databinding.HospitalsFragmentBinding
import kotlinx.android.synthetic.main.sections_fragment.*


class HospitalFragment : Fragment() {
    private val TAG = "HospitalFragment"

    companion object {
        fun newInstance() = HospitalFragment()
    }

    private lateinit var binding: HospitalsFragmentBinding

    private val hospitalViewModel: HospitalViewModel by activityViewModels {
        HospitalViewModelFactory(((activity?.application) as HospitalApplication).repository)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = HospitalsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = HospitalAdapter()
        binding.recyclerviewList.adapter = adapter
        binding.recyclerviewList.layoutManager = LinearLayoutManager(activity)

        hospitalViewModel.hospitals.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "onCreate: $it")
            adapter.submitList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_back -> {
                    findNavController().navigateUp()
                    true
                }
                R.id.action_done -> {
                    // Save profile changes
                    true
                }
                else -> false
            }
        }


        toolbar.setNavigationIcon(R.drawable.abc_vector_test)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


}