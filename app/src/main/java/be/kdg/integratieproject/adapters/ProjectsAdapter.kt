package be.kdg.integratieproject.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.R
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.model.project.ProjectBasic
import be.kdg.integratieproject.rest.getRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.project_list_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProjectsAdapter(
    private val listener: Listener,
    private val context: Context
) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>(){
    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvProjectName = view.tvProjectName
        val ivProjectImage = view.ivProjectImage
        val tvProjectLikes = view.tvLikes
        val tvProjectIdeations = view.tvIdeations
        val ivIdeationIcon = view.ivIdeations
        val tvIdeations = view.tvIdeations
    }

    var projectList: ArrayList<ProjectBasic> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
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
        //val url = "https://localhost:5001/api/ProjectImage/${currentProject.projectId}"
        //Picasso.get().load(url).into(projectViewHolder.ivProjectImage) //doesn't work but can't find why, the api works fine
                                                                        //maybe problem with build gradle, had some problems there with mismatching versions
        //projectViewHolder.ivProjectImage.setImageResource(R.drawable.project1) // hardcoded image, should be deleted once picasso works

        val id = context.resources.getIdentifier("project"+currentProject.projectId, "drawable", context.packageName)
        projectViewHolder.ivProjectImage.setImageResource(id)

        /*val call = getRetrofit().getProjectImage(currentProject.projectId)
        call.enqueue(object: Callback<File>{
            override fun onResponse(
                call: Call<File>,
                response: Response<File>
            ) {
                if (response.isSuccessful){
                    projectViewHolder.ivProjectImage.setImageURI(Uri.fromFile(response.body()))
                }
            }

            override fun onFailure(call: Call<File>, t: Throwable) {
                //projectViewHolder.ivProjectImage.setImageResource(R.drawable.nature)
            }
        })*/
        // doesn't work with retrofit either

        projectViewHolder.ivProjectImage.setOnClickListener {
            listener.onProjectSelected(currentProject.projectId)
        }
        projectViewHolder.tvProjectName.setOnClickListener {
            listener.onProjectSelected(currentProject.projectId)
        }
        projectViewHolder.ivIdeationIcon.setOnClickListener {
            listener.onIdeationsSelected(currentProject.projectId)
        }
        projectViewHolder.tvIdeations.setOnClickListener {
            listener.onIdeationsSelected(currentProject.projectId)
        }
    }

    interface Listener {
        fun onProjectSelected(projectId: Int)
        fun onIdeationsSelected(projectId: Int)
    }
}