package com.nimeshlj.blogspot.projectmultiplicationtable

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.RequiresApi
import java.util.ArrayList

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var listView: ListView
    private lateinit var dropdown: Spinner
    private lateinit var seekBar: SeekBar

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.tableList)
        seekBar = findViewById(R.id.seekTable)

        //spinner
        dropdown = findViewById(R.id.spinner)
        val tablesUpTo = resources.getStringArray(R.array.tables)
        val adapter =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_dropdown_item, tablesUpTo)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = adapter
        dropdown.onItemSelectedListener = this
        seekBar.min = 1

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                populate(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Log.d("tracking", "onStartTrackingTouch: $seekBar ")
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Log.d("tracking", "onStopTrackingTouch: $seekBar ")
            }
        })
    }

    fun populate(table: Int) {
        val multiTable = ArrayList<String>()
        for (i in 1..10) {
            multiTable.add(table.toString() + "X" + i + "=" + table * i)
        }
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, multiTable)
        listView.adapter = arrayAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (parent.id == R.id.spinner) {
            val value = parent.getItemAtPosition(position).toString()
            seekBar.max = value.toInt()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}