package com.precieux.solvelt.logic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.precieux.solvelt.logic.ui.viewmodels.ProblemSolverViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProblemSolverFragment : Fragment() {

    private val viewModel: ProblemSolverViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return TextView(requireContext()).apply {
            text = "ProblemSolverFragment - OK"
            textSize = 24f
            gravity = android.view.Gravity.CENTER
        }
    }
}
