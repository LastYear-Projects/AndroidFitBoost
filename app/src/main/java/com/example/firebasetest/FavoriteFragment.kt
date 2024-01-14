package com.example.firebasetest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import com.example.firebasetest.databinding.FragmentFavoriteBinding
import com.example.firebasetest.databinding.FragmentHomeBinding


class FavoriteFragment : Fragment() {

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var binding: FragmentFavoriteBinding

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favorite, container, false)
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        button = binding.btnPickImage
        imageView = binding.imgSave

        button.setOnClickListener {
            pickImageGallery()
        }

        return binding.root
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView.setImageURI(data?.data)
    }

}