package com.sum.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sum.room.R
import com.sum.room.model.User
import com.sum.room.viewmodel.UserViewModel
import com.sum.room.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addButton.setOnClickListener {
             insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstName.text.toString()
        val lastName = binding.addLastName.text.toString()
        val age = binding.addAge.text

        if (inputCheck(firstName, lastName, age)) {
            //Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}