package com.example.codelab_5_shubhendra.ui.bowler

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab_5_shubhendra.adapter.AdapterBatsman
import com.example.codelab_5_shubhendra.adapter.AdapterBowler
import com.example.codelab_5_shubhendra.databinding.FragmentBowlerBinding
import com.example.codelab_5_shubhendra.databinding.FragmentBowlingBinding

import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

class BowlerFragment : Fragment() {
        private lateinit var binding:FragmentBowlerBinding
    private lateinit var viewModelBatsman: MainViewModel
    private lateinit var repBatsman: Repository
    private lateinit var adapter:AdapterBowler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding= FragmentBowlerBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().app_bar_main.add_btn.visibility=View.INVISIBLE

        repBatsman = Repository(DataBase.getFun(requireActivity().applicationContext))
        viewModelBatsman= ViewModelProvider(this, MainViewModelFactory(repBatsman))[MainViewModel::class.java]

        viewModelBatsman.getData.observe(requireActivity(), Observer { it ->
            if (isAdded) {
                binding.rvRecyleBowler.layoutManager = LinearLayoutManager(requireContext())

                val list = ArrayList<DataStoreTable>()
                it.forEach {
                    if (!it.batsman) {
                        list.add(it)
                    }
                }
                if (list.size > 0) {

                    adapter = AdapterBowler(list, requireContext())
                    binding.rvRecyleBowler.adapter = adapter
                    binding.dataNotFoundBowler.visibility= View.INVISIBLE

                } else {
                    Log.d("tag", "no data found")
                binding.dataNotFoundBowler.visibility= View.VISIBLE
                }
            }

//            adapter.notifyDataSetChanged()
//
//            binding.rvRecyleBowler.adapter = adapter

        })
    }

}