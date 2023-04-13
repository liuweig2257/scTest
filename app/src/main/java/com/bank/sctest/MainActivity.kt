package com.bank.sctest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bank.sctest.databinding.ActivityMainBinding
import com.bank.sctest.viemmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    lateinit var binding:ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.amountEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.formatAmount(s?.toString()?.trim())
            }
        })

        binding.timeEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mainViewModel.formatTime(s?.toString()?.trim())
            }
        })

        mainViewModel.scAmountLiveData.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                binding.amountText.text = t?:""
            }
        })
        mainViewModel.scTimeLiveData.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                binding.timeText.text = t?:""
            }
        })

        binding.btnSubmit.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity,ResultActivity::class.java))
            }
        })
    }
}