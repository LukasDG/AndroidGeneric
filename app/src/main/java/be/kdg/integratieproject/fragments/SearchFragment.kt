package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Search"
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        fun newInstance(): SearchFragment =
            SearchFragment()
    }


}
