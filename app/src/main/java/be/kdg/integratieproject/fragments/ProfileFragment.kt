package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)

        return view
    }

    companion object {
        fun newInstance(): ProfileFragment =
            ProfileFragment()
    }

    private fun initViews(view: View){

    }
}
