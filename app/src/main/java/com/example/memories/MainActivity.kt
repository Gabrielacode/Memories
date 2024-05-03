package com.example.memories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.children
import com.example.memories.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     lateinit var memorylist : ArrayList<Memory>
     lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        memorylist = ArrayList()
   

        mainBinding.memoryLane.adapter = MemoryListAdapter(memorylist){memory, view ->
            val intent = Intent(this@MainActivity, ViewMemoryActivity::class.java)
            intent.putExtra(MemoryIntent.MEMORY_TITLE.name,memory.name)
            intent.putExtra(MemoryIntent.MEMORY_DESCRIPTION.name,memory.description)
            startActivity(intent)
        }

        val addMemoryActivityLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val memory = Memory("","").apply {
                val dataIntent = it.data
                name = dataIntent?.getStringExtra(MemoryIntent.MEMORY_TITLE.name)?: "Default Memory Name"
                description = dataIntent?.getStringExtra(MemoryIntent.MEMORY_DESCRIPTION.name)?: " Default Memory Description"
            }
              val adapter = mainBinding.memoryLane.adapter as MemoryListAdapter
            adapter.list.add(memory)
            adapter.notifyDataSetChanged()
        }

       mainBinding.addButton.setOnClickListener {
           Intent(this@MainActivity,AddMemoryActivity::class.java).also {
               addMemoryActivityLaucher.launch(it)
           }
       }

        mainBinding.deleteButton.setOnClickListener {
            val adapter = mainBinding.memoryLane.adapter as MemoryListAdapter
            val currentListItems =  mainBinding.memoryLane.children
            //Get removed listView items
            val removedListItems = currentListItems.filter {
                val viewHolder = it.tag as ViewHolder

                viewHolder.checkBox.isChecked
            }

            // Get thier respective memories
            val removedMemories  = (removedListItems.map { mainBinding.memoryLane.getPositionForView(it) }.map { adapter.list[it] }).toMutableList()
            removedMemories.forEach { Log.i("Test",it.toString()) }
            adapter.list.removeAll(removedMemories)
            adapter.notifyDataSetChanged()
        }
        }





    }

