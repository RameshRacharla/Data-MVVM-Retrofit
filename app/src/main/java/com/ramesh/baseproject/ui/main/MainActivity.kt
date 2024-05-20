package com.ramesh.baseproject.ui.main

import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramesh.baseproject.data.remote.response.DataList
import com.ramesh.baseproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *  Author : @Ramesh Racharla
 *    private lateinit var binding: ActivitySplashBinding
 *     private lateinit var viewModel: SplashViewModel
 * */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataAdapter: DataAdapter
    private lateinit var dashboardManager: RecyclerView.LayoutManager
    private var dataList: ArrayList<DataList?> = ArrayList<DataList?>()
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);
        initializeRecyclerView()
        initializeObservers()
    }


    private fun initializeRecyclerView() {
        dataAdapter = DataAdapter(this, dataList)
        binding.rvDataitems.apply {
            dashboardManager = LinearLayoutManager(context)
            layoutManager = dashboardManager
            adapter = dataAdapter
        }
        viewModel.getEmployeeData()

    }

    private fun initializeObservers() {
        viewModel.loading.observe(this, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            if (it) binding.progressBar.playAnimation()
            else binding.progressBar.cancelAnimation()
        })
        viewModel.getDataResponse.observe(this, Observer {
            dataList = (it.data?.list as ArrayList<DataList?>?)!!

            it.data.run {
                if (dataList.isNotEmpty()) {
                    dataAdapter.onAddItems(dataList)
                    binding.rvDataitems.visibility = View.VISIBLE
                    binding.textNo.visibility = View.GONE
                } else {
                    binding.rvDataitems.visibility = View.GONE
                    binding.textNo.visibility = View.VISIBLE
                }
            }
        })

    }

}