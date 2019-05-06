package be.kdg.integratieproject.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Project
import kotlinx.android.synthetic.main.project_list_item.view.*

/*class ProjectsAdapter(
    private val projects: Array<Project>,
    private val projectSelectionListener: ProjectSelectionListener,
    private val context: Context
) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>(){
    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ivProjectImage: ImageView = view.ivProjectImage
        val tvProjectName: TextView = view.tvProjectName
        val tvLikes: TextView = view.tvLikes
        val tvComments: TextView = view.tvComments
        val tvShares: TextView = view.tvShares
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val projectView = LayoutInflater.from(context).inflate(viewType, parent, false)
        return ProjectViewHolder(projectView)
    }

    override fun onBindViewHolder(projectViewHolder: ProjectViewHolder, position: Int) {
        projectViewHolder.tvProjectName.text = projects[position].name


        projectViewHolder.itemView.setOnClickListener {
            projectSelectionListener.onProjectSelected(position)
        }
    }

    override fun getItemCount() = projects.size

    interface ProjectSelectionListener{
        fun onProjectSelected(position: Int)
    }
}*/

class ProjectsAdapter(val context: Context) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>(){
    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvProjectName = view.tvProjectName
        val ivProjectImage = view.ivProjectImage
    }

    var projects: ArrayList<Project> = arrayListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewId: Int): ProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount() = projects.size

    override fun onBindViewHolder(projectViewHolder: ProjectViewHolder, position: Int) {
        val currentProject = projects[position]
        projectViewHolder.tvProjectName.text = currentProject.name

    }
}