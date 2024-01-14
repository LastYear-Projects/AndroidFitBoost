package com.example.firebasetest

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebasetest.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var isEditMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        disableEditModeFirst()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch and set your data
        fetchDataAndPopulateUI()

        // Set click listeners for buttons
        binding.btnEdit.setOnClickListener {
            toggleEditMode()
        }

        binding.btnSave.setOnClickListener {
            // Handle Save button click
            saveDataAndUpdateUI()
            toggleEditMode()
        }

        binding.btnCancel.setOnClickListener {
            // Handle Cancel button click
            fetchDataAndPopulateUI()
            toggleEditMode()
        }
    }

    private fun fetchDataAndPopulateUI() {
        // TODO: Implement your logic to fetch data and update UI
        // For demonstration purposes, setting placeholder values

        val fullName = "Your Full Name"
        val phoneNumber = "Your Phone Number"
        val email = "Your Email"
        val weight = "Your Weight"
        val height = "Your Height"
        val gender = "Your Gender"
        val age = "Your Age"

        binding.tvProfileName.text = createEditable(fullName)
        binding.tvProfilePhone.text = createEditable(phoneNumber)
        binding.tvProfileEmail.text = createEditable(email)
        binding.tvProfileWeight.text = createEditable(weight)
        binding.tvProfileHeight.text = createEditable(height)
        binding.tvProfileGender.text = createEditable(gender)
        binding.tvProfileAge.text = createEditable(age)
    }

    private fun disableEditModeFirst(){
        binding.tvProfileName.isEnabled = false
        binding.tvProfilePhone.isEnabled = false
        binding.tvProfileEmail.isEnabled = false
        binding.tvProfileWeight.isEnabled = false
        binding.tvProfileHeight.isEnabled = false
        binding.tvProfileGender.isEnabled = false
        binding.tvProfileAge.isEnabled = false
    }

    private fun createEditable(text: String): Editable {
        return Editable.Factory.getInstance().newEditable(text)
    }


    private fun toggleEditMode() {
        isEditMode = !isEditMode
        updateEditModeUI()
    }

    private fun updateEditModeUI() {
        val visibilityInEditMode = if (isEditMode) View.VISIBLE else View.GONE
        val visibilityInDisplayMode = if (isEditMode) View.GONE else View.VISIBLE

        binding.btnEdit.visibility = visibilityInDisplayMode
        binding.btnSave.visibility = visibilityInEditMode
        binding.btnCancel.visibility = visibilityInEditMode

        // Set editable or not editable based on the mode
        binding.tvProfileName.isEnabled = isEditMode
        binding.tvProfilePhone.isEnabled = isEditMode
        binding.tvProfileEmail.isEnabled = isEditMode
        binding.tvProfileWeight.isEnabled = isEditMode
        binding.tvProfileHeight.isEnabled = isEditMode
        binding.tvProfileGender.isEnabled = isEditMode
        binding.tvProfileAge.isEnabled = isEditMode
    }

    private fun saveDataAndUpdateUI() {
        // TODO: Implement your logic to save data
        // Update UI if needed after saving data
    }
}
