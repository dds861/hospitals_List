package com.dd.hospitalslist.ui.hospitals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dd.hospitalslist.HospitalApplication
import com.dd.hospitalslist.R
import com.dd.hospitalslist.databinding.HospitalsFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HospitalFragment : Fragment() {
    private val TAG = "HospitalFragment"

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
        binding.recyclerviewList.layoutManager = LinearLayoutManager(requireContext())



        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            hospitalViewModel.hospitals.collectLatest { pagingData ->

                adapter.submitData(pagingData)
            }
        }




        binding.recyclerviewList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.includeHospitalFilter.root.visibility = View.GONE
                    Log.i(TAG, "onBindViewHolder: Scrolled Down")
                } else {
                    binding.includeHospitalFilter.root.visibility = View.VISIBLE
                    Log.i(TAG, "onBindViewHolder: Scrolled Up")
                }
            }

//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                when (newState) {
//                    AbsListView.OnScrollListener.SCROLL_STATE_FLING -> {
//                        // Do something
//                    }
//                    AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL -> {
//                        // Do something
//                    }
//                    else -> {
//                        // Do something
//                    }
//                }
//            }
        })

        //        binding.btn.setOnClickListener {
        //            val action = SectionsFragmentDirections.actionSectionsFragmentToHospitalFragment()
        //            findNavController().navigate(action)
        //        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_night_mode -> {
                    findNavController().navigateUp()
                    true
                }

                else -> false
            }
        }

        //        //navigate Up
        //        binding.toolbar.setNavigationIcon(R.drawable.abc_vector_test)
        //        binding.toolbar.setNavigationOnClickListener {
        //            findNavController().navigateUp()
        //        }
    }
}