package com.example.mimi_tips

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Member variables
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSportsData: ArrayList<Sport>
    private lateinit var mAdapter: SportsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Get the integer from the integers.xml resource file
        val gridColumnCount = resources.getInteger(R.integer.grid_column_count)



        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


        //Initialize the RecyclerView
        mRecyclerView = this.findViewById(R.id.recyclerView)

        //Set the Layout Manager
        mRecyclerView.layoutManager = GridLayoutManager(this,gridColumnCount)

        //Initialize the ArrayLIst that will contain the data
        mSportsData = ArrayList()

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = SportsAdapter(this, mSportsData)
        mRecyclerView.adapter = mAdapter

        //Get the data
        initializeData()


        // Use the gridColumnCount variable to disable the swipe action when there is more than one column
        val mSwipeDirs = when {
            gridColumnCount > 1 -> 0
            else -> ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        }


        //Create a new instance of ItemTouchHelper.SimpleCallback that define what happens to RecyclerView list items when the user performs various touch actions, such as swipe, or drag and drop
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP, mSwipeDirs) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(mSportsData, from, to)
                mAdapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mSportsData.removeAt(viewHolder.adapterPosition)
                mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }

        //After creating the new ItemTouchHelper object in the Main Activity's onCreate() method, call attachToRecyclerView() on the ItemTouchHelper instance to add it to your RecyclerView
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)

    }

    /**
     * Method for initializing the sports data from resources.
     */
    private fun initializeData() {
        //Get the resources from the XML file
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)
        val sportsImageResources = resources.obtainTypedArray(R.array.sports_images)

        //Clear the existing data (to avoid duplication)
        mSportsData.clear()

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for (i in sportsList.indices) {
            mSportsData.add(Sport(sportsList[i], sportsInfo[i], sportsImageResources.getResourceId(i,0)))
        }
        //Clean up the data in the typed array once you have created the Sport data ArrayList
        sportsImageResources.recycle()

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged()
    }

    private fun resetSports() {
        initializeData()
    }




















    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
