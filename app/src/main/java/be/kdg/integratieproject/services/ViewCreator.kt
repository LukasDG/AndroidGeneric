package be.kdg.integratieproject.services

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import android.widget.*
import be.kdg.integratieproject.R
import be.kdg.integratieproject.fragments.QuestionnaireFragment


fun getEditText(context: Context): View {
    val editText = EditText(context)
    editText.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
    return editText
}

fun getRadioGroup(context: Context, options: ArrayList<String>): View {
    val radioGroup = RadioGroup(context)
    for (option in options){
        val radioButton = RadioButton(context)
        radioButton.text = option
        radioButton.id = QuestionnaireFragment.id
        QuestionnaireFragment.id++
        radioGroup.addView(radioButton)
    }
    return radioGroup
}

fun getCheckBoxes(context: Context, options: ArrayList<String>): View {
    val layout = LinearLayout(context)
    layout.orientation = LinearLayout.VERTICAL
    for (option in options){
        val cb = CheckBox(context)
        cb.text = option
        layout.addView(cb)
    }
    return layout
}

fun getDropDownList(context: Context, options: ArrayList<String>): View {
    val spinner = Spinner(context)
    val spinnerArrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, options)
    spinner.adapter = spinnerArrayAdapter

    return spinner
}

fun getCheckBoxAnswers(view: ViewGroup): String {
    val checkboxes = ArrayList<CheckBox>()
    val count = view.childCount
    for (x in 0 until count){
        val child = view.getChildAt(x)
        if (child is CheckBox){
            checkboxes.add(child)
        }
    }
    var answers = ""
    for (item in checkboxes){
        if(item.isChecked){
            if (answers != ""){
                answers += ","
            }
            answers += item.text
        }
    }

    return answers
}