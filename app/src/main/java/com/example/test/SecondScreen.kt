package com.example.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class SecondScreen : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    lateinit var displayData:Button
    lateinit var textViewData:TextView
    lateinit var gpa:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        displayData=findViewById(R.id.displayData)
        textViewData=findViewById(R.id.textViewData)
        gpa=findViewById(R.id.gpa)

        dbHelper=DBHelper(this)

      displayData.setOnClickListener {
          val cursor=dbHelper.getAllData()
          val buffer=StringBuffer()
          if (cursor!=null){
              if (cursor.count==0){
                  showMessage("DATA","Nothing Found")
              }
          }
          buffer.append("Course Name   Course Credit   Course Grade\n\n")
          if (cursor!=null){
              while (cursor.moveToNext()){

                  buffer.append(cursor.getString(0)+"\t\t\t\t\t\t\t\t\t")
                  buffer.append(cursor.getString(1)+" \t\t\t\t\t\t\t\t")
                  buffer.append(cursor.getString(2)+"\n\n")
              }
          }

         displayData(buffer.toString())
          gpa.text="Current Gpa :"+gpaCalculate()
      }

    }
    fun displayData(data: String) {
        textViewData.text = data
    }
    fun showMessage(title:String,message:String){
        val dialog=AlertDialog.Builder(this)
        dialog.create()
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.show()
    }

    fun gpaCalculate():String{
       val cursor= dbHelper.getAllData()
        var creditTotal:Int=0
        var upperData:Double=0.0
       if (cursor!=null){

           while (cursor.moveToNext()){
               var credit:Int=0
               var grade=""
               credit=cursor.getString(1).toInt()
               grade=cursor.getString(2)

               upperData+= (gradePrint(grade)*credit).toInt()
               creditTotal+=credit
           }
       }
        return String.format("%.2f", (upperData/creditTotal))
    }

    fun gradePrint(grade:String):Double{
        if(grade=="A+"){
            return 4.0
        }
        else if(grade=="A"){
            return 4.0
        }
        else if (grade=="A-"){
            return 3.7
        }
        else if (grade=="B+"){
            return 3.3
        }
        else if (grade=="B"){
            return 3.0
        }
        else if (grade=="B-"){
            return 2.7
        }
        else if (grade=="C+"){
            return 2.3
        }
        else if (grade=="C"){
            return 2.0
        }else if (grade=="C-"){
            return 1.7
        }
        else if (grade=="D+"){
            return 1.3
        }
        else if (grade=="D"){
            return 1.0
        }
        else if (grade=="E"){
            return 0.0
        }
        return 0.0
    }

}