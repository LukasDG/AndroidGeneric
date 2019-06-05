package be.kdg.integratieproject.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import be.kdg.integratieproject.R
import be.kdg.integratieproject.adapters.IdeationsAdapter
import be.kdg.integratieproject.adapters.QuestionnairesAdapter
import be.kdg.integratieproject.rest.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

private const val PROJECT_ID = "projectId"

class IdeationFragment : Fragment(), IdeationsAdapter.Listener {
    private lateinit var rvIdeations: RecyclerView
    private lateinit var llIdeations: LinearLayout
    private var projectId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            projectId = it.getInt(PROJECT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_ideation, container, false)
        initViews(view)
        loadData()

        return view
    }

    companion object {
        fun newInstance(projectId: Int) =
            IdeationFragment().apply {
                arguments = Bundle().apply {
                    putInt(PROJECT_ID, projectId)
                }
            }
    }

    private fun initViews(view: View){
        rvIdeations = view.findViewById(R.id.rvIdeations)
        rvIdeations.adapter = IdeationsAdapter(this)
        rvIdeations.layoutManager = LinearLayoutManager(this.context)
        llIdeations = view.findViewById(R.id.llIdeations)
    }

    private fun loadData(){
        getRetrofit().getIdeations(projectId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                (rvIdeations.adapter as IdeationsAdapter).ideationList = ArrayList(it)
                if (it.isEmpty()){
                    llIdeations.removeView(rvIdeations)
                    val message = TextView(this.context)
                    message.text = "This project doesn't have any ideations yet"
                    llIdeations.addView(message)
                }
            })
    }

    override fun onShowIdeasSelected(ideationId: Int) {

    }
}
