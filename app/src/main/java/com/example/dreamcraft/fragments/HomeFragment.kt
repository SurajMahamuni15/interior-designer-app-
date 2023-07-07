package com.example.dreamcraft.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dreamcraft.R
import com.example.dreamcraft.WorkTypeModel
import com.example.dreamcraft.adapter.CategoriesAdapter
import com.example.dreamcraft.adapter.WorkTypeAdapter
import com.example.dreamcraft.databinding.FragmentHomeBinding
import com.example.dreamcraft.utils.Constants.CATEGORIES_APARTMENT
import com.example.dreamcraft.utils.Constants.CATEGORIES_COMMERCIAL
import com.example.dreamcraft.utils.Constants.CATEGORIES_HOUSE
import com.example.dreamcraft.utils.Constants.CATEGORIES_OFFICE
import com.example.dreamcraft.utils.Constants.WORK_2D
import com.example.dreamcraft.utils.Constants.WORK_3D

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workAdapter = WorkTypeAdapter()
        val categoriesAdapter = CategoriesAdapter()
        workAdapter.setData(ArrayList<WorkTypeModel>().apply {
            add(WorkTypeModel(WORK_2D, getString(R.string.work_2d)))
            add(WorkTypeModel(WORK_3D, getString(R.string.work_3d)))
        })
        categoriesAdapter.setData(ArrayList<WorkTypeModel>().apply {
            add(WorkTypeModel(CATEGORIES_APARTMENT, getString(R.string.categories_apartment)))
            add(WorkTypeModel(CATEGORIES_OFFICE, getString(R.string.categories_office)))
            add(WorkTypeModel(CATEGORIES_COMMERCIAL, getString(R.string.categories_commercial)))
            add(WorkTypeModel(CATEGORIES_HOUSE, getString(R.string.categories_house)))
        })
        binding.categoriesRv.adapter = categoriesAdapter
        binding.workRv.adapter = workAdapter
    }
}