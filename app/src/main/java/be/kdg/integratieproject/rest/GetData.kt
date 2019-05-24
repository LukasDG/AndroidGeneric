package be.kdg.integratieproject.rest

import be.kdg.integratieproject.model.project.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.io.File

interface DataService {
    @GET("api/Projects")
    fun getProjects():Observable<List<ProjectBasic>>

    @GET("api/Projects/{id}")
    fun getProjectById(@Path("id") id:Int):Observable<Project>

    @GET("api/ProjectImage/{id}")
    fun getProjectImage(@Path("id") id: Int): Call<File>

    @GET("api/Questionnaires/{id}")
    fun getQuestionnaires(@Path("id") id: Int): Observable<List<Questionnaire>>

    @GET("api/Questions/{id}")
    fun getQuestions(@Path("id") id: Int): Observable<List<Question>>

    @POST("api/SubmitQuestionnaire")
    fun submitQuestionnaire(@Body model: ArrayList<QuestionAnswer>): Call<ArrayList<QuestionAnswer>>
}



