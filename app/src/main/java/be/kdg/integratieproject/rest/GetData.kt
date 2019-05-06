package be.kdg.integratieproject.rest

import be.kdg.integratieproject.model.project.Project
import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {
    @GET("api/Projects")
    fun getProjects(): Observable<List<Project>>
}