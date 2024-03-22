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
import kotlinx.android.synthetic.main.cardview_home.view.*

class AdapterBatsman(private val userList:List<DataStoreTable>,private val context:Context):RecyclerView.Adapter<AdapterBatsman.ViewHolderClass>() {
    private val binding:CardviewHomeBinding?=null
    inner class ViewHolderClass(view: View?): RecyclerView.ViewHolder(view!!){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_home,parent,false)
            return ViewHolderClass(view)




    }
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
//        Log.d("msg","adapter : ${userList[position].batsman} + pos : $position")
            holder.itemView.apply {

                tv_countryname.text = userList[position].country

                img_profile_dp.setImageBitmap(BitmapFactory.decodeByteArray(userList[position].img, 0, userList[position].img!!.size))

                tv_playername.text = userList[position].name
        }
        holder.itemView.cardview.setOnClickListener{
            val intent=Intent(context, DetailsActivity::class.java)
            intent.putExtra("key",userList[position])
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return userList.size
    }
}