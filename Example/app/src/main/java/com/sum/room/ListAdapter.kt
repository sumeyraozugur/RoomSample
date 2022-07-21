package com.sum.room

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sum.room.databinding.CustomRowBinding
import com.sum.room.fragments.list.ListFragment
import com.sum.room.fragments.list.ListFragmentDirections
import com.sum.room.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var userList = arrayListOf<User>()

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
        val rowLayout = holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout)

        textNumber.text = currentItem.id.toString()
        customName.text = currentItem.firstName
        customSurname.text = currentItem.lastName
        customAge.text = currentItem.age.toString()

        rowLayout.setOnClickListener {
            val action =  ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }








    }

    fun updateList(user: List<User>) {
        userList.clear()
        userList.addAll(user)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return userList.size
    }
}