package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R
import be.kdg.integratieproject.adapters.QuestionnairesAdapter
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.model.project.Questionnaire
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

private const val PROJECT_ID = "projectId"

class QuestionnairesFragment : Fragment(), QuestionnairesAdapter.Listener {

    private lateinit var rvQuestionnaires: RecyclerView
    private var projectId: Int = 0

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
        val view = inflater.inflate(R.layout.fragment_questionnaires, container, false)
        initViews(view)
        loadData()

        return view
    }

    companion object {
        fun newInstance(projectId: Int) =
            QuestionnairesFragment().apply {
                arguments = Bundle().apply {
                    putInt(PROJECT_ID, projectId)
                }
            }
    }

    private fun initViews(view: View){
        rvQuestionnaires = view.findViewById(R.id.rvQuestionaires)
        rvQuestionnaires.layoutManager = LinearLayoutManager(this.context)
    }

    private fun loadData(){
        getRetrofit().getQuestionnaires(projectId+1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse)
    }

    private fun handleResponse(questionnaireList: List<Questionnaire>){
        val questionnaires = ArrayList(questionnaireList)
        rvQuestionnaires.adapter = QuestionnairesAdapter(questionnaires, this)
    }

    override fun onQuestionnaireSelected(questionnaireId: Int) {
        val questionnaireFragment = QuestionnaireFragment.newInstance(questionnaireId)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container, questionnaireFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
