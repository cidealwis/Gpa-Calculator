package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var courseName:EditText
    lateinit var numberOfCredit:EditText
    lateinit var yourGrade:EditText
    lateinit var save:Button
    lateinit var myCurrentGpa:Button
    lateinit var delete:Button
    lateinit var deleteCourse:EditText
    lateinit var update:Button
    lateinit var dbHelper: DBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseName=findViewById(R.id.courseName)
        numberOfCredit=findViewById(R.id.numberOfCredit)
        yourGrade=findViewById(R.id.yourGrade)
        save=findViewById(R.id.save)
        myCurrentGpa=findViewById(R.id.myCurrentGpa)
        delete=findViewById(R.id.delete)
        deleteCourse=findViewById(R.id.deleteCourse)
        update=findViewById(R.id.update)
        dbHelper=DBHelper(this)

        save.setOnClickListener {

         val result=   dbHelper.insertData(courseName.text.toString(),numberOfCredit.text.toString(),yourGrade.text.toString())
            if (result==true){
                Toast.makeText(this,"Your ${courseName.text.toString()}  data Save",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Your ${courseName.text.toString()}  data not Save",Toast.LENGTH_SHORT).show()
            }
        }

        delete.setOnClickListener {
            val result=dbHelper.deleteData(deleteCourse.text.toString())
            if (result==true){
                Toast.makeText(this,"Your ${deleteCourse.text.toString()}  data delete",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Your ${deleteCourse.text.toString()}  data not here",Toast.LENGTH_SHORT).show()
            }
        }

        update.setOnClickListener {
            val result=   dbHelper.updateData(courseName.text.toString(),numberOfCredit.text.toString(),yourGrade.text.toString())
            if (result==true){
                Toast.makeText(this,"Your ${courseName.text.toString()}  data Update",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Your ${courseName.text.toString()}  data not Update",Toast.LENGTH_SHORT).show()
            }
        }

        myCurrentGpa.setOnClickListener {
            val intent=Intent(this,SecondScreen::class.java)
            startActivity(intent)
        }



    }


}