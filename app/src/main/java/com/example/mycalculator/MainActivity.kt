package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumber: Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){  //when button pressed "view" contains all the properties of the button which was pressed
 //       tvInput?.append("1")   //this appends 1 to existing number. Now we do this for specific buttons

        tvInput?.append((view as Button).text)
        lastNumber = true  //signifies some number is present
        lastDot = false  //no decimal entered yet


    }

    fun onClear(view: View){
        tvInput?.text=""
    }

    fun onDecimal(view: View){
        if(lastNumber && !lastDot){   //executes iff number is true and dot is false

            tvInput?.append(".")
            lastNumber = false
            lastDot = true

        }
    }

    fun onOperator(view : View){
        tvInput?.text?.let{

            if(lastNumber && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastDot = false
                lastNumber = false
            }
        }

    }

    fun onEquals(view :  View){

        if(lastNumber){
            var tvvalue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvvalue.startsWith("-")){
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)   //makes -99 -> 99
                }
                if(tvvalue.contains("-")){

                    var splitValue = tvvalue.split("-")     //99-8 -->  99 , 8 in an array
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if(tvvalue.contains("+")){

                    var splitValue = tvvalue.split("+")     //99-8 -->  99 , 8 in an array
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if(tvvalue.contains("*")){

                    var splitValue = tvvalue.split("*")     //99-8 -->  99 , 8 in an array
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if(tvvalue.contains("/")){

                    var splitValue = tvvalue.split("/")     //99-8 -->  99 , 8 in an array
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }




            }catch( e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String) : String{
        var value = result;
        if(value.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value;
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}