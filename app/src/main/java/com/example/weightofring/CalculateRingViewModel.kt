package com.example.weightofring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor

class CalculateRingViewModel: ViewModel() {

    private val _size = MutableLiveData<String>()
    val size: LiveData<String> get() = _size

    private val _width = MutableLiveData<String>()
    val width: LiveData<String> get() = _width

    private val _thickness = MutableLiveData<String>()
    val thickness: LiveData<String> get() = _thickness


    fun calculateVolumeOfRing(): Double {
        val size = _size.value ?: throw Throwable("INVALID_SIZE")
        val sizeDouble = size.toDouble()
        val width = _width.value ?: throw Throwable("INVALID_SIZE")
        val widthDouble = width.toDouble()
        val thickness = _thickness.value ?: throw Throwable("INVALID_SIZE")
        val thicknessDouble = thickness.toDouble()


        val areaSize = ((sizeDouble * sizeDouble) / 4) * 3.14
        val insideDiameter = (thicknessDouble * 2.0) + sizeDouble
        val areaTotal = ((insideDiameter * insideDiameter) / 4) * 3.14
        val areaRingSide = areaTotal - areaSize

        return areaRingSide * widthDouble
    }

    fun calculateWeightOfRing(volumeRing: Double, density: DensityGoldEnum): Double {

        val resultFloor = (volumeRing * density.dens)
        return floor(resultFloor * 100.0) /100.0
    //        val gold750 = 0.0154 * volumeRing
//        val gold750Floor = floor(gold750 * 100.0) /100.0
//        val gold585 = 0.0138 * volumeRing
//        val gold585Floor = floor(gold585 * 100.0) /100.0
//        val silver = 0.01036 * volumeRing
//        val silverFloor = floor(silver * 100.0) /100.0
    }


    fun updateSize(size: String) {
        if (_size.value != size) {
            _size.value = size
        }
    }

    fun updateWidth(width: String) {
        if (_width.value != width) {
            _width.value = width
        }
    }

    fun updateThickness(thickness: String) {
        if (_thickness.value != thickness) {
            _thickness.value = thickness
        }
    }
}