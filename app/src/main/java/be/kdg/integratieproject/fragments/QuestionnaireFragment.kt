package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import be.kdg.integratieproject.R
import be.kdg.integratieproject.adapters.ProjectsAdapter
import be.kdg.integratieproject.model.project.ProjectBasic
import be.kdg.integratieproject.model.project.Question
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

private const val QUESTIONNAIRE_ID = "questionnaire_id"

class QuestionnaireFragment : Fragment() {

    private var questionnaireId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            questionnaireId = it.getInt(QUESTIONNAIRE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire, container, false)
        initViews(view)
        loadData()

        return view
    }

    companion object {
        fun newInstance(questionnaireId: Int) =
            QuestionnaireFragment().apply {
                arguments = Bundle().apply {
                    putInt(QUESTIONNAIRE_ID, questionnaireId)
                }
            }
    }

    fun initViews(view: View){

    }

    private fun loadData(){
        getRetrofit().getQuestions(questionnaireId+1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse)
    }

    private fun handleResponse(questionsList: List<Question>){
        val questions = ArrayList(questionsList)

    }

}
