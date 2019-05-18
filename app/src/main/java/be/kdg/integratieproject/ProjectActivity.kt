package be.kdg.integratieproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProjectActivity : AppCompatActivity() {
    private lateinit var projectTitle: TextView
    private lateinit var projectDescription: TextView
    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        myCompositeDisposable = CompositeDisposable()
        initialiseViews()
        loadData()
    }

    private fun initialiseViews() {
        projectTitle = findViewById(R.id.tvProjectTitle)
        projectDescription = findViewById(R.id.tvProjectDescription)
    }

    private fun loadData(){
        val retrofit = getRetrofit()

        myCompositeDisposable?.add(retrofit.getProjectById(intent.getIntExtra(PROJECT_ID, 0))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(project: Project){
        projectTitle.text = project.name
        projectDescription.text = project.description
    }
}
