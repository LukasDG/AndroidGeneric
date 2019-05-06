package be.kdg.integratieproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import be.kdg.integratieproject.adapters.ProjectsAdapter
import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.rest.GetData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){
    private lateinit var rvProjects: RecyclerView
    private var myCompositeDisposable: CompositeDisposable? = null
    private val BASE_URL = "http://localhost:5001/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseViews()
        loadData()
    }

    private fun initialiseViews(){
        rvProjects = findViewById(R.id.rvProjects)
        rvProjects.layoutManager = LinearLayoutManager(this)
    }

    fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)

        myCompositeDisposable?.add(retrofit.getProjects()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(myData: List<Project>){
        val myDataArrayList = ArrayList(myData)
        rvProjects.adapter = ProjectsAdapter(this)
        (rvProjects.adapter as ProjectsAdapter).projects = myDataArrayList
    }
}
