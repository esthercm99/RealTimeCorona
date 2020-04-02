package es.com.scoroleth.realtimecorona.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _totalCases = MutableLiveData<Long>()
    val totalCases: LiveData<Long> = _totalCases

    private val _actualCases = MutableLiveData<Long?>()
    val actualCases: LiveData<Long?> = _actualCases

    private val _recovered = MutableLiveData<Long?>()
    val recovered: LiveData<Long?> = _recovered

    private val _deaths = MutableLiveData<Long?>()
    val deaths: LiveData<Long?> = _deaths


    fun setTotalCases(numCases: Long?) {
        _totalCases.postValue(numCases)
    }
    fun setActualCases(numCases: Long?) {
        _actualCases.postValue(numCases)
    }
    fun setRecovered(numRecovered: Long?) {
        _recovered.postValue(numRecovered)
    }
    fun setDeaths(numDeaths: Long?) {
        _deaths.postValue(numDeaths)
    }
}