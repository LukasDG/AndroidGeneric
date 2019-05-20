package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import be.kdg.integratieproject.R

class LoginFragment : Fragment() {
    private lateinit var inEmail: EditText
    private lateinit var inPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvNoAccount: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)

        return  view
    }

    companion object {
        fun newInstance(): LoginFragment =
            LoginFragment()
    }

    private fun initViews(view: View){
        inEmail = view.findViewById(R.id.inputEmailLogin)
        inPassword = view.findViewById(R.id.inputPasswordLogin)
        btnLogin = view.findViewById(R.id.btnLogin)
        tvNoAccount = view.findViewById(R.id.tvNoAccountYet)

        tvNoAccount.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, registerFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }
}
