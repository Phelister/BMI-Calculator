package com.example.bmicalculator

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private  lateinit var weightValue : EditText
    private  lateinit var heightValue : EditText
    private  lateinit var bmiValue : TextView
    private  lateinit var bmi : TextView
    private  lateinit var submit : Button
    private  lateinit var card : CardView
    private lateinit var  sharedPref: SharedPreferences
    private lateinit var  editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weightValue = findViewById(R.id.etWeight)
        heightValue=findViewById(R.id.etHeight)
        bmiValue=findViewById(R.id.tvBMIValue)
        bmi=findViewById(R.id.tvBmi)
        submit=findViewById(R.id.btn)
        card =findViewById(R.id.cardView2)


        submit.setOnClickListener{

            var weight =weightValue.text.toString().toInt()
            var height =heightValue.text.toString().toInt()
            height /= 100

            Log.i("weight value is:","${weight}")
            Log.i("height value is:","${height}")

            var bmiCalc=0;
            if(weight>0 && height>0){
                bmiCalc=weight/(height*height)
            }

            card.visibility= View.VISIBLE
            bmiValue.setText(bmiCalc.toString())
            Log.i("bmi value is:","${bmiCalc}")
        }


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