package com.example.webgados.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webgados.R
import com.example.webgados.response.AdListItemResponse
import kotlinx.android.synthetic.main.announcement_list_item.view.*

class AnnouncementAdapter(
    private var cattle: List<AdListItemResponse>,
) : RecyclerView.Adapter<AnnouncementAdapter.CattleAdsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CattleAdsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.announcement_list_item, parent, false)
        return CattleAdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CattleAdsViewHolder, position: Int) {
        holder.bindView(cattle[position])
    }

    override fun getItemCount(): Int {
        return cattle.size
    }

    class CattleAdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ad_photo = itemView.announcement_ad_photo_image_view
        private val quantity = itemView.announcement_quantity_text_view
        private val title = itemView.announcement_title_description_text_view
        private val months = itemView.announcement_number_of_months_text_view
        private val at_sign = itemView.announcement_number_of_arrobas_text_view
        private val price = itemView.announcement_price_text_view
        private val evaluation = itemView.announcement_evaluation_text_view
        private val broker = itemView.announcement_broker_name_text_view
        private val localization = itemView.announcement_City_text_view
        private val state = itemView.announcement_state_text_view


        fun bindView(dice: AdListItemResponse) {
            quantity.text = dice.quantity
            title.text = dice.title
            months.text = dice.age
            at_sign.text = dice.weight
            price.text = dice.price
            broker.text = dice.broker
            localization.text = dice.city
            state.text = dice.state
            evaluation.text = dice.broker_evaluation
            val imageUrl = dice.image_url
            getUrl(imageUrl.toString())
        }

        fun getUrl(imageUrl: String) {
            val url = imageUrl

            Glide.with(this@CattleAdsViewHolder.itemView).load(url).into(ad_photo)

        }
    }

}