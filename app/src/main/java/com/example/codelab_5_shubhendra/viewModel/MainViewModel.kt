package com.example.codelab_5_shubhendra.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.codelab_5_shubhendra.db.DataStoreTable
import com.example.codelab_5_shubhendra.repository.Repository

class MainViewModel(private val repository: Repository):ViewModel() {

    val getData:LiveData<List<DataStoreTable>>
        get()=repository.userData

    fun insert(dataStoreTable: DataStoreTable){
    repository.insertData(dataStoreTable)
}
    fun update(dataStoreTable: DataStoreTable){
        repository.updateData(dataStoreTable)
    }


}