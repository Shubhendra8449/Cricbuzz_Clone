package com.example.codelab_5_shubhendra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.databinding.FragmentBowlingBinding
import com.example.codelab_5_shubhendra.db.DataStoreTable


class Bowling : Fragment() {
  private lateinit var binding:FragmentBowlingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBowlingBinding.inflate(inflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data=requireActivity().intent.getParcelableExtra<DataStoreTable>("key")
        binding.matchesBowling.text=data?.matches
        binding.testwick.text=data?.wickets

    }

}