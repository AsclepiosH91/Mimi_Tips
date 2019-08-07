package com.example.mimi_tips


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //initialize the ImageView, title TextView and detail TextView
        val recipeTitle : TextView = findViewById<View>(R.id.titleDetail) as TextView
        val recipeDetail : TextView = findViewById(R.id.subTitleDetail) as TextView
        val recipeImage : ImageView = findViewById<View>(R.id.sportsImageDetail) as ImageView

        //Get the title from the incoming Intent and set it to the TextView
        recipeTitle.text = intent.getStringExtra("title")
        recipeDetail.text = intent.getStringExtra("detail")

        //Use Glide to load the image into the ImageView
        Glide.with(this).load(intent.getIntExtra("image_resource",0)).into(recipeImage)

    }
}
