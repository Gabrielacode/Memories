package com.example.memories

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class MemoryListAdapter( var list :ArrayList<Memory>,  var onItemClick : (memory:Memory,view: View)->Unit ) : BaseAdapter() {
    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(p0: Int): Any {
         return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        Log.i("OnView","View Created")
        var customView:View
        if (p1 == null) {
        val inflater : LayoutInflater = p2?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val createdView = inflater.inflate(R.layout.memory_list_item,p2,false)
            val personalHolder = ViewHolder(createdView)
            personalHolder.titleView.text = list[p0].name
            personalHolder.descriptionTv.text = list[p0].description
            createdView.tag = personalHolder
            customView = createdView

        }else {

            val customHolder = p1.tag as ViewHolder
            customHolder.titleView.text = list[p0].name
            customHolder.descriptionTv.text = list[p0].description
            customView = p1
        }
        val parentAdapterView : AdapterView<BaseAdapter> = p2 as AdapterView<BaseAdapter>
        customView.setOnClickListener{
    Toast.makeText(p2.context,"Hello", Toast.LENGTH_SHORT).show()
    val specifiedMemory = getItem(p0) as Memory
    onItemClick(specifiedMemory,it)

}
        return customView
    }
}
class ViewHolder (var view :View ){
    var titleView = view.findViewById<TextView>(R.id.main_title_Tv)
    var descriptionTv = view.findViewById<TextView>(R.id.description_Tv)
    var checkBox : CheckBox = view.findViewById (R.id.memory_checkBox)
}