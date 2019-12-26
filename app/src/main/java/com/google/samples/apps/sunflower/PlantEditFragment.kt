/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.databinding.FragmentPlantDetailBinding
import com.google.samples.apps.sunflower.databinding.FragmentPlantEditBinding
import com.google.samples.apps.sunflower.utilities.InjectorUtils
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel
import com.google.samples.apps.sunflower.viewmodels.PlantEditViewModel
import com.google.samples.apps.sunflower.viewmodels.PlantEditViewModelFactory

/**
 * A fragment representing a single Plant detail screen.
 */
class PlantEditFragment : Fragment() {
    private val args: PlantEditFragmentArgs by navArgs()

    private val plantEditViewModel: PlantEditViewModel by viewModels {
        InjectorUtils.providePlantEditViewModelFactory(requireActivity(), args.plantId)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlantEditBinding>(
                inflater, R.layout.fragment_plant_edit, container, false
        ).apply {
            viewModel = plantEditViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        plantEditViewModel.plant.observe(this, Observer {
            binding.notifyChange()
        })
        view?.postDelayed({
//            plantEditViewModel.plant.value = Plant("32", "888", "323232", 0)
        }, 200)
        return binding.root
    }
}
