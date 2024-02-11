package com.almadevs.androidcurso.asistenciaapp
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.almadevs.androidcurso.R
import java.util.Locale


class HomeActivity : AppCompatActivity() {

    private var isStartSelected:Boolean = true
    private var isPausaSelected:Boolean = false

    private lateinit var viewStart:CardView
    private lateinit var viewPausa:CardView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Obtener la referencia al TextView
        val textDate = findViewById<TextView>(R.id.textDate)

        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale.getDefault())
        val fechaActual = dateFormat.format(calendar.time)

        // Establecer la fecha actual en el TextView
        textDate.text = fechaActual
        initComponents()
        initListeners()
        initUI()
    }


    private fun initComponents(){
        viewStart = findViewById(R.id.start)
        viewPausa = findViewById(R.id.pausa)
    }

    private fun initListeners(){
        viewStart.setOnClickListener {
            changeGender()
            setGenderColor() }
        viewPausa.setOnClickListener {
            changeGender()
            setGenderColor()}
    }

    private fun changeGender(){
        isStartSelected = !isStartSelected
        isPausaSelected = !isPausaSelected
    }

    private fun setGenderColor(){
        viewStart.setCardBackgroundColor(getBackgroundColor(isStartSelected))
        viewPausa.setCardBackgroundColor(getBackgroundColor(isPausaSelected))
    }

    private fun getBackgroundColor(isSelectedComponet:Boolean):Int {
        val colorReference = if(isSelectedComponet){
            R.color.title_text
        }else{
            R.color.verde_btn_lgn
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
    }

}
