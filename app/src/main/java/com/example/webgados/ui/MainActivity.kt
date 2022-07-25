package com.example.webgados.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webgados.R
import com.example.webgados.adapter.AnnouncementAdapter
import com.example.webgados.databinding.ActivityMainBinding
import com.example.webgados.response.ListOfAdsResponse
import com.example.webgados.utils.RetrofitConfig
import com.example.webgados.utils.showAlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        requestAdList()
    }

    private fun setupRecyclerView() {
        with(binding.mainRecyclerView) {
            layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
            setHasFixedSize(true)
        }
    }

    private fun requestAdList() {
        binding.mainProgressBar.isVisible = true
        val announcementService = RetrofitConfig.getRetrofit().getListAnnouncement()
        val call: Call<ListOfAdsResponse> = announcementService

        call.enqueue(object : Callback<ListOfAdsResponse> {
            override fun onResponse(
                call: Call<ListOfAdsResponse>,
                response: Response<ListOfAdsResponse>
            ) {
                binding.mainProgressBar.isVisible = false
                handleAdListResponse(response)
            }

            override fun onFailure(call: Call<ListOfAdsResponse>, t: Throwable) {
                binding.mainProgressBar.isVisible = false
                handleAdListDataFailure(t)
            }
        })
    }

    private fun handleAdListDataFailure(throwable: Throwable) {
        if (throwable is IOException) {
            showAlertDialog(getString(R.string.no_internet_connection_error)) {
                requestAdList()
            }
        } else {
            showAlertDialog(getString(R.string.generic_error)) {
                requestAdList()
            }
        }
    }

    private fun handleAdListResponse(response: Response<ListOfAdsResponse>) {
        if (response.isSuccessful && response.body() != null) {
            binding.mainRecyclerView.adapter = AnnouncementAdapter(
                response.body()?.cattle ?: return
            )
        } else {
            showAlertDialog(getString(R.string.server_error)) {
                requestAdList()
            }
        }
    }
}