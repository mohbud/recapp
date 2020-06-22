package com.mycompany.recipeapp
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment() {

    private val TAG = "RecipeFragment"
    private val ingredient_list = ArrayList<String>()

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        return view
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart")
        load()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Stop")
        ingredient_list.clear()
    }

    fun load()
    {
        val mealName = arguments?.getString("recipeName", "b")
        meal_title.text = mealName
        val mealImage = arguments?.getString("image", " ")
        Picasso.get().load(mealImage).into(meal_image)
        val measures1 = arguments?.getString("measure1", "")
        val measures2 = arguments?.getString("measure2", "")
        val measures3 = arguments?.getString("measure3", "")
        val measures4 = arguments?.getString("measure4", "")
        val measures5 = arguments?.getString("measure5", "")
        val measures6 = arguments?.getString("measure6", "")
        val measures7 = arguments?.getString("measure7", "")
        val measures8 = arguments?.getString("measure8", "")
        val measures9 = arguments?.getString("measure9", "")
        val measures10 = arguments?.getString("measure10", "")
        val measures11 = arguments?.getString("measure11", "")
        val measures12 = arguments?.getString("measure12", "")
        val measures13 = arguments?.getString("measure13", "")
        val measures14 = arguments?.getString("measure14", "")
        val measures15 = arguments?.getString("measure15", "")
        val measures16 = arguments?.getString("measure16", "")
        val measures17 = arguments?.getString("measure17", "")
        val measures18 = arguments?.getString("measure18", "")
        val measures19 = arguments?.getString("measure19", "")
        val measures20 = arguments?.getString("measure20", "")
        val ingredients1 = arguments?.getString("ingredient1", "")
        val ingredients2 = arguments?.getString("ingredient2", "")
        val ingredients3 = arguments?.getString("ingredient3", "")
        val ingredients4 = arguments?.getString("ingredient4", "")
        val ingredients5 = arguments?.getString("ingredient5", "")
        val ingredients6 = arguments?.getString("ingredient6", "")
        val ingredients7 = arguments?.getString("ingredient7", "")
        val ingredients8 = arguments?.getString("ingredient8", "")
        val ingredients9 = arguments?.getString("ingredient9", "")
        val ingredients10 = arguments?.getString("ingredient10", "")
        val ingredients11 = arguments?.getString("ingredient11", "")
        val ingredients12 = arguments?.getString("ingredient12", "")
        val ingredients13 = arguments?.getString("ingredient13", "")
        val ingredients14 = arguments?.getString("ingredient14", "")
        val ingredients15 = arguments?.getString("ingredient15", "")
        val ingredients16 = arguments?.getString("ingredient16", "")
        val ingredients17 = arguments?.getString("ingredient17", "")
        val ingredients18 = arguments?.getString("ingredient18", "")
        val ingredients19 = arguments?.getString("ingredient19", "")
        val ingredients20 = arguments?.getString("ingredient20", "")

        ingredient_list.add("$measures1 $ingredients1")
        ingredient_list.add("$measures2 $ingredients2")
        ingredient_list.add("$measures3 $ingredients3")
        ingredient_list.add("$measures4 $ingredients4")
        if (measures5!!.isNotBlank()) {
            ingredient_list.add("$measures5 $ingredients5")
            if (measures6!!.isNotBlank()) {
                ingredient_list.add("$measures6 $ingredients6")
                if (measures7!!.isNotBlank()) {
                    ingredient_list.add("$measures7 $ingredients7")
                    if (measures8!!.isNotBlank()) {
                        ingredient_list.add("$measures8 $ingredients8")
                        if (measures9!!.isNotBlank()) {
                            ingredient_list.add("$measures9 $ingredients9")
                            if (measures10!!.isNotBlank()) {
                                ingredient_list.add("$measures10 $ingredients10")
                                if (measures11!!.isNotBlank()) {
                                    ingredient_list.add("$measures11 $ingredients11")
                                    if (measures12!!.isNotBlank()) {
                                        ingredient_list.add("$measures12 $ingredients12")
                                        if (measures13!!.isNotBlank()) {
                                            ingredient_list.add("$measures13 $ingredients13")
                                            if (measures14!!.isNotBlank()) {
                                                ingredient_list.add("$measures14 $ingredients14")
                                                if (measures15!!.isNotBlank()) {
                                                    ingredient_list.add("$measures15 $ingredients15")
                                                    if (measures16!!.isNotBlank()) {
                                                        ingredient_list.add("$measures16 $ingredients16")
                                                        if (measures17!!.isNotBlank()) {
                                                            ingredient_list.add("$measures17 $ingredients17")
                                                            if (measures18!!.isNotBlank()) {
                                                                ingredient_list.add("$measures18 $ingredients18")
                                                                if (measures19!!.isNotBlank()) {
                                                                    ingredient_list.add("$measures19 $ingredients19")
                                                                    if (measures20!!.isNotBlank()) {
                                                                        ingredient_list.add("$measures20 $ingredients20")
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        val ingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ingredient_list)
        ingredientList.adapter = ingAdapter
    }
}