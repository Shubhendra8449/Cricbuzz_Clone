package com.example.codelab_5_shubhendra.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.databinding.FragmentInformatonBinding
import com.example.codelab_5_shubhendra.db.DataStoreTable
import kotlinx.android.synthetic.main.cardview_home.view.*


class Informaton : Fragment() {

    private lateinit var binding:FragmentInformatonBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentInformatonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data=requireActivity().intent.getParcelableExtra<DataStoreTable>("key")
        binding.name.text=data?.name
        binding.tvCountry.text=data?.country
        binding.etBorn.text=data?.dob
        binding.etTeam.text=data?.team
        binding.cirImg.setImageBitmap(BitmapFactory.decodeByteArray(data?.img, 0, data?.img!!.size))


//        binding.cirImg.setImageBitmap(data?.img)
//        Log.d("msg","${data?.dob}")
      when(data?.batsman){
          true->binding.etRole.text="Batsman"
          else->binding.etRole.text="Bowler"
      }



    }


}