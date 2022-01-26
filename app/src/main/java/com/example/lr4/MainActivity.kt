package com.example.lr4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lr4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Личности"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = verticalLinearLayoutManager
        binding.recyclerView.adapter = UsersAdapter(
            PicturesHolder.createCollectionPictures(),
            ::showSnackbar,
            ::showSnackbarLike)
    }

    private fun showSnackbar(picture: Picture) =
        Snackbar.make(binding.root, "Нажата карточка " + picture.name, 1000).show()

    private fun showSnackbarLike(picture: Picture) =
        Snackbar.make(binding.root, "Нажат лайк " + picture.name, 1000).show()

}