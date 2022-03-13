package android.example.islamiproject.radio

import android.example.islamiproject.R
import android.example.islamiproject.api.ApiManager
import android.example.islamiproject.api.responses.RadiosChannelResponse
import android.example.islamiproject.api.responses.RadiosItem
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.activity_radio.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RadioActivity : AppCompatActivity() {

    lateinit var adapter: RadioChannelRecyclerAdapter
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio)
        initRecyclerView()
        getRadioChannels()
    }

    private fun initRecyclerView() {
        adapter = RadioChannelRecyclerAdapter()
        radioChannelRecyclerView.adapter = adapter
        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(radioChannelRecyclerView)
        adapter.setOnPlayClick(object : RadioChannelRecyclerAdapter.onPlayClickListener {
            override fun onItemClick(position: Int, channel: RadiosItem) {
                channel.radioUrl?.let { playChannel(it) }
            }

        })
        adapter.setOnStopClick(object : RadioChannelRecyclerAdapter.onStopClickListener {
            override fun onItemClick(position: Int, channel: RadiosItem) {
                stopChannel()
            }

        })
    }

    private fun getRadioChannels() {
        ApiManager.getApis()
            .getRadiosChannel()
            .enqueue(object : Callback<RadiosChannelResponse> {
                override fun onFailure(call: Call<RadiosChannelResponse>, t: Throwable) {
                    Toast.makeText(this@RadioActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                    progressBar.visibility = View.GONE

                }

                override fun onResponse(
                    call: Call<RadiosChannelResponse>,
                    response: Response<RadiosChannelResponse>
                ) {
                    progressBar.visibility = View.GONE
                    //retrieve data
                    if (response.isSuccessful) {
                        Log.e("radios", "radios " + response.body()?.radios)

                        response.body()?.radios?.let { adapter.changeData(it) }
                    }
                    //retrieve error
                    else {
                        Toast.makeText(this@RadioActivity, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }


                }

            })
    }

    fun playChannel(url: String) {
        stopChannel()
        mediaPlayer = MediaPlayer()

        try {
            mediaPlayer?.setDataSource(url)
            mediaPlayer?.prepareAsync()
            mediaPlayer?.setOnPreparedListener {
                it.start()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this@RadioActivity, "can't play channel ", Toast.LENGTH_LONG).show()

        }

    }

    fun stopChannel() {
        if (mediaPlayer == null) {
            return
        } else {
            mediaPlayer?.stop()
            mediaPlayer?.release()

        }
    }

}