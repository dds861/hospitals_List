package com.dd.hospitalslist.ui.hospitals

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.hospitalslist.HospitalApplication
import com.dd.hospitalslist.data.CategoryState
import com.dd.hospitalslist.data.HospitalModel
import com.dd.hospitalslist.data.RegionState
import com.dd.hospitalslist.databinding.HospitalsFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HospitalFragment : Fragment() {
    private val TAG = "HospitalFragment"

    private lateinit var binding: HospitalsFragmentBinding
    private lateinit var adapter: HospitalAdapter

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
        adapter = HospitalAdapter()

        binding.recyclerviewList.adapter = adapter
        binding.recyclerviewList.layoutManager = LinearLayoutManager(requireContext())


        setupSpinners()

        setupSearchView()

    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.i(TAG, "setupSearchView v: $v")
            Log.i(TAG, "setupSearchView hasFocus: $hasFocus")
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i(TAG, "onQueryTextSubmit newText: $newText")

                newText?.let {
                    hospitalViewModel.hospitalModel =
                        hospitalViewModel.hospitalModel.copy(searchQuery = it)
                }
                setList(hospitalViewModel.hospitalModel)
                return true
            }

        })
    }

    private fun setupSpinners() {
        spinnerRegionObserve()
        spinnerCategoryObserve()
        spinnerRegionItemSelectedListenerSetup()
        spinnerCategoryItemSelectedListenerSetup()
    }

    private fun spinnerCategoryItemSelectedListenerSetup() {
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedItem = binding.spinnerCategory.selectedItem.toString()

                    if (position == ALL_CATEGORIES) {
                        hospitalViewModel.hospitalModel =
                            hospitalViewModel.hospitalModel.copy(
                                categoryName = selectedItem,
                                categoryState = CategoryState.ALL
                            )
                    } else {
                        hospitalViewModel.hospitalModel =
                            hospitalViewModel.hospitalModel.copy(
                                categoryName = selectedItem,
                                categoryState = CategoryState.SPECIFIC_ITEM
                            )
                    }

                    hospitalViewModel.hospitalModel =
                        hospitalViewModel.hospitalModel.copy(categoryName = selectedItem)

                    setList(
                        hospitalViewModel.hospitalModel

                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun spinnerRegionItemSelectedListenerSetup() {
        binding.spinnerRegion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedItem = binding.spinnerRegion.selectedItem.toString()

                    if (position == ALL_REGIONS) {
                        hospitalViewModel.hospitalModel =
                            hospitalViewModel.hospitalModel.copy(
                                regionName = selectedItem,
                                regionState = RegionState.ALL
                            )
                    } else {
                        hospitalViewModel.hospitalModel =
                            hospitalViewModel.hospitalModel.copy(
                                regionName = selectedItem,
                                regionState = RegionState.SPECIFIC_ITEM
                            )
                    }



                    setList(
                        hospitalViewModel.hospitalModel

                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun spinnerCategoryObserve() {
        hospitalViewModel.categoriesName.observe(viewLifecycleOwner, { list ->
            context?.let { context ->
                ArrayAdapter(
                    context,
                    R.layout.simple_spinner_item,
                    list
                ).also { adapter ->
                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.spinnerCategory.adapter = adapter
                }
            }
            binding.spinnerCategory.setSelection(ALL_CATEGORIES)
        })
    }

    private fun spinnerRegionObserve() {
        hospitalViewModel.regionNames.observe(viewLifecycleOwner, { list ->
            context?.let { context ->
                ArrayAdapter(
                    context,
                    R.layout.simple_spinner_item,
                    list
                ).also { adapter ->

                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.spinnerRegion.adapter = adapter
                }
            }
            binding.spinnerRegion.setSelection(ALL_REGIONS)
        })
    }

    companion object {
        const val ALL_CATEGORIES = 0
        const val ALL_REGIONS = 5
    }


    private fun setList(hospitalModel: HospitalModel) {


        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class)
            hospitalViewModel.getHospitals(hospitalModel).collectLatest { pagingData ->

                adapter.submitData(pagingData)
            }
        }
    }

}

