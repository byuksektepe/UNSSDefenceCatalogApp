package com.unss.defencecatalog.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide.init
import com.unss.defencecatalog.R
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.data.model.Urunler
import com.unss.defencecatalog.databinding.ActivityUrunlerBinding
import com.unss.defencecatalog.ui.detail.SSUrunDetayActivity
import com.unss.defencecatalog.util.*

class UrunlerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrunlerBinding
    private var kategoriList: KatgoriResponseItem?=null
    private lateinit var urunlerAdapter: UrunlerAdapter
    private var isList: Boolean = true
    private var layoutManager: RecyclerView.LayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityUrunlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strMovedKategori:String = intent.getStringExtra(Const.MOVED_CATEGORY_ID)!!
        kategoriList = ObjectUtil.jsonStringToObject(strMovedKategori)

        binding.apply {
            txtCatName.text = kategoriList!!.kategoriAdi
            btnList.setOnClickListener {
                if (isList){
                    layoutManager = StaggeredGridLayoutManager(Const.PRODUCTS_GRID_SIZE, StaggeredGridLayoutManager.VERTICAL)
                    btnList.background = getDrawable(R.drawable.ic_grid)
                }else{
                    layoutManager = LinearLayoutManager(applicationContext)
                    btnList.background = getDrawable(R.drawable.ic_liste)
                }
                isList = !isList
                rcvUrunler.setLayoutManager(layoutManager)
            }

            btnSort.setOnClickListener {
                AlertUtil.sortingAlert(
                    this@UrunlerActivity,
                    getString(R.string.sıralamaturu),
                    kategoriList!!.urunler,
                    urunlerAdapter,
                    binding
                )
            }
        }
        initRecycleView(kategoriList!!.urunler)

    }

    private fun initRecycleView(urunList: List<Urunler>) {
        binding.apply {
            urunlerAdapter = UrunlerAdapter(urunList!!, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val tiklananUrun: Urunler = urunList.get(position)
                    val strTiklananUrun = ObjectUtil.objectToString(tiklananUrun)
                    val urunDetailIntent = Intent(this@UrunlerActivity, SSUrunDetayActivity::class.java)
                    urunDetailIntent.putExtra(Const.MOVED_CATEGORY_ID, strTiklananUrun)
                    startActivity(urunDetailIntent)
                }
            })
            rcvUrunler.adapter = urunlerAdapter
            rcvUrunler.setLayoutManager(LinearLayoutManager(applicationContext))
        }
    }
}