package com.sum.room

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sum.room.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var userList = emptyList<User>()

    class ListHolder(private val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = userList[position]
        val textNumber = holder.itemView.findViewById<TextView>(R.id.textViewNumber)
        val customName = holder.itemView.findViewById<TextView>(R.id.textCustomName)
        val customSurname = holder.itemView.findViewById<TextView>(R.id.textCustomSurname)
        val customAge =holder.itemView.findViewById<TextView>(R.id.textCustomAge)

        textNumber.text = currentItem.id.toString()
        customName.text = currentItem.firstName
        customSurname.text = currentItem.lastName
        customAge.text = currentItem.age.toString()




    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return userList.size
    }
}