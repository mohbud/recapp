package com.mycompany.recipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import com.squareup.picasso.Picasso

class MealDBAdapter(val context: Context, val meals: List<Meals>) :  RecyclerView.Adapter<MealDBAdapter.MyMealHolder>() {
    private val TAG = "MealDBAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMealHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return MyMealHolder(view)
    }

    override fun onBindViewHolder(holder: MyMealHolder, position: Int) {
        val currentItem = meals[position]

        holder.name.text = currentItem.strMeal
        holder.area.text = currentItem.strArea
        holder.categories.text = currentItem.strCategory
        holder.mealid.text = currentItem.idMeal.toString()
        Picasso.get().load(currentItem.strMealThumb).into(holder.image)
        holder.name.setOnClickListener { view ->
            val myIntent = Intent(context, RecipeActivity::class.java)

            myIntent.putExtra("recipeName", currentItem.strMeal)
            myIntent.putExtra("recipeId", currentItem.idMeal)
            myIntent.putExtra("instructions", currentItem.strInstructions)
            myIntent.putExtra("measure1", currentItem.strMeasure1)
            myIntent.putExtra("measure2", currentItem.strMeasure2)
            myIntent.putExtra("measure3", currentItem.strMeasure3)
            myIntent.putExtra("measure4", currentItem.strMeasure4)
            myIntent.putExtra("measure5", currentItem.strMeasure5)
            myIntent.putExtra("measure6", currentItem.strMeasure6)
            myIntent.putExtra("measure7", currentItem.strMeasure7)
            myIntent.putExtra("measure8", currentItem.strMeasure8)
            myIntent.putExtra("measure9", currentItem.strMeasure9)
            myIntent.putExtra("measure10", currentItem.strMeasure10)
            myIntent.putExtra("measure11", currentItem.strMeasure11)
            myIntent.putExtra("measure12", currentItem.strMeasure12)
            myIntent.putExtra("measure13", currentItem.strMeasure13)
            myIntent.putExtra("measure14", currentItem.strMeasure14)
            myIntent.putExtra("measure15", currentItem.strMeasure15)
            myIntent.putExtra("measure16", currentItem.strMeasure16)
            myIntent.putExtra("measure17", currentItem.strMeasure17)
            myIntent.putExtra("measure18", currentItem.strMeasure18)
            myIntent.putExtra("measure19", currentItem.strMeasure19)
            myIntent.putExtra("measure20", currentItem.strMeasure20)
            myIntent.putExtra("ingredient1", currentItem.strIngredient1)
            myIntent.putExtra("ingredient2", currentItem.strIngredient2)
            myIntent.putExtra("ingredient3", currentItem.strIngredient3)
            myIntent.putExtra("ingredient4", currentItem.strIngredient4)
            myIntent.putExtra("ingredient5", currentItem.strIngredient5)
            myIntent.putExtra("ingredient6", currentItem.strIngredient6)
            myIntent.putExtra("ingredient7", currentItem.strIngredient7)
            myIntent.putExtra("ingredient8", currentItem.strIngredient8)
            myIntent.putExtra("ingredient9", currentItem.strIngredient9)
            myIntent.putExtra("ingredient10", currentItem.strIngredient10)
            myIntent.putExtra("ingredient11", currentItem.strIngredient11)
            myIntent.putExtra("ingredient12", currentItem.strIngredient12)
            myIntent.putExtra("ingredient13", currentItem.strIngredient13)
            myIntent.putExtra("ingredient14", currentItem.strIngredient14)
            myIntent.putExtra("ingredient15", currentItem.strIngredient15)
            myIntent.putExtra("ingredient16", currentItem.strIngredient16)
            myIntent.putExtra("ingredient17", currentItem.strIngredient17)
            myIntent.putExtra("ingredient18", currentItem.strIngredient18)
            myIntent.putExtra("ingredient19", currentItem.strIngredient19)
            myIntent.putExtra("ingredient20", currentItem.strIngredient20)
            myIntent.putExtra("image", currentItem.strMealThumb)
            context.startActivity(myIntent)
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    inner class MyMealHolder (itemView: View): RecyclerView.ViewHolder (itemView)
    {
        val name = itemView.recipe_name
        val categories = itemView.recipe_category
        val area = itemView.area_recipe
        val image = itemView.rest_image
        val mealid = itemView.meal_id
    }
}

