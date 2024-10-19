package com.cibertec.myciberapps06

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.myciberapps06.databinding.ActivityDetailBinding
import com.cibertec.myciberapps06.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPlace(intent.getIntExtra("ITEM_ID", 0))
    }

    private fun getPlace(placeId: Int) {
        ClientIRetrofit.api.getTourismById(placeId)
            .enqueue(object : Callback<Tourism> {
                override fun onResponse(
                    call: Call<Tourism>,
                    response: Response<Tourism>
                ) {
                    val place = response.body()
                    binding.detailTourismName.text = place?.name
                    binding.detailTourismDescription.text = place?.description

                    binding.locationChip.text = place?.location
                    binding.locationChip.visibility = View.VISIBLE

                    Picasso.get()
                        .load(place?.imageUrl)
                        .placeholder(R.drawable.ic_placeholder_tourism)
                        .error(R.drawable.ic_placeholder_error)
                        .into(binding.detailImage)

                }

                override fun onFailure(call: Call<Tourism>, t: Throwable) {

                }


            })
    }
}