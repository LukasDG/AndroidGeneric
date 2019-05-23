package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import be.kdg.integratieproject.R
import be.kdg.integratieproject.adapters.ProjectsAdapter
import be.kdg.integratieproject.model.project.ProjectBasic
import be.kdg.integratieproject.model.project.Question
import be.kdg.integratieproject.rest.getRetrofit
import be.kdg.integratieproject.services.getCheckBoxes
import be.kdg.integratieproject.services.getDropDownList
import be.kdg.integratieproject.services.getEditText
import be.kdg.integratieproject.services.getRadioGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

private const val QUESTIONNAIRE_ID = "questionnaire_id"

class QuestionnaireFragment : Fragment() {

    private var questionnaireId: Int = 0
    private lateinit var llQuestionnaire: LinearLayout
    private lateinit var btnSubmit: Button

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
        loadData(view)

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
        llQuestionnaire = view.findViewById(R.id.llQuestionnaire)
        btnSubmit = view.findViewById(R.id.btnSubmitQuestionnaire)
    }

    private fun loadData(view: View){
        getRetrofit().getQuestions(questionnaireId+1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                initQuestionnaire(ArrayList(it), view)
            })
    }

    fun initQuestionnaire(questions: ArrayList<Question>, view: View){
        for (question in questions){
            val questionTitle = TextView(this.context)
            questionTitle.text = question.question
            llQuestionnaire.addView(questionTitle)

            when {
                question.questionType == 0 || question.questionType == 4 -> {
                    val editText = getEditText(this.requireContext())
                    editText.id = question.id
                    llQuestionnaire.addView(editText)
                }
                question.questionType == 1 -> {
                    val radioGroup = getRadioGroup(this.requireContext(), question.options)
                    radioGroup.id = question.id
                    llQuestionnaire.addView(radioGroup)
                }
                question.questionType == 2 -> {
                    val checkList = getCheckBoxes(this.requireContext(), question.options)
                    checkList.id = question.id
                    llQuestionnaire.addView(checkList)
                }
                question.questionType == 3 -> {
                    val dropDownList = getDropDownList(this.requireContext(), question.options)
                    dropDownList.id = question.id
                    llQuestionnaire.addView(dropDownList)
                }
            }
        }

        btnSubmit.setOnClickListener {
            for (x in 0 until questions.size){
                var answer = ""
                when {
                    questions[x].questionType == 0 || questions[x].questionType == 4 -> {
                        val editText = view.findViewById<EditText>(questions[x].id)
                        answer = editText.text.toString()
                    }
                    questions[x].questionType == 1 -> {
                        val radioGroup = view.findViewById<RadioGroup>(questions[x].id)
                        val selectedButtonId = radioGroup.checkedRadioButtonId
                        val radioButton = view.findViewById<RadioButton>(selectedButtonId)
                        answer = radioButton.text.toString()
                    }
                    questions[x].questionType == 2 -> {

                    }
                    questions[x].questionType == 3 -> {

                    }
                }
            }
        }
    }
}
