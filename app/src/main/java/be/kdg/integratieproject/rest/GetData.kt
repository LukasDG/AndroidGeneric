package be.kdg.integratieproject.rest

import be.kdg.integratieproject.model.project.Project
import be.kdg.integratieproject.model.project.ProjectBasic
import be.kdg.integratieproject.model.project.Question
import be.kdg.integratieproject.model.project.Questionnaire
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("api/Projects")
    fun getProjects():Observable<List<ProjectBasic>>

    @GET("api/Projects/{id}")
    fun getProjectById(@Path("id") id:Int):Observable<Project>

    @GET("api/Questionnaires/{id}")
    fun getQuestionnaires(@Path("id") id: Int): Observable<List<Questionnaire>>

    @GET("api/Questions/{id}")
    fun getQuestions(@Path("id") id: Int): Observable<List<Question>>
}



