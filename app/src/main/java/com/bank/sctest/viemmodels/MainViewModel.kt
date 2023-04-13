package com.bank.sctest.viemmodels

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

class MainViewModel : ViewModel() {
    val scAmountLiveData = MutableLiveData<String>()
    val scTimeLiveData = MutableLiveData<String>()

    fun formatAmount(amount: String?) {
        if (TextUtils.isEmpty(amount) || amount == null) {
            scAmountLiveData.value = "0"
        } else {
           amountInternal(amount)
        }
    }

    private fun amountInternal(amount: String) {
        try {
            var formater: NumberFormat? = null
            val len = amount.length
            val num: Double = amount.toDouble()
            if (len === 0) {
                formater = DecimalFormat("###,###")
            } else {
                val buff = StringBuffer()
                buff.append("###,###.")
                for (i in 0 until len) {
                    buff.append("#")
                }
                formater = DecimalFormat(buff.toString())
            }
            scAmountLiveData.value = formater.format(num)
        } catch (e: java.lang.Exception) {
            scAmountLiveData.value = "0"
        }
    }

    fun formatTime(time: String?){
        if (TextUtils.isEmpty(time) || time == null) scTimeLiveData.value = "0s" else  formatTimeInternal(time)
    }

    private fun formatTimeInternal(time: String) {
        try {
            val timeInt = time.toInt()
            scTimeLiveData.value = "${timeInt/60}m${timeInt%60}s"
        }catch (e:Exception){
            scTimeLiveData.value =  "0s"
        }
    }
}