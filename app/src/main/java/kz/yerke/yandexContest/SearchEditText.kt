package kz.yerke.yandexContest

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout

class SearchEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    val uiEditText:EditText
    val uiCancle:ImageView
    val uiSearch:ImageView
    val uiArrow:ImageView
    init {
        LayoutInflater.from(context).inflate(R.layout.search_edittext_component, this, true)
        uiEditText = findViewById(R.id.uiEditText)
        uiCancle = findViewById(R.id.uiCancleIcon)
        uiArrow = findViewById(R.id.uiArrowIcon)
        uiSearch = findViewById(R.id.uiSearchIcon)
        uiEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.isNotEmpty() == true){
                    uiCancle.visibility = View.VISIBLE
                }
                else{
                    uiCancle.visibility = View.GONE
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        uiEditText.onFocusChangeListener = object : OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(hasFocus){
                    uiArrow.visibility = View.VISIBLE
                    uiSearch.visibility = View.GONE
                }
                else{
                    uiArrow.visibility = View.GONE
                    uiSearch.visibility = View.VISIBLE
                }
            }
        }

        uiCancle.setOnClickListener {
            uiEditText.text = null
        }
    }
}