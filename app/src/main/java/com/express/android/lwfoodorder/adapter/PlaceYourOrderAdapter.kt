package com.express.android.lwfoodorder.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.express.android.lwfoodorder.databinding.PlaceyourorderListRowBinding
import com.express.android.lwfoodorder.models.Menus

class PlaceYourOrderAdapter(val menuList: List<Menus?>?) : RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceYourOrderAdapter.MyViewHolder {
        return MyViewHolder(
            PlaceyourorderListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaceYourOrderAdapter.MyViewHolder, position: Int) {
        holder.bind(menuList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(menuList == null) 0 else menuList.size
    }

    inner class MyViewHolder(val binding: PlaceyourorderListRowBinding): RecyclerView.ViewHolder(binding.root) {

        val thumbImage = binding.thumbImage
        val menuName = binding.menuName
        val menuPrice = binding.menuPrice
        val menuQty = binding.menuQty

        fun bind(menu: Menus) {
            menuName.text = menu?.name!!
            menuPrice.text = "Price $" + String.format("%.2f", menu?.price * menu.totalInCart)
            menuQty.text = "Qty :" + menu?.totalInCart

            Glide.with(thumbImage)
                .load(menu?.url)
                .into(thumbImage)
        }
    }
}