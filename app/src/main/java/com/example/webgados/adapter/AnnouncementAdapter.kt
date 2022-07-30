package com.example.webgados.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webgados.R
import com.example.webgados.databinding.AnnouncementListItemBinding
import com.example.webgados.response.AdListItemResponse

class AnnouncementAdapter(
    private var cattle: List<AdListItemResponse>,
) : RecyclerView.Adapter<AnnouncementAdapter.CattleAdsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CattleAdsViewHolder {
        val view = AnnouncementListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return CattleAdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CattleAdsViewHolder, position: Int) {
        holder.bindView(cattle[position])
    }

    override fun getItemCount(): Int {
        return cattle.size
    }

    class CattleAdsViewHolder(binding: AnnouncementListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adphoto = binding.announcementAdPhotoImageView
        private val quantity = binding.announcementQuantityTextView
        private val title = binding.announcementTitleDescriptionTextView
        private val months = binding.announcementNumberOfMonthsTextView
        private val atsign = binding.announcementNumberOfArrobasTextView
        private val price = binding.announcementValueTextView
        private val evaluation = binding.announcementBrokerTextView
        private val broker = binding.announcementBrokerNameTextView
        private val localization = binding.announcementCityTextView
        private val state = binding.announcementStateTextView

        fun bindView(item: AdListItemResponse) {
            Glide.with(this@CattleAdsViewHolder.itemView).load(item.image_url).into(adphoto)
            quantity.text = item.quantity
            title.text = item.title
            broker.text = item.broker
            localization.text = item.city
            state.text = item.state
            months.text = itemView.context.getString(R.string.announcement_months_text, item.age)
            atsign.text =
                itemView.context.getString(R.string.announcement_arrobas_text, item.weight)
            price.text = itemView.context.getString(R.string.announcement_coin_text, item.price)
            evaluation.text = itemView.context.getString(
                R.string.announcement_broker_text,
                item.broker_evaluation
            )
        }
    }
}