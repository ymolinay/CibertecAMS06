package com.cibertec.myciberapps06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibertec.myciberapps06.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tourismAdapter: TourismAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllPLaces()
    }

    fun getAllPLaces() {
        ClientIRetrofit.api.getAllTourismItems()
            .enqueue(object : Callback<List<Tourism>> {
                override fun onResponse(
                    call: Call<List<Tourism>>,
                    response: Response<List<Tourism>>
                ) {

                    val tourismList = response.body() ?: emptyList()
                    binding.tourismRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    tourismAdapter = TourismAdapter(tourismList) { selectedItem ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("ITEM_ID", selectedItem.id)
                        startActivity(intent)
                    }
                    binding.tourismRecyclerView.adapter = tourismAdapter

                }

                override fun onFailure(call: Call<List<Tourism>>, t: Throwable) {

                }


            })
    }
}