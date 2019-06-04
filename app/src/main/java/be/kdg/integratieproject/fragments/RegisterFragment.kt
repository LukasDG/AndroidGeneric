package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        initViews(/*view*/)
        activity?.title = "Register"

        return view
    }

    companion object {
        fun newInstance(): RegisterFragment =
            RegisterFragment()
    }

    private fun initViews(/*view: View*/){

    }

}
