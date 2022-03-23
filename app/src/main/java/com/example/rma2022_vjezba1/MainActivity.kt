package com.example.rma2022_vjezba1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private val listaVrijednosti= arrayListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstance : Bundle?){
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_main)

        //Inicijaliziramo elemente
        button = findViewById<Button>(R.id.button1)
        editText = findViewById<EditText>(R.id.editText1)
        listView = findViewById<ListView>(R.id.listView1)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listaVrijednosti)
        listView.adapter=adapter

        button.setOnClickListener{
            addToList()
        }


    }

    private fun addToList() {
        listaVrijednosti.add(0,editText.text.toString())
        adapter.notifyDataSetChanged();
        editText.setText("")

    }


}