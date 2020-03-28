package com.example.flicker_search_photos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flicker_search_photos.R
import com.example.flicker_search_photos.adapter.ImageListAdapter
import com.example.flicker_search_photos.component.EndlessRecyclerViewScrollListener
import com.example.flicker_search_photos.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var searchEditTextView: EditText
    private lateinit var searchIconImageView: ImageView
    private lateinit var initialTextContainer: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter
    private lateinit var endlessScrollListener: EndlessRecyclerViewScrollListener
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupUi()
        setupClickListener()
        setupRecyclerView()
    }

    private fun setupUi(){
        searchEditTextView = search_edit_text
        searchIconImageView = search_icon_image_view
        initialTextContainer = initial_text_container
        recyclerView = search_result_recycler_view
        progressBar = progress_bar
    }

    private fun setupClickListener(){
        searchIconImageView.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.totalPageFetched = 0
            viewModel.totalPage = 0
            viewModel.searchTag = searchEditTextView.text.toString()
            viewModel.getPhotosBySearchTerm(this, {
                initialTextContainer.visibility = View.GONE
                progressBar.visibility = View.GONE
                adapter.notifyDataSetChanged()
            }, {
                Toast.makeText(this, "failed to load the data", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this, 2)
        endlessScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getPhotosBySearchTerm(this@MainActivity, {
                    adapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(this@MainActivity, "failed to load the data", Toast.LENGTH_LONG).show()
                })
            }
        }
        adapter = ImageListAdapter(this, viewModel.flickrPhotoResponseList, viewModel.totalPageFetched, viewModel.totalPage, viewModel.perPage)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(endlessScrollListener)
        recyclerView.adapter = adapter
    }
}
