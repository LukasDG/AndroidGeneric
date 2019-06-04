package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R

private const val PROJECT_ID = "projectId"

class IdeationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ideation, container, false)
    }

    companion object {
        fun newInstance(projectId: Int) =
            IdeationFragment().apply {
                arguments = Bundle().apply {
                    putInt(PROJECT_ID, projectId)
                }
            }
    }

}
