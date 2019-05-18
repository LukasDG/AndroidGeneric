package be.kdg.integratieproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.kdg.integratieproject.adapters.ProjectsAdapter
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.ArrayList

class HomeFragment : Fragment(), ProjectsAdapter.Listener{
    private lateinit var rvProjects: RecyclerView
    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        loadData()
        myCompositeDisposable = CompositeDisposable()

        return view
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private fun initViews(view: View){
        rvProjects = view.rvProjects
        rvProjects.layoutManager = LinearLayoutManager(this.context)
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
        println("test")
        println("test")
        println("test")
        rvProjects.adapter = ProjectsAdapter(myDataArrayList, this)
    }

    override fun onProjectSelected(projectId: Int) {

    }
}
