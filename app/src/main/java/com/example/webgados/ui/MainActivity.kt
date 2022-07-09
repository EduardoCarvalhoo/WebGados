package com.example.webgados.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webgados.R
import com.example.webgados.adapter.AnnouncementAdapter
import com.example.webgados.response.AdListItemResponse
import com.example.webgados.response.ListOfAdsResponse
import com.example.webgados.utils.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAdList()

    }


    private fun setupRecyclerView(result: List<AdListItemResponse>) {
        main_recycler_view.layoutManager = LinearLayoutManager(
            this@MainActivity, RecyclerView.VERTICAL, false
        )
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.adapter = AnnouncementAdapter(result)
    }

    private fun requestAdList() {

        val announcementService = RetrofitConfig.getRetrofit().getListAnnouncement()
        val call: Call<ListOfAdsResponse> = announcementService

        call.enqueue(object : Callback<ListOfAdsResponse> {
            override fun onResponse(
                call: Call<ListOfAdsResponse>,
                response: Response<ListOfAdsResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.cattle
                    if (result != null) {
                        setupRecyclerView(result)
                    }
                }
            }

            override fun onFailure(call: Call<ListOfAdsResponse>, t: Throwable) {

            }
        })
    }
}