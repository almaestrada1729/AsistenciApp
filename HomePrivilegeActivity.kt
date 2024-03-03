package com.almadevs.androidcurso.asistenciaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.almadevs.androidcurso.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class HomePrivilegeActivity : AppCompatActivity() {
    private var isStartSelected:Boolean = true
    private var isPausaSelected:Boolean = false
    private lateinit var viewStart: CardView
    private lateinit var viewPausa: CardView
    private lateinit var btnReport: MenuItem
    private lateinit var btnListEmp: MenuItem

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_privilege)
        // Obtener la referencia al TextView
        val textDate = findViewById<TextView>(R.id.textDatePrivilege)
        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale.getDefault())
        val fechaActual = dateFormat.format(calendar.time)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.collectionMenuBottomNavigation)

        // Obtener una referencia al menú inflado
        val menuInflater = menuInflater
        val menu = bottomNavigationView.menu

        // Obtener las referencias a los elementos del menú
        btnReport = menu.findItem(R.id.btnReport)
        btnListEmp = menu.findItem(R.id.btnListEmp)

        // Establecer la fecha actual en el TextView
        textDate.text = fechaActual
        initComponents()
        initListeners()
        initUI()

        btnReport.setOnMenuItemClickListener {
            navigateToReport()
            true
        }

        btnListEmp.setOnMenuItemClickListener {
            navigateToListEmpl()
            true
        }
    }

    private fun navigateToReport() {
        val intent = Intent(this, ReportActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToListEmpl(){
        val intent = Intent( this, ListEmpActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents(){
        viewStart = findViewById(R.id.startPtivilege)
        viewPausa = findViewById(R.id.pausaPrivilege)
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
            R.color.colorPrimaryCoppelAzul2
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
    }

}
