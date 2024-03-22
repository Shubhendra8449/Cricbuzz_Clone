package com.example.codelab_5_shubhendra.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab_5_shubhendra.adapter.AdapterHome
import com.example.codelab_5_shubhendra.databinding.FragmentHomeBinding
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModelHome:MainViewModel
    private lateinit var repoHome:Repository

    private lateinit var adapter:AdapterHome


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


       binding= FragmentHomeBinding.inflate(inflater)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoHome = Repository(DataBase.getFun(requireActivity().applicationContext))
        viewModelHome= ViewModelProvider(this,MainViewModelFactory(repoHome))[MainViewModel::class.java]

//        viewModelHome.getData.observe(requireActivity(), Observer {
//
//            binding.rvRecyleHome.layoutManager=LinearLayoutManager(requireContext())
//            val adapter = AdapterHome(it,viewModelHome,requireContext())
////            adapter.notifyDataSetChanged()
//
//            binding.rvRecyleHome.adapter=adapter
//        })

        viewModelHome.getData.observe(requireActivity(), Observer {
                it ->
            val list = ArrayList<DataStoreTable>()

            it.forEach {

                list.add(it)
            }
            if (list.size > 0) {
                if (isAdded) {
                    binding.rvRecyleHome.layoutManager=LinearLayoutManager(requireContext())
                    adapter = AdapterHome(list,viewModelHome, requireContext())
                    binding.rvRecyleHome.adapter = adapter
                    binding.dataNotFoundHome.visibility = View.INVISIBLE
                }
            }
            else{
                binding.dataNotFoundHome.visibility = View.VISIBLE
                binding.rvRecyleHome.adapter = null
            }


        })


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                    if (newText!!.length>=3){

                        viewModelHome.getData.observe(requireActivity()) {
                            val list = ArrayList<DataStoreTable>()

                            it.forEach {
                                Log.d("msg","name : ${it.name} +  text : $newText")
                                if (it.name.contains(newText, true)) {

                                    list.add(it)
                                    if (list.size > 0) {

                                            adapter = AdapterHome(list,viewModelHome, requireContext())
                                            binding.rvRecyleHome.adapter = adapter
                                            binding.dataNotFoundHome.visibility = View.INVISIBLE

                                    }
                                }
                                else if(list.isEmpty()){
                                    Log.d("inside","name : ${it.name} +  text : $newText")
                                    binding.dataNotFoundHome.visibility=View.VISIBLE
                                    binding.rvRecyleHome.adapter=null
                                }
//
                            }

                        }
                    }
                    else {
//                        binding.rvRecyleHome.layoutManager=LinearLayoutManager(requireContext())
                        viewModelHome.getData.observe(requireActivity()) { it ->
                            val list = ArrayList<DataStoreTable>()

                            it.forEach {

                                list.add(it)
                            }
                                    if (list.size > 0) {
                                    if (isAdded) {
                                        adapter = AdapterHome(list,viewModelHome, requireContext())
                                        binding.rvRecyleHome.adapter = adapter
                                        binding.dataNotFoundHome.visibility = View.INVISIBLE
                                        }
                                    }
                                    else {
                                        Log.d("tag", "no data found")
                                        binding.dataNotFoundHome.visibility = View.INVISIBLE
                                    }

                        }
                    }

                return false
            }
        })

        }



    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onResume() {
        super.onResume()
        requireActivity().app_bar_main.add_btn.visibility=View.VISIBLE
    }

}