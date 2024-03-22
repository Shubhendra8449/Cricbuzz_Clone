package com.example.codelab_5_shubhendra.ui.favourite

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab_5_shubhendra.adapter.AdapterBatsman
import com.example.codelab_5_shubhendra.databinding.FragmentFavouriteBinding
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class FavouriteFragment : Fragment() {


    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var viewModelBatsman: MainViewModel
    private lateinit var repBatsman: Repository
    private lateinit var adapter: AdapterBatsman


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


       binding= FragmentFavouriteBinding.inflate(inflater)
        return binding.root



    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().app_bar_main.add_btn.visibility=View.INVISIBLE


        repBatsman = Repository(DataBase.getFun(requireActivity().applicationContext))
        viewModelBatsman= ViewModelProvider(this, MainViewModelFactory(repBatsman)).get(MainViewModel::class.java)

        viewModelBatsman.getData.observe(requireActivity(), Observer { it ->
            Log.d("onViewCreated", it.size.toString())
            if (isAdded){
                binding.rvRecyleFavourite.layoutManager = LinearLayoutManager(requireContext())

                val list=ArrayList<DataStoreTable>()
                it.forEach {
                    if (it.favourite){
                        list.add(it)
                    }
                }
                if (list.size>0){
                    if (isAdded){
//                        Log.d("msg","${list}")
                        adapter = AdapterBatsman(list, requireContext())
                        binding.rvRecyleFavourite.adapter = adapter
                        binding.favNotFound.visibility= View.INVISIBLE
                    }
                }




                else {
                    Log.d("tag", "no data found")

                    binding.favNotFound.visibility= View.VISIBLE
                    binding.rvRecyleFavourite.adapter=null


                }

            }


        })

    }


    }


