package com.express.android.lwfoodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.express.android.lwfoodorder.databinding.RecyclerRestaurantListRowBinding
import com.express.android.lwfoodorder.models.Hours
import com.express.android.lwfoodorder.models.RestaurantModel
import java.text.SimpleDateFormat
import java.util.*

class RestaurantListAdapter(val restaurantList: List<RestaurantModel?>?, val clickListener: RestaurantListClickListener): RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.MyViewHolder {
        return MyViewHolder(
            RecyclerRestaurantListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.MyViewHolder, position: Int) {
        holder.bind(restaurantList?.get(position))
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(restaurantList?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList?.size!!
    }

    inner class MyViewHolder(val binding: RecyclerRestaurantListRowBinding): RecyclerView.ViewHolder(binding.root){

        val thumbImage = binding.thumbImage
        val tvRestaurantName = binding.tvRestaurantName
        val tvRestaurantAddress = binding.tvRestaurantAddress
        val tvRestaurantHours = binding.tvRestaurantHours

        fun bind(restaurantModel: RestaurantModel?) {
            tvRestaurantName.text = restaurantModel?.name
            tvRestaurantAddress.text = "Address: " + restaurantModel?.address
            tvRestaurantHours.text = "Today's Hours: " + getTodaysHours(restaurantModel?.hours!!)

            Glide.with(thumbImage)
                .load(restaurantModel?.image)
                .into(thumbImage)
        }

        private fun getTodaysHours(hours: Hours): String? {
            val calendar: Calendar =  Calendar.getInstance()
            val date: Date = calendar.time
            val day : String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
            return when(day) {
                "Sunday" -> hours.Sunday
                "Monday" -> hours.Monday
                "Tuesday" -> hours.Tuesday
                "Wednesday" -> hours.Wednesday
                "Thursday" -> hours.Thursday
                "Friday" -> hours.Friday
                "Saturday" -> hours.Saturday
                else -> hours.Sunday
            }
        }
    }

    interface RestaurantListClickListener {
        fun onItemClick(restaurantModel: RestaurantModel)
    }
}