package com.mycompany.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.video_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeActivity : AppCompatActivity() {

    private val TAG = "RecipeActivity"
    private val ingredient_list = ArrayList<String>()
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipeFragment = RecipeFragment()
        val instructionsFragment = InstructionsFragment()
        val videoFragment = VideoFragment()
        val bundle = Bundle()
        val bundleInstructions = Bundle()
        val bundleVideo = Bundle()

        val mealName = intent.getStringExtra("recipeName")
        bundle.putString("recipeName", mealName)
        bundleVideo.putString("recipeName", mealName)

        val recId = intent.getIntExtra("recipeId", 0)
        bundle.putInt("recId", recId)
        val mealImage = intent.getStringExtra("image")
        bundle.putString("image", mealImage)
        val instrucs = intent.getStringExtra("instructions")
        bundle.putString("instrucs", instrucs)
        bundleInstructions.putString("instrucs", instrucs)
        if (instrucs == null) {
            infoFromCat(recId)
        } else {
            val measures1 = intent.getStringExtra("measure1")
            bundle.putString("measure1", measures1)
            val measures2 = intent.getStringExtra("measure2")
            bundle.putString("measure2", measures2)
            val measures3 = intent.getStringExtra("measure3")
            bundle.putString("measure3", measures3)
            val measures4 = intent.getStringExtra("measure4")
            bundle.putString("measure4", measures4)
            val measures5 = intent.getStringExtra("measure5")
            bundle.putString("measure5", measures5)
            val measures6 = intent.getStringExtra("measure6")
            bundle.putString("measure6", measures6)
            val measures7 = intent.getStringExtra("measure7")
            bundle.putString("measure7", measures7)
            val measures8 = intent.getStringExtra("measure8")
            bundle.putString("measure8", measures8)
            val measures9 = intent.getStringExtra("measure9")
            bundle.putString("measure9", measures9)
            val measures10 = intent.getStringExtra("measure10")
            bundle.putString("measure10", measures10)
            val measures11 = intent.getStringExtra("measure11")
            bundle.putString("measure11", measures11)
            val measures12 = intent.getStringExtra("measure12")
            bundle.putString("measure12", measures12)
            val measures13 = intent.getStringExtra("measure13")
            bundle.putString("measure13", measures13)
            val measures14 = intent.getStringExtra("measure14")
            bundle.putString("measure14", measures14)
            val measures15 = intent.getStringExtra("measure15")
            bundle.putString("measure15", measures15)
            val measures16 = intent.getStringExtra("measure16")
            bundle.putString("measure16", measures16)
            val measures17 = intent.getStringExtra("measure17")
            bundle.putString("measure17", measures17)
            val measures18 = intent.getStringExtra("measure18")
            bundle.putString("measure18", measures18)
            val measures19 = intent.getStringExtra("measure19")
            bundle.putString("measure19", measures19)
            val measures20 = intent.getStringExtra("measure20")
            bundle.putString("measure20", measures20)

            val ingredients1 = intent.getStringExtra("ingredient1")
            bundle.putString("ingredient1", ingredients1)
            val ingredients2 = intent.getStringExtra("ingredient2")
            bundle.putString("ingredient2", ingredients2)
            val ingredients3 = intent.getStringExtra("ingredient3")
            bundle.putString("ingredient3", ingredients3)
            val ingredients4 = intent.getStringExtra("ingredient4")
            bundle.putString("ingredient4", ingredients4)
            val ingredients5 = intent.getStringExtra("ingredient5")
            bundle.putString("ingredient5", ingredients5)
            val ingredients6 = intent.getStringExtra("ingredient6")
            bundle.putString("ingredient6", ingredients6)
            val ingredients7 = intent.getStringExtra("ingredient7")
            bundle.putString("ingredient7", ingredients7)
            val ingredients8 = intent.getStringExtra("ingredient8")
            bundle.putString("ingredient8", ingredients8)
            val ingredients9 = intent.getStringExtra("ingredient9")
            bundle.putString("ingredient9", ingredients9)
            val ingredients10 = intent.getStringExtra("ingredient10")
            bundle.putString("ingredient10", ingredients10)
            val ingredients11 = intent.getStringExtra("ingredient11")
            bundle.putString("ingredient11", ingredients11)
            val ingredients12 = intent.getStringExtra("ingredient12")
            bundle.putString("ingredient12", ingredients12)
            val ingredients13 = intent.getStringExtra("ingredient13")
            bundle.putString("ingredient13", ingredients13)
            val ingredients14 = intent.getStringExtra("ingredient14")
            bundle.putString("ingredient14", ingredients14)
            val ingredients15 = intent.getStringExtra("ingredient15")
            bundle.putString("ingredient15", ingredients15)
            val ingredients16 = intent.getStringExtra("ingredient16")
            bundle.putString("ingredient16", ingredients16)
            val ingredients17 = intent.getStringExtra("ingredient17")
            bundle.putString("ingredient17", ingredients17)
            val ingredients18 = intent.getStringExtra("ingredient18")
            bundle.putString("ingredient18", ingredients18)
            val ingredients19 = intent.getStringExtra("ingredient19")
            bundle.putString("ingredient19", ingredients19)
            val ingredients20 = intent.getStringExtra("ingredient20")
            bundle.putString("ingredient20", ingredients20)
            recipeFragment.arguments = bundle
            instructionsFragment.arguments = bundleInstructions
            videoFragment.arguments = bundleVideo

            val tab_layout: TabLayout = findViewById(R.id.tabLayout)
            val viewPager: ViewPager = findViewById(R.id.viewpager_main)
            val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
            viewPagerAdapter.addFragment(recipeFragment, "Recipes")
            viewPagerAdapter.addFragment(instructionsFragment, "Instructions")
            viewPagerAdapter.addFragment(videoFragment, "Videos")
            viewPager.adapter = viewPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    internal class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList()
            titles = ArrayList()
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String)
        {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

    fun infoFromCat(recid: Int) {
        val recipeFrag = RecipeFragment()
        val instructionsFrag = InstructionsFragment()
        val videoFrag = VideoFragment()
        val bundle = Bundle()
        val bundleInstrucs = Bundle()
        val bundleVids = Bundle()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mealdbAPI = retrofit.create(MealDBService::class.java)
        mealdbAPI.idLookup(recid).enqueue(object : Callback<MealDBData> {
            override fun onResponse(call: Call<MealDBData>, response: Response<MealDBData>) {
                Log.d(TAG, "onResponse: $response")
                val body = response.body()

                if (body == null) {
                    Log.w(TAG, "Valid response was not received")
                    return
                }
                val mealName = intent.getStringExtra("recipeName")
                bundle.putString("recipeName", mealName)
                bundleVids.putString("recipeName", mealName)

                val recId = intent.getIntExtra("recipeId", 0)
                bundle.putInt("recId", recId)
                val mealImage = intent.getStringExtra("image")
                bundle.putString("image", mealImage)
                val instrucs = body.meals[0].strInstructions
                bundle.putString("instrucs", instrucs)
                bundleInstrucs.putString("instrucs", instrucs)
                val measures1 = body.meals[0].strMeasure1
                bundle.putString("measure1", measures1)
                val measures2 = body.meals[0].strMeasure2
                bundle.putString("measure2", measures2)
                val measures3 = body.meals[0].strMeasure3
                bundle.putString("measure3", measures3)
                val measures4 = body.meals[0].strMeasure4
                bundle.putString("measure4", measures4)
                val measures5 = body.meals[0].strMeasure5
                bundle.putString("measure5", measures5)
                val measures6 = body.meals[0].strMeasure6
                bundle.putString("measure6", measures6)
                val measures7 = body.meals[0].strMeasure7
                bundle.putString("measure7", measures7)
                val measures8 = body.meals[0].strMeasure8
                bundle.putString("measure8", measures8)
                val measures9 = body.meals[0].strMeasure9
                bundle.putString("measure9", measures9)
                val measures10 = body.meals[0].strMeasure10
                bundle.putString("measure10", measures10)
                val measures11 = body.meals[0].strMeasure11
                bundle.putString("measure11", measures11)
                val measures12 = body.meals[0].strMeasure12
                bundle.putString("measure12", measures12)
                val measures13 = body.meals[0].strMeasure13
                bundle.putString("measure13", measures13)
                val measures14 = body.meals[0].strMeasure14
                bundle.putString("measure14", measures14)
                val measures15 = body.meals[0].strMeasure15
                bundle.putString("measure15", measures15)
                val measures16 = body.meals[0].strMeasure16
                bundle.putString("measure16", measures16)
                val measures17 = body.meals[0].strMeasure17
                bundle.putString("measure17", measures17)
                val measures18 = body.meals[0].strMeasure18
                bundle.putString("measure18", measures18)
                val measures19 = body.meals[0].strMeasure19
                bundle.putString("measure19", measures19)
                val measures20 = body.meals[0].strMeasure20
                bundle.putString("measure20", measures20)

                val ingredients1 = body.meals[0].strIngredient1
                bundle.putString("ingredient1", ingredients1)
                val ingredients2 = body.meals[0].strIngredient2
                bundle.putString("ingredient2", ingredients2)
                val ingredients3 = body.meals[0].strIngredient3
                bundle.putString("ingredient3", ingredients3)
                val ingredients4 = body.meals[0].strIngredient4
                bundle.putString("ingredient4", ingredients4)
                val ingredients5 = body.meals[0].strIngredient5
                bundle.putString("ingredient5", ingredients5)
                val ingredients6 = body.meals[0].strIngredient6
                bundle.putString("ingredient6", ingredients6)
                val ingredients7 = body.meals[0].strIngredient7
                bundle.putString("ingredient7", ingredients7)
                val ingredients8 = body.meals[0].strIngredient8
                bundle.putString("ingredient8", ingredients8)
                val ingredients9 = body.meals[0].strIngredient9
                bundle.putString("ingredient9", ingredients9)
                val ingredients10 = body.meals[0].strIngredient10
                bundle.putString("ingredient10", ingredients10)
                val ingredients11 = body.meals[0].strIngredient11
                bundle.putString("ingredient11", ingredients11)
                val ingredients12 = body.meals[0].strIngredient12
                bundle.putString("ingredient12", ingredients12)
                val ingredients13 = body.meals[0].strIngredient13
                bundle.putString("ingredient13", ingredients13)
                val ingredients14 = body.meals[0].strIngredient14
                bundle.putString("ingredient14", ingredients14)
                val ingredients15 = body.meals[0].strIngredient15
                bundle.putString("ingredient15", ingredients15)
                val ingredients16 = body.meals[0].strIngredient16
                bundle.putString("ingredient16", ingredients16)
                val ingredients17 = body.meals[0].strIngredient17
                bundle.putString("ingredient17", ingredients17)
                val ingredients18 = body.meals[0].strIngredient18
                bundle.putString("ingredient18", ingredients18)
                val ingredients19 = body.meals[0].strIngredient19
                bundle.putString("ingredient19", ingredients19)
                val ingredients20 = body.meals[0].strIngredient20
                bundle.putString("ingredient20", ingredients20)

                recipeFrag.arguments = bundle
                instructionsFrag.arguments = bundle
                videoFrag.arguments = bundle
                val tab_layout: TabLayout = findViewById(R.id.tabLayout)
                val viewPager: ViewPager = findViewById(R.id.viewpager_main)
                val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
                viewPagerAdapter.addFragment(recipeFrag, "Recipes")
                viewPagerAdapter.addFragment(instructionsFrag, "Instructions")
                viewPagerAdapter.addFragment(videoFrag, "Videos")
                viewPager.adapter = viewPagerAdapter
                tabLayout.setupWithViewPager(viewPager)
            }

            override fun onFailure(call: Call<MealDBData>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

    fun openVideoActivity(view: View)
    {
        val newIntent = Intent(this, PlayVideoActivity::class.java)
        val videoid = vid_id.text.toString()
        newIntent.putExtra("vidid", videoid)
        startActivity(newIntent)
    }
}