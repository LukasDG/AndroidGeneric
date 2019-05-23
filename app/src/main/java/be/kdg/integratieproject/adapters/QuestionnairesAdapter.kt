package be.kdg.integratieproject.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Questionnaire
import kotlinx.android.synthetic.main.questionnaire_list_item.view.*

class QuestionnairesAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<QuestionnairesAdapter.QuestionnaireViewHolder>(){

    class QuestionnaireViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvQuestionnaireName = view.tvQuestionnaireName
        val tvFillIn = view.tvFillIn
        val ivFillInImage = view.ivFillInImage
    }

    var questionnairesList: ArrayList<Questionnaire> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewId: Int): QuestionnaireViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.questionnaire_list_item, parent, false)
        return QuestionnaireViewHolder(view)
    }

    override fun getItemCount() = questionnairesList.size

    override fun onBindViewHolder(questionnaireViewHolder: QuestionnaireViewHolder, position: Int) {
        val currentProject = questionnairesList[position]

        questionnaireViewHolder.tvQuestionnaireName.text = currentProject.name

        questionnaireViewHolder.ivFillInImage.setOnClickListener {
            listener.onQuestionnaireSelected(position)
        }
        questionnaireViewHolder.tvFillIn.setOnClickListener {
            listener.onQuestionnaireSelected(position)
        }
        questionnaireViewHolder.tvQuestionnaireName.setOnClickListener {
            listener.onQuestionnaireSelected(position)
        }
    }

    interface Listener{
        fun onQuestionnaireSelected(questionnaireId: Int)
    }
}
