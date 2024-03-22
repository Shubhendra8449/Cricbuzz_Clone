package com.example.codelab_5_shubhendra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.databinding.FragmentBattingBinding
import com.example.codelab_5_shubhendra.db.DataStoreTable


class Batting : Fragment() {
    private lateinit var binding:FragmentBattingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding= FragmentBattingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data=requireActivity().intent.getParcelableExtra<DataStoreTable>("key")
        binding.batsmanRun.text=data?.runs
        binding.batsmanMatches.text=data?.matches


    }
}