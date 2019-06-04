package be.kdg.integratieproject.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.model.project.Questionnaire
import be.kdg.integratieproject.rest.getRetrofit
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val PROJECT_ID = "projectId"

class ProjectFragment : Fragment(){
    private var projectId: Int = 0
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private lateinit var projectTitle: TextView
    private lateinit var projectDescription: TextView
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var btnQuestionnaires: Button
    private lateinit var btnIdeations: Button
    private lateinit var ivProjectImage: ImageView
    private lateinit var pbProjectProgress: ProgressBar

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
        loadData()
        initViews(view)
        activity?.title = "Project details"

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
        startDate = view.findViewById(R.id.tvStartdate)
        endDate = view.findViewById(R.id.tvEndDate)
        btnIdeations = view.findViewById(R.id.btnIdeations)
        btnQuestionnaires = view.findViewById(R.id.btnQuestionnaires)
        ivProjectImage = view.findViewById(R.id.ivProjectImage)
        pbProjectProgress = view.findViewById(R.id.projectProgressBar)

        val url = "https://localhost:5001/api/ProjectImage/$projectId"
        Picasso.get().load(url).into(ivProjectImage) //doesn't work either

        ivProjectImage.setImageResource(R.drawable.nature) // should be deleted once picasso works

        btnQuestionnaires.setOnClickListener {
            val questionnaireFragment = QuestionnairesFragment.newInstance(projectId)
            switchFragment(questionnaireFragment)
        }

        btnIdeations.setOnClickListener {

        }
    }



    private fun loadData(){
        getRetrofit().getProjectById(projectId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse)
    }

    private fun handleResponse(project: Project){
        projectTitle.text = project.name
        projectDescription.text = project.description
        startDate.text = dateFormatter.format(project.startDate)
        endDate.text = dateFormatter.format(project.endDate)
        pbProjectProgress.progress = project.percentageCompleted
    }

    private fun switchFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
