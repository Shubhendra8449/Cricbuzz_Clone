package com.example.codelab_5_shubhendra.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codelab_5_shubhendra.ui.DetailsActivity
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.databinding.CardviewHomeBinding
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import kotlinx.android.synthetic.main.cardview_home.view.*

class AdapterHome(private val userList:List<DataStoreTable>,private val viewModel: MainViewModel,private val context:Context):RecyclerView.Adapter<AdapterHome.ViewHolderClass>() {
    private val binding:CardviewHomeBinding?=null
    inner class ViewHolderClass(view: View): RecyclerView.ViewHolder(view){
//    val name:TextView=view.findViewById(R.id.tv_player_name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHome.ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_home,parent,false)
        return ViewHolderClass(view)

    }

    override fun onBindViewHolder(holder: AdapterHome.ViewHolderClass, position: Int) {
        val data=userList[position]

        holder.itemView.apply {
           tv_countryname.text=data.country
            img_profile_dp.setImageBitmap(BitmapFactory.decodeByteArray(userList[position].img, 0, userList[position].img!!.size))
//            img_profile_dp.setImageBitmap(data.img)
            tv_playername.text=data.name
        }
        holder.itemView.cardview.setOnClickListener{
            val intent=Intent(context, DetailsActivity::class.java)
            intent.putExtra("key",data)
            context.startActivity(intent)
        }

//        holder.itemView.
//        holder.itemView.
//        holder.itemView.

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}