package com.mycompany.recipeapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private val TAG = "SearchActivity"
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchT = intent.getStringExtra("searchTerm")
        val catT = intent.getStringExtra("catTerm")
        val recipeList = mutableListOf<Meals>()
        val adapter = MealDBAdapter(this, recipeList)
        searchRecycle.adapter = adapter
        searchRecycle.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mealdbAPI = retrofit.create(MealDBService::class.java)

        if (searchT != null) {
            mealdbAPI.search(searchT).enqueue(object : Callback<MealDBData> {
                override fun onResponse(call: Call<MealDBData>, response: Response<MealDBData>) {
                    Log.d(TAG, "onResponse: $response")
                    val body = response.body()

                    if (body == null) {
                        Log.w(TAG, "Valid response was not received")
                        return
                    }

                    if (body.meals.isNullOrEmpty())
                    {
                        val dialogBuilder = AlertDialog.Builder(this@SearchActivity)
                        val backIntent = Intent(this@SearchActivity, MainActivity::class.java)
                        dialogBuilder.setMessage("No recipes found")
                            .setCancelable(false)
                            .setNegativeButton("Okay", DialogInterface.OnClickListener {
                                    dialog, id -> startActivity(backIntent)
                            })

                        val alert = dialogBuilder.create()
                        alert.setTitle("Search Term or Location Missing")
                        alert.show()
                    }
                    else {
                        recipeList.addAll(body.meals)

                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<MealDBData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }

        if (catT != null) {
            mealdbAPI.catFilter(catT).enqueue(object : Callback<MealDBData> {
                override fun onResponse(call: Call<MealDBData>, response: Response<MealDBData>) {
                    Log.d(TAG, "onResponse: $response")
                    val body = response.body()

                    if (body == null) {
                        Log.w(TAG, "Valid response was not received")
                        return
                    }

                    /* Add rest of log.d later */
                    Log.d(TAG, ": ${body.meals.get(0).strMeal}")
                    Log.d(TAG, ": ${body.meals.get(0).strCategory}")

                    recipeList.addAll(body.meals)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<MealDBData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
        }
    }
}