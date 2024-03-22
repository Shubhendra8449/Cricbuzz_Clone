package com.example.codelab_5_shubhendra.ui.addDetailsFragment

import android.Manifest
import android.annotation.SuppressLint

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.codelab_5_shubhendra.R
import com.example.codelab_5_shubhendra.databinding.FragmentAddDetailsBinding
import com.example.codelab_5_shubhendra.db.DataBase
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository
import com.example.codelab_5_shubhendra.viewModel.MainViewModel
import com.example.codelab_5_shubhendra.viewModel.MainViewModelFactory
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.fragment_add_details.*
import java.io.ByteArrayOutputStream
import java.util.*



class AddDetailsFragment : Fragment() {

    private var image: ByteArray?=null
    private var select: Bitmap?=null
    private var dp:Boolean = true
    private lateinit var viewModelObj:MainViewModel
    private lateinit var repo:Repository
    private lateinit var binding: FragmentAddDetailsBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAddDetailsBinding.inflate(inflater)


        return binding.root




    }
    private fun addUserDetail() {

        viewModelObj.insert(
            DataStoreTable(
                null,
                image!!,
                binding.  etPlayersName.text.trim().toString(),
               binding. etTeam.text.trim().toString(),
                binding.edCountryCode.selectedCountryName.toString(),
              binding.  etMatches.text.trim().toString(),
                binding.  etRuns.text.trim().toString(),
                binding.  etWickets.text.trim().toString(),
                binding.etDob.text.toString(),
                binding.radioButton.isChecked,
                binding.radioButton2.isChecked,
                false




            )
        )
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repo= Repository(DataBase.getFun(requireActivity().applicationContext))
        viewModelObj= ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]



        binding.btnSaveDetails.setOnClickListener{



            binding.apply {

                if (etPlayersName.text.isEmpty()){
                    etPlayersName.error="mandatory"
                    return@setOnClickListener
                }
                else if(etTeam.text.isEmpty()){
                    etTeam.error="mandatory"
                    return@setOnClickListener
                }
                else if(etDob.text.isEmpty() ){
                    Toast.makeText(requireContext(),"Enter DOB",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                else if(!radioButton.isChecked && !radioButton2.isChecked ){
                    Toast.makeText(requireContext(),"Select Batsman or Bowler",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                else if(etMatches.text.isEmpty()){
                    etMatches.error="mandatory"
                    return@setOnClickListener
                }


                else if(etRuns.text.isEmpty()){
                    etRuns.error="mandatory"
                    return@setOnClickListener
                }


                else if(etWickets.text.isEmpty()){
                    etWickets.error="mandatory"
                    return@setOnClickListener
                }
                 if (select==null){

                    Toast.makeText(requireContext(),"Select profile",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            addUserDetail()
            findNavController().navigate(R.id.action_addDetailsFragment_to_nav_home)

        }




        val myCalendar=Calendar.getInstance()

        val dataPicker = DatePickerDialog.OnDateSetListener{ _: DatePicker, year, month, dayMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayMonth)
            updateLable(myCalendar)
        }


        binding.etDob.setOnClickListener{
            DatePickerDialog(requireActivity(),dataPicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.etDob.setOnClickListener {
            val c: java.util.Calendar = java.util.Calendar.getInstance()
            val mYear: Int = c.get(java.util.Calendar.YEAR) // current year
            val mMonth: Int = c.get(java.util.Calendar.MONTH) // current month
            val mDay: Int = c.get(java.util.Calendar.DAY_OF_MONTH) // current day

            // date picker dialog

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                { view, year, month, day -> // set day of month , month and year value in the edit text
                    if (c.get(java.util.Calendar.YEAR) - year >= 18) {
                        binding.etDob.text =
                            day.toString() + "/" + (month + 1) + "/" + year

                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Under 18 Player Not Allowed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }, mYear, mMonth, mDay

            )
            datePickerDialog.show()

        }


        binding.ivPlayerDp.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Select Image")
            builder.setMessage("Choose Your Option?")

            builder.setPositiveButton("Gallery") { dialog, _ ->
                dialog.dismiss()
                galleryPermission()
            }


            builder.setNegativeButton("Camera") { dialog, _ ->
                dialog.dismiss()
                askCameraPermissions()

            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
    private fun askCameraPermissions() {

        when(checkSelfPermission(requireContext(), Manifest.permission.CAMERA)){
            PackageManager.PERMISSION_GRANTED->openCamera()
            else->requestPermissions(arrayOf(Manifest.permission.CAMERA),0)
        }
    }
    private fun galleryPermission() {

        when(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)){
            PackageManager.PERMISSION_GRANTED->openGallery()
            else->requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
    }

    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, 0)
    }
    private fun openGallery() {
        val gallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery,1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {

        Log.d("msg","requestcode: $requestCode")
        when(requestCode){
            0->if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)openCamera()
            1->if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)openGallery()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        when(requestCode){
            0->{
                iv_player_dp.setImageBitmap(data?.extras?.get("data") as Bitmap)
                select=data.extras?.get("data") as Bitmap
                val outputStream = ByteArrayOutputStream()
                val bitmap = data.extras?.get("data") as Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outputStream)
                val btArray = outputStream.toByteArray()
                image=btArray

            }
            1->{val outputStream = ByteArrayOutputStream()
                val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,data?.data)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outputStream)
                val btArray = outputStream.toByteArray()
                image=btArray
                iv_player_dp.setImageBitmap(bitmap)
                select=bitmap
            }
            else-> Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }


@RequiresApi(Build.VERSION_CODES.N)
private fun updateLable(myCalendar: Calendar){
    val myFormat ="dd-MM-yyyy"
    val sdf=java.text.SimpleDateFormat(myFormat, Locale.UK)
    binding.etDob.setText(sdf.format(myCalendar.time))
}
}




