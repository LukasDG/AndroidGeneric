package be.kdg.integratieproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ProjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        initialiseViews()
    }

    private fun initialiseViews() {

    }
}
