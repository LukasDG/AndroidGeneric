package be.kdg.integratieproject.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.ideation.Ideation
import kotlinx.android.synthetic.main.ideation_list_item.view.*

class IdeationsAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<IdeationsAdapter.IdeationViewHolder>(){

    class IdeationViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvIdeationTitle = view.tvIdeationTitle
        val tvShowIdeas = view.tvShowIdeas
        val tvIdeas = view.tvIdeationComments
        val tvLikes = view.tvIdeationLikes
        val ivIdeas = view.ivIdeationComments
    }

    var ideationList: ArrayList<Ideation> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewId: Int): IdeationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ideation_list_item, parent, false)
        return IdeationViewHolder(view)
    }

    override fun getItemCount() = ideationList.size

    override fun onBindViewHolder(ideationViewHolder: IdeationViewHolder, position: Int) {
        val currentIdeation = ideationList[position]

        ideationViewHolder.tvIdeationTitle.text = currentIdeation.question
        ideationViewHolder.tvIdeas.text = currentIdeation.numberOfIdeas.toString()
        ideationViewHolder.tvLikes.text = currentIdeation.numberOfLikes.toString()

        ideationViewHolder.tvShowIdeas.setOnClickListener {
            listener.onShowIdeasSelected(currentIdeation.ideationId)
        }
        ideationViewHolder.tvIdeationTitle.setOnClickListener {
            listener.onShowIdeasSelected(currentIdeation.ideationId)
        }
        ideationViewHolder.tvIdeas.setOnClickListener {
            listener.onShowIdeasSelected(currentIdeation.ideationId)
        }
        ideationViewHolder.ivIdeas.setOnClickListener {
            listener.onShowIdeasSelected(currentIdeation.ideationId)
        }
    }

    interface Listener{
        fun onShowIdeasSelected(ideationId: Int)
    }
}