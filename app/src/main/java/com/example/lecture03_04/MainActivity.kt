package com.example.lecture03_04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var pointText: TextView

    private var num1 : Int = 0
    private var num2 : Int =0
    private var pointScore : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 1: widgets IDs get , (all widgets Id's of an app are being stored in R.java)
        leftButton = findViewById(R.id.left_click)  // left button of type button
        // leftButton = findViewById<Button>(R.id.left_click)

        rightButton = findViewById(R.id.right_click)
        pointText = findViewById(R.id.points)


        //step 2: display random numbers on button
        displayRandomNumber()
    }

    fun leftBtnClickFun(view: View) {
        if(leftButton.text.toString().toInt() > rightButton.text.toString().toInt()){
            pointScore++
        }else pointScore--
        pointText.text = "Points Earned : $pointScore"
        displayRandomNumber()
    }

    fun rightBtnClickFun(view: View) {
        if(rightButton.text.toString().toInt() > leftButton.text.toString().toInt()){
            pointScore++
        }else pointScore--
        pointText.text = "Points Earned : $pointScore"
        displayRandomNumber()
    }

    fun displayRandomNumber(){
        val r = Random()
        num1= r.nextInt(1000)
        num2 = r.nextInt(1000)
        if(num1 == num2){
            num1 = r.nextInt(1000)
        }

        leftButton.text = "$num1";
        rightButton.text= "$num2";
    }
}