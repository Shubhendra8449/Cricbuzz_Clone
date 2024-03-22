package com.example.codelab_5_shubhendra.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.adapter.PageAdapter
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModelFav: MainViewModel
    private lateinit var repFavourite: Repository
    private  var flag:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide()


        repFavourite = Repository(DataBase.getFun(applicationContext))
        viewModelFav= ViewModelProvider(this, MainViewModelFactory(repFavourite)).get(MainViewModel::class.java)






        val viewPagerAdapter= PageAdapter(supportFragmentManager)

        viewPagerAdapter.addFragment(Informaton())
        viewPagerAdapter.addFragment(Batting())
        viewPagerAdapter.addFragment(Bowling())
        view_pager.adapter = viewPagerAdapter


        tab_layout.layoutDirection = View.LAYOUT_DIRECTION_LTR



        tab_layout.setupWithViewPager(view_pager)
        view_pager.currentItem = 0
        tab_layout.getTabAt(0)?.text="Info"
        tab_layout.getTabAt(1)?.text="Batting"
        tab_layout.getTabAt(2)?.text="Bowling"
        val data=intent.getParcelableExtra<DataStoreTable>("key")

        title_bar.text=data?.name

        viewModelFav.getData.observe(this, Observer {


            flag=it[data?.id!!-1].favourite
            if (flag){
                btn_favred.visibility=View.VISIBLE
            }
            else{
                btn_fav.visibility=View.VISIBLE
            }
            Log.d("msg","${flag}")
        })


        btn_back.setOnClickListener{
           val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
//        app_bar_main.btn_back.visibility=View.INVISIBLE

        btn_fav.setOnClickListener{

            flag=!flag

            if (flag){
                btn_favred.visibility=View.VISIBLE
                if (data != null) {
                    viewModelFav.update(DataStoreTable(
                        data.id,
                        data.img,
                        data.name,
                        data.team,
                        data.country,
                        data.matches,
                        data.runs,
                        data.wickets,
                        data.dob,
                        data.batsman,
                        data.Bowler,


                        true
                    ))

                }
            Toast.makeText(this,"fav pressed",Toast.LENGTH_LONG).show()}
            else{
                btn_favred.visibility=View.INVISIBLE
                if (data != null) {
                    viewModelFav.update(DataStoreTable(
                        data.id,
                        data.img,
                        data.name,
                        data.team,
                        data.country,
                        data.matches,
                        data.runs,
                        data.wickets,
                        data.dob,
                        data.batsman,
                        data.Bowler,


                        false
                    ))
                }
                Toast.makeText(this,"fav unpressed",Toast.LENGTH_LONG).show()
            }
        }



    }
}