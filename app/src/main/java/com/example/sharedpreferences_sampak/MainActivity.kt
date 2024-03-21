package com.example.sharedpreferences_sampak

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button1 = findViewById<Button>(R.id.theButton)
        val picture1 = findViewById<ImageView>(R.id.thePicture)
        val textField1 = findViewById<EditText>(R.id.textField)
        val sharedPrefs = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        //Restore State from SharedPreferences Object
        when (sharedPrefs.getInt("dogValue",0)) {
                1 -> picture1.setImageResource(R.drawable.dog1)
                2 -> picture1.setImageResource(R.drawable.dog2)
                3 -> picture1.setImageResource(R.drawable.dog3)
                4 -> picture1.setImageResource(R.drawable.dog4)
                5 -> picture1.setImageResource(R.drawable.dog5)
        }
        textField1.setText(sharedPrefs.getString("editText",getString(R.string.defaultEditText)))

        button1.setOnClickListener {
            val x = Random.nextInt(1, 5)
            editor.putInt("dogValue",x).apply()
            when (x) {
                1 -> picture1.setImageResource(R.drawable.dog1)
                2 -> picture1.setImageResource(R.drawable.dog2)
                3 -> picture1.setImageResource(R.drawable.dog3)
                4 -> picture1.setImageResource(R.drawable.dog4)
                5 -> picture1.setImageResource(R.drawable.dog5)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        val textField1 = findViewById<EditText>(R.id.textField)
        val sharedPrefs = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("editText",textField1.text.toString()).apply()
    }
}