package com.example.bmicalculator

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private  lateinit var weightValue : EditText
    private  lateinit var heightValue : EditText
    private  lateinit var bmiValue : TextView
    private lateinit var  sharedPref: SharedPreferences
    private lateinit var  editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weightValue = findViewById(R.id.etWeight)
        heightValue=findViewById(R.id.etHeight)
        bmiValue=findViewById(R.id.tvBMIValue)
        var weight =25
        var height =5
        if(weightValue.text.toString().isNotEmpty() && heightValue.text.toString().isNotEmpty()) {
            weight =weightValue.text.toString().toInt()
            height =heightValue.text.toString().toInt()
            height /= 100
        }
        Log.i("weight value is:","${weight}")
        Log.i("height value is:","${height}")

        var bmiCalc=0;
        if(weight>0 && height>0){
            bmiCalc=weight/(height*height)
        }
        bmiValue.setText(bmiCalc.toString())
        Log.i("bmi value is:","${bmiCalc}")
        sharedPref=getSharedPreferences("my_sf", MODE_PRIVATE)
        editor=sharedPref.edit();
    }

    override fun onPause() {
        super.onPause()
        var weight =weightValue.text.toString().toInt()
        var height =heightValue.text.toString().toInt()
        editor.apply{
            putInt("weight",weight)
            putInt("height", height)
            commit()//ensures values are saved
        }
    }

    override fun onResume() {
        super.onResume()
        val weight =sharedPref.getInt("weight",0)
        val height =sharedPref.getInt("height",0)

        if(weight!=0) {
            weightValue.setText(weight.toString())
        }
        if(height!=0) {
            heightValue.setText(height.toString())
        }
    }
}