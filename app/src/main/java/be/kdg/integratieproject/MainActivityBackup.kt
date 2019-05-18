package be.kdg.integratieproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import be.kdg.integratieproject.adapters.ProjectsAdapter
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

const val PROJECT_ID2: String = "PROJECT_ID"

class MainActivityBackup : AppCompatActivity(), ProjectsAdapter.Listener {
    private lateinit var rvProjects: RecyclerView
    private lateinit var navMenu: BottomNavigationView
    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initialiseViews()
        loadData()
    }

    private fun initialiseViews(){
        rvProjects = findViewById(R.id.rvProjects)
        navMenu = findViewById(R.id.bottom_navigation)
        //navMenu.setOnNavigationItemSelectedListener(initMenuListener(this))
        rvProjects.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        val retrofit = getRetrofit()
        myCompositeDisposable?.add(retrofit.getProjects()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(myData: List<Project>){
        val myDataArrayList = ArrayList(myData)
        rvProjects.adapter = ProjectsAdapter(myDataArrayList, this)
    }

    override fun onProjectSelected(projectId: Int){
        val intent = Intent(this, ProjectActivity::class.java)
        intent.putExtra(PROJECT_ID2, projectId+1)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}
