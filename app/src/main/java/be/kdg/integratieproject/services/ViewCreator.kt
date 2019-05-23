package be.kdg.integratieproject.services

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.*


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