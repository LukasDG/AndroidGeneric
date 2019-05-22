package be.kdg.integratieproject.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.model.project.ProjectBasic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.project_list_item.view.*

class ProjectsAdapter(
    private val projectList: ArrayList<ProjectBasic>,
    private val listener: Listener
) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>(){
    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvProjectName = view.tvProjectName
        val ivProjectImage = view.ivProjectImage
        val tvProjectLikes = view.tvLikes
        val tvProjectIdeations = view.tvIdeations
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewId: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_list_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount() = projectList.size

    override fun onBindViewHolder(projectViewHolder: ProjectViewHolder, position: Int) {
        val currentProject = projectList[position]
        projectViewHolder.tvProjectName.text = currentProject.name
        projectViewHolder.tvProjectLikes.text = currentProject.numberOfLikes.toString()
        projectViewHolder.tvProjectIdeations.text = currentProject.numberOfIdeations.toString()
        Picasso.get().load("https://localhost:5001/api/ProjectImage/1").into(projectViewHolder.ivProjectImage)
        //projectViewHolder.ivProjectImage.setImageBitmap(currentProject.projectImage)
        //projectViewHolder.ivProjectImage.setImageResource(R.drawable.nature)
        projectViewHolder.ivProjectImage.setOnClickListener {
            listener.onProjectSelected(position)
        }
        projectViewHolder.tvProjectName.setOnClickListener {
            listener.onProjectSelected(position)
        }
    }

    interface Listener {
        fun onProjectSelected(projectId: Int)
    }
}