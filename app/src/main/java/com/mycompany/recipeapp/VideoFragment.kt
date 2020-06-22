package com.mycompany.recipeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_video.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideoFragment : Fragment() {
    private val TAG = "VideoFragment"
    private val YOUTUBE_API_KEY: String = "AIzaSyBpae5ddvOl15EOHS6AL96-sy2WeClLgjs"

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        return view
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart")
        load()
    }

    fun load()
    {
        val url = "https://www.googleapis.com/youtube/v3/"
        val searchVideo = arguments!!.getString("recipeName", "")
        val maxResults = 15
        val order = "rating"
        val videoList = ArrayList<Items>()
        val vidAdapter = VideoAdapter(videoList)
        videos_list.adapter = vidAdapter
        videos_list.layoutManager = LinearLayoutManager(context)

        val request = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youTubeAPI = request.create(YouTubeService::class.java)
        youTubeAPI.youtubeSearch(maxResults, order, searchVideo, YOUTUBE_API_KEY).enqueue(object: Callback<Kind>
        {
            override fun onResponse(call: Call<Kind>, response: Response<Kind>) {
                Log.d(TAG,"onResponse: $response")
                val body = response.body()

                if (body == null)
                {
                    Log.w(TAG, "Valid response was not received")
                    return
                }

                videoList.addAll(body.items)
                vidAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Kind>, t: Throwable)
            {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }
}