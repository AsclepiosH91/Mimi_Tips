package com.example.mimi_tips

/**
 * Data model for each row of the RecyclerView.
 */
internal class Sport
/**
 * Constructor for the Sport data model
 * @param title The name if the sport.
 * @param info Information about the sport.
 * @param imageResource Image resource of the sport.
 */
    (//Member variables representing the title, information and image about the sport
    /**
     * Gets the title of the sport
     * @return The title of the sport.
     */
    val title: String,
    /**
     * Gets the info about the sport
     * @return The info about the sport.
     */
    val info: String,
    /**
     * Gets the image resource of the sport
     * @return The image info of the sport.
     */
    private val imageResource: Int) {


    fun getImageResource(): Int {
        return imageResource
    }
}