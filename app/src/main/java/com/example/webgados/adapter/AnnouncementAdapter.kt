package com.example.webgados.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webgados.R
import com.example.webgados.response.AdListItemResponse
import kotlinx.android.synthetic.main.announcement_list_item.view.*

class AnnouncementAdapter(
    private var cattle: List<AdListItemResponse>,
) : RecyclerView.Adapter<AnnouncementAdapter.CattleAdsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CattleAdsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_list_item, parent, false)
        return CattleAdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CattleAdsViewHolder, position: Int) {
        holder.bindView(cattle[position])
    }

    override fun getItemCount(): Int {
        return cattle.size
    }

    class CattleAdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.announcement_title_description_text_view
        private val months = itemView.announcement_months_text_view
        private val atsign = itemView.announcement_at_sign_text_view
        private val price = itemView.announcement_price_text_view
        private val broker = itemView.announcement_broker_name_text_view
        private val localization = itemView.announcement_localization_text_view



        fun bindView(dice: AdListItemResponse) {
            title.text = dice.title
            months.text = dice.age
            atsign.text = dice.weight
            price.text = dice.price
            broker.text = dice.broker
            localization.text = dice.city

        }
    }

}