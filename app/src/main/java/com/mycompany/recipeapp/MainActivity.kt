package com.mycompany.hw5_yelpclone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import retrofit2.Retrofit
import android.content.DialogInterface
import android.view.inputmethod.InputMethodManager
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.edamam.com"
    private val TAG = "MainActivity"
    private val APP_KEY = "2df4bd897b2fe0932b245b5d99f613f3"
    private val APP_ID = "c39fff63"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun searchRecipe(view: View)
    {
        if (foodText.text.toString() == "")
        {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Search term or location cannot be empty. Please enter a search term or location")
                .setCancelable(false)
                .setNegativeButton("Okay", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Search Term or Location Missing")
            alert.show()
        }
        else
        {
            val search_recipe = foodText.text.toString()
            val recipeList = mutableListOf<Hit>()
            val adapter = RecipeAdapter(this, recipeList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val edamamAPI = retrofit.create(EdamamService::class.java)

            edamamAPI.search(search_recipe, APP_ID, APP_KEY).enqueue(object: Callback<Hits>
            {
                override fun onResponse(call: Call<Hits>, response: Response<Hits>) {
                    Log.d(TAG,"onResponse: $response")
                    val body = response.body()

                    if (body == null)
                    {
                        Log.w(TAG, "Valid response was not received")
                        return
                    }

                    /* Add rest of log.d later */
                    Log.d(TAG, ": ${body.hits.get(0).recipe.label}")
                    Log.d(TAG, ": ${body.hits.get(0).recipe.image}")

                    recipeList.add(body.hits[0])
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Hits>, t: Throwable)
                {
                    Log.d(TAG, "onFailure: $t")
                }
            })

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
