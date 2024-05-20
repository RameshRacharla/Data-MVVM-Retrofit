package com.ramesh.baseproject.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramesh.baseproject.R
import com.ramesh.baseproject.data.remote.response.DataList
import com.ramesh.baseproject.databinding.AdapterDataBinding
import com.ramesh.baseproject.ui.employeedetails.EmployeeDetailsActivity
import com.ramesh.baseproject.utils.common.Toaster

class DataAdapter(
    val context: Context, var dataList: ArrayList<DataList?>
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DataViewHolder {
        val binding = AdapterDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataViewHolder, position: Int
    ) {

        try {
            holder.binding.textId.text = dataList[position]?.id.toString()
            holder.binding.textEmployeeName.text = dataList[position]?.employee_name
            holder.binding.textEmployeeAge.text = dataList[position]?.employee_age.toString()
            holder.binding.textEmployeeSalary.text = dataList[position]?.employee_salary.toString()
            Glide.with(context).load(dataList[position]?.profile_image).placeholder(
                R.mipmap.ic_launcher
            ).into(holder.binding.imageLogo)
            holder.itemView.setOnClickListener {
                val id = dataList[position]?.id.toString()
                Toaster.show(context,id)
                val intent = EmployeeDetailsActivity.newIntent(
                    context,
                    id
                )
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.stackTrace
        }

    }


    fun onAddItems(list: ArrayList<DataList?>) {
        this.dataList = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class DataViewHolder(val binding: AdapterDataBinding) : RecyclerView.ViewHolder(binding.root) {}

}

