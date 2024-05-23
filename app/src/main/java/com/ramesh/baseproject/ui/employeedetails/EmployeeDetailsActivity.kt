package com.ramesh.baseproject.ui.employeedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ramesh.baseproject.R
import com.ramesh.baseproject.data.remote.response.DataList
import com.ramesh.baseproject.databinding.ActivityEmployeedetailsBinding
import com.ramesh.baseproject.utils.common.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@AndroidEntryPoint
class EmployeeDetailsActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, EmployeeDetailsActivity::class.java)
            intent.putExtra(Constants.id, id)
            return intent
        }
    }

    private lateinit var empDetails: DataList
    private lateinit var binding: ActivityEmployeedetailsBinding
    val viewModel: EmployeeDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeedetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(Constants.id)
        viewModel.getEmployeeDetails(id!!)
        initializeObservers()
    }

    private fun initializeObservers() {

        viewModel.loading.observe(this, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            if (it) binding.progressBar.playAnimation()
            else binding.progressBar.cancelAnimation()
        })
        viewModel.getDataResponse.observe(this, Observer {
            empDetails = it.data?.list!!

            empDetails.run {
                binding.textId.text = id.toString()
                binding.textEmployeeName.text = employee_name.toString()
                binding.textEmployeeAge.text = employee_age.toString()
                binding.textEmployeeSalary.text = employee_salary.toString()
                Glide.with(this@EmployeeDetailsActivity).load(profile_image).placeholder(
                    R.mipmap.ic_launcher
                ).into(binding.imageLogo)

            }
        })

    }


}