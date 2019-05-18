package be.kdg.integratieproject.rest

import be.kdg.integratieproject.model.project.Project
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("api/Projects")
    fun getProjects():Observable<List<Project>>

    @GET("api/Projects/{id}")
    fun getProjectById(@Path("id") id:Int):Observable<Project>
}



