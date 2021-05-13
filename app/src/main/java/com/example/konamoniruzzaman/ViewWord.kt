package com.example.konamoniruzzaman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.konamoniruzzaman.view_model.ListViewModel
import com.shadhin.android_jetpack.view.model.WordModel
import kotlinx.android.synthetic.main.activity_view_words.*

class ViewWord : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RequestAdapter? = null
    private lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_words)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)

        recyclerView = findViewById(R.id.rv_words) as RecyclerView
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        //response when data fetch successfully
        obserViewModel();
        btnSubmit.setOnClickListener {

            if(edtSearch.text.toString().length<3){
                Toast.makeText(getApplication(), "Minimum 3 Character", Toast.LENGTH_SHORT).show()
            }else{
                pDialog.show()
                viewModel.fetchFromDB(edtSearch.text.toString())
            }

        }
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.setLayoutManager(mLayoutManager)
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
    }

    private fun obserViewModel() {


        viewModel.words.observe(this, androidx.lifecycle.Observer { words ->
            words.let {
                pDialog.dismiss()
                Toast.makeText(getApplication(), "Fetch From Database ", Toast.LENGTH_SHORT).show()
                mAdapter = RequestAdapter(words)
                recyclerView!!.setAdapter(mAdapter)
                mAdapter?.notifyDataSetChanged();
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
    //display customers list
    inner class RequestAdapter(private val requestWordList: List<WordModel>) :
        RecyclerView.Adapter<RequestAdapter.MyViewHolder>(), View.OnClickListener {

        override fun onClick(v: View) {

        }

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
              var txtWord: TextView

            init {
                txtWord = view.findViewById(R.id.txtWord) as TextView


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout_word, parent, false)

            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val requestMoneyModel = requestWordList[position]
            holder.txtWord.setText(requestMoneyModel.word)
        }

        override fun getItemCount(): Int {
            return requestWordList.size
        }
    }
}