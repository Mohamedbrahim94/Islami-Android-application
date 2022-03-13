package android.example.islamiproject.ui

import android.example.islamiproject.Constants
import android.example.islamiproject.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sura.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class SuraDetailsActivity : AppCompatActivity() {

    var verses : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura)
        initView()
    }

    fun initView() {
        val suraName = intent.getStringExtra(Constants.SURA_NAME_EXTRA)
        val suraPosition = intent.getIntExtra(Constants.POSITION_EXTRA,0)
        item_sura_name.text = suraName
        readFile(suraPosition)
        val adapter = VersesAdapter(verses)
        recycler.adapter = adapter


    }

    fun readFile(position: Int ) {
       val reader: BufferedReader?
        Log.d("StackOverflow", position.toString())


        try {
            val file: InputStream = assets.open((position+1).toString() + ".txt")
             reader = BufferedReader(InputStreamReader(file))
            var line: String ? = reader.readLine()
            while (line != null) {
                verses.add(line)
                line = reader.readLine()

            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }
}