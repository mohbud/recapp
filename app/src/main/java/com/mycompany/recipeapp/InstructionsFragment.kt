package com.mycompany.recipeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_instructions.*

class InstructionsFragment : Fragment() {
    private val TAG = "InstructionsFragment"

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return (inflater.inflate(R.layout.fragment_instructions, container, false))
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG, "onStart")
        load()
    }

    fun load()
    {
        Log.d(TAG, "load")
        val instrucs = arguments?.getString("instrucs", "")
        instructions_text.text = instrucs
    }
}