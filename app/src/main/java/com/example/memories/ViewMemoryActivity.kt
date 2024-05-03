package com.example.memories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memories.databinding.ActivityViewMemoryBinding

class ViewMemoryActivity : AppCompatActivity() {
     lateinit var viewMemoryBinding : ActivityViewMemoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMemoryBinding = ActivityViewMemoryBinding.inflate(layoutInflater)
        setContentView(viewMemoryBinding.root)

     val intent = intent
        var selectedMemoryTitle = intent.getStringExtra(MemoryIntent.MEMORY_TITLE.name)
        var selectedMemoryDescription = intent.getStringExtra(MemoryIntent.MEMORY_DESCRIPTION.name)

        viewMemoryBinding.memoryTitle.text = selectedMemoryTitle
        viewMemoryBinding.memoryDescription.text = selectedMemoryDescription
    }
}