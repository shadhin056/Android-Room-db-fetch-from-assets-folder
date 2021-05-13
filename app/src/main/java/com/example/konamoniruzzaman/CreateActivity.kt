package com.example.konamoniruzzaman

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import cn.pedant.SweetAlert.SweetAlertDialog
import com.shadhin.android_jetpack.view.model.WordModel
import com.shadhin.android_jetpack.view.view_model.WordViewModel
import kotlinx.android.synthetic.main.activity_create.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class CreateActivity : AppCompatActivity() {
    private lateinit var customer: WordModel
    private lateinit var pDialog: SweetAlertDialog
    private lateinit var viewModel: WordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)

        buttonAction()

        //response when data inserted successfully
        observeViewModel()
    }

    private fun buttonAction() {

        btnSubmit.setOnClickListener {
                //call for data insert for customer
            wordInfoAdd()

        }
        btnDelete.setOnClickListener {
            //call for data insert for customer
            deleteInfoA()

        }
    }

    private fun deleteInfoA() {
        viewModel.deleteLocally()
    }

    private fun wordInfoAdd() {

        var br: BufferedReader? = null
        try {
            br = BufferedReader(InputStreamReader(this.getAssets().open("words.txt")), 1024 * 34)
            var line: String? = null

            while (br.readLine().also { line = it } != null) {
                /*if(br.readLine().also { line = it } == null){
                    Toast.makeText(getApplication(), "Added Successfully", Toast.LENGTH_LONG).show()
                }*/
                customer = WordModel(line)
                viewModel.storeWordLocally(customer)

            }

        } catch (e: IOException) {

        } finally {

            if (br != null) {
                try {
                    br.close()
                } catch (e: IOException) {
                    Log.e("xxx", "buffer reader close error")
                }
            }
        }







    }
    private fun observeViewModel() {

        viewModel.word.observe(this, androidx.lifecycle.Observer {
            it?.let {
                /*SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success!")
                    .setContentText(it)
                    .setConfirmText("Check Word List")
                    .setConfirmClickListener { sDialog ->
                        sDialog.dismissWithAnimation()
                        var intent: Intent? = Intent(this, ViewWord::class.java)
                        startActivity(intent)
                    }
                    .show()*/

            }
        })



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

