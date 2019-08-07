package com.example.mimi_tips

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import java.util.ArrayList
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView


/***
 * The adapter class for the RecyclerView, contains the sports data
 */
internal class RecipeAdapter
/**
 * Constructor that passes in the sports data and the context
 * @param sportsData ArrayList containing the sports data
 * @param context Context of the application
 */
    (private val mContext: Context, //Member variables
     private val mRecipeData: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    override  fun getItemCount(): Int {
        return mRecipeData.size
    }

    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        //Get current sport
        val currentRecipe = mRecipeData[position]

        //Populate the imageviews with data image using Glide Library
        Glide.with(mContext).load(currentRecipe.getImageResource()).into(holder.mRecipeImage)
        //Populate the textviews with data
        holder.bindTo(currentRecipe)
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    internal inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        private var mTitleText: TextView
        private var mInfoText: TextView

        //Add a variable in the RecipeAdapter class, ViewHolder class for the ImageView, and initialize it in the ViewHolder constructor
        var mRecipeImage : ImageView


        init {
            //Member Variables for the TextViews
            mTitleText = itemView.findViewById(R.id.title)
            mInfoText = itemView.findViewById(R.id.subTitle)
            mRecipeImage = itemView.findViewById(R.id.sportsImage)

            itemView.setOnClickListener{ _: View ->
                //var sportPosition: Int = this.adapterPosition
                val currentRecipe : Recipe = mRecipeData[adapterPosition]

                val detailIntent = Intent(mContext, DetailActivity::class.java)
                detailIntent.putExtra("title", currentRecipe.title)
                detailIntent.putExtra("detail", currentRecipe.detail)
                detailIntent.putExtra("image_resource", currentRecipe.getImageResource())

                mContext.startActivity(detailIntent)
            }

        }

        fun bindTo(currentRecipe: Recipe) {
            //Populate the textviews with data
            mTitleText.text = currentRecipe.title
            mInfoText.text = currentRecipe.info
            //mDetailText.text = currentRecipe.detail

        }




    }
}
