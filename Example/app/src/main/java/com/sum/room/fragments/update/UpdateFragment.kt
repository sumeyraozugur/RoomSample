package com.sum.room.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.sum.room.R
import com.sum.room.databinding.FragmentAddBinding
import com.sum.room.databinding.FragmentUpdateBinding
import com.sum.room.model.User
import com.sum.room.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())

        binding.updateButton.setOnClickListener {
            updateItem()

        }

    }





    private fun updateItem(){
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = Integer.parseInt(binding.updateAge.text.toString())

        if(inputCheck(firstName,lastName,binding.updateAge.text )){
            //Create User Object
            val updateUser = User(args.currentUser.id,firstName,lastName,age)
            //Update Current User
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"Updated Successfully!",Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields!",Toast.LENGTH_SHORT).show()

        }
    }



    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}