package com.arsy.testscreeningsatu.ui.guest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.arsy.testscreeningsatu.R
import com.arsy.testscreeningsatu.data.Guest
import com.arsy.testscreeningsatu.databinding.ActivityGuestBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray


class GuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding
    val guestAdapter = GuestAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.guest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getGuestData()

        binding.swipeContainer.setOnRefreshListener {
            getGuestData()
        }

        binding.swipeContainer.setColorSchemeResources(
            R.color.purple_700,
            R.color.teal_200
        )
    }

    private fun getGuestData() {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://www.mocky.io/v2/596dec7f0f000023032b8017"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                // Jika koneksi berhasil
                binding.progressBar.visibility = View.INVISIBLE

                val listItems: ArrayList<Guest> = ArrayList()
                val result = String(responseBody)
                Log.d("GUEST", result)

                // Parsing JSON
                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val guest = Guest()
                        guest.name = jsonObject.getString("name")
                        guest.birthdate = jsonObject.getString("birthdate")
                        listItems.add(guest)
                    }

                    binding.rvGuest.layoutManager = GridLayoutManager(this@GuestActivity, 2)
                    guestAdapter.clear()
                    guestAdapter.setGuests(listItems)
                    binding.rvGuest.adapter = guestAdapter
                    binding.rvGuest.setHasFixedSize(true)
                    binding.swipeContainer.isRefreshing = false
                } catch (e: Exception) {
                    Toast.makeText(this@GuestActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                // Jika koneksi gagal
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@GuestActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}