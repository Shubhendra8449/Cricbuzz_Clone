package com.example.codelab_5_shubhendra.ui.batsman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab_5_shubhendra.adapter.AdapterBatsman
import com.example.codelab_5_shubhendra.databinding.FragmentBatsmanBinding
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*

class BatsmanFragment : Fragment() {

    private lateinit var binding:FragmentBatsmanBinding
    private lateinit var viewModelBatsman:MainViewModel
    private lateinit var repBatsman:Repository
    private lateinit var adapter:AdapterBatsman



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentBatsmanBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().app_bar_main.add_btn.visibility=View.INVISIBLE

        repBatsman = Repository(DataBase.getFun(requireActivity().applicationContext))
        viewModelBatsman= ViewModelProvider(this, MainViewModelFactory(repBatsman))[MainViewModel::class.java]

        viewModelBatsman.getData.observe(requireActivity()) { it ->

            if (isAdded) {
                binding.rvRecyleBatsman.layoutManager = LinearLayoutManager(requireContext())

                val list = ArrayList<DataStoreTable>()
                it.forEach {
                    if (it.batsman) {
                        list.add(it)
                    }
                }
                if (list.size > 0) {

                    adapter = AdapterBatsman(list, requireContext())
                    binding.rvRecyleBatsman.adapter = adapter
                    binding.dataNotFound.visibility = View.INVISIBLE

                } else {
                    binding.dataNotFound.visibility = View.VISIBLE
                }

            }
//                    adapter.notifyDataSetChanged()

//                    binding.rvRecyleBatsman.adapter = adapter

        }

    }



}