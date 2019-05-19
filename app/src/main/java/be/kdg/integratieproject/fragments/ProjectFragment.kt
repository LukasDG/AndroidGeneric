package be.kdg.integratieproject.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val PROJECT_ID = "projectId"

class ProjectFragment : Fragment() {
    private var projectId: Int = 0
    private lateinit var projectTitle: TextView
    private lateinit var projectDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            projectId = it.getInt(PROJECT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project, container, false)
        initViews(view)
        loadData()

        return view
    }

    companion object {
        fun newInstance(projectId: Int) =
            ProjectFragment().apply {
                arguments = Bundle().apply {
                    putInt(PROJECT_ID, projectId)
                }
            }
    }

    private fun initViews(view: View){
        projectTitle = view.findViewById(R.id.tvProjectTitle)
        projectDescription = view.findViewById(R.id.tvProjectDescription)
    }

    private fun loadData(){
        val retrofit = getRetrofit()

        retrofit.getProjectById(projectId+1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse)
    }

    private fun handleResponse(project: Project){
        projectTitle.text = project.name
        projectDescription.text = project.description
    }
}
