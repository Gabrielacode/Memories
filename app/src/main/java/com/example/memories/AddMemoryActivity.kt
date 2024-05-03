package com.example.memories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memories.databinding.ActivityAddMemoryBinding

class AddMemoryActivity : AppCompatActivity() {
     lateinit var presentBinding : ActivityAddMemoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentBinding = ActivityAddMemoryBinding.inflate(layoutInflater)
        setContentView(presentBinding.root)
     // Get the intent that started this Activity

        presentBinding.submitMemory.setOnClickListener {
            if(presentBinding.nameMemory.text.isNotEmpty() && presentBinding.descriptionMemory.text.isNotEmpty()){
              val resultIntent = Intent()
                resultIntent.putExtra(MemoryIntent.MEMORY_TITLE.name,presentBinding.nameMemory.text.toString())
                resultIntent.putExtra(MemoryIntent.MEMORY_DESCRIPTION.name,presentBinding.descriptionMemory.text.toString())

                setResult(RESULT_OK,resultIntent)
                finish()
            }
            else{
                presentBinding.nameMemory.error = "Input some text"
                presentBinding.descriptionMemory.error = "Input some text"
            }
        }

    }
}