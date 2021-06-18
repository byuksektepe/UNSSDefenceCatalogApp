package com.unss.defencecatalog.ui.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.unss.defencecatalog.R
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.databinding.ActivityMainBinding
import com.unss.defencecatalog.ui.product.UrunlerActivity
import com.unss.defencecatalog.util.AlertUtil
import com.unss.defencecatalog.util.Const
import com.unss.defencecatalog.util.ObjectUtil
import com.unss.defencecatalog.util.OnItemClickListener
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var kategoriViewModel: KategoriViewModel = KategoriViewModel()
    var kategoriDatas: List<KatgoriResponseItem>? = null
    var kategoriAdapter: CategoryAdapter? = null
    public var categoryId: String? = null

    override fun onBackPressed() {
        AlertUtil.appAlert(
            this,
            getString(R.string.are_you_sure),
            getString(R.string.exit_message),
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
    }

    private fun initRecycleView(categories: List<KatgoriResponseItem>) {
        kategoriAdapter = CategoryAdapter(categories, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productIntent = Intent(applicationContext, UrunlerActivity::class.java)
                val strKategoriTitle:String = ObjectUtil.objectToString(categories.get(position))
                productIntent.putExtra(Const.MOVED_CATEGORY_ID, strKategoriTitle)
                startActivity(productIntent)

            }
        })

        binding.apply {
            rcvKategoriler.adapter = kategoriAdapter
            rcvKategoriler.layoutManager =
                GridLayoutManager(
                    applicationContext,
                    Const.CATEGORIES_GRID_SIZE,
                    GridLayoutManager.HORIZONTAL,
                    false
                )

            srcKategori.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchCat(newText)
                    kategoriAdapter!!.notifyDataSetChanged()
                    return false
                }

            })
        }
    }

    private fun searchCat(kategoriText: String?) {
        kategoriText?.let {
            kategoriDatas?.let{
                var categoryListFilter = it.filter {
                    it.kategoriAdi.toLowerCase(Locale(Const.LOCALE))
                        .contains(kategoriText.toLowerCase(Locale(Const.LOCALE)))
                }
                kategoriAdapter!!.setData(categoryListFilter)
                kategoriAdapter!!.notifyDataSetChanged()
            }
        }

    }

    private fun initViewModel() {
        kategoriViewModel.apply {
            kategorilerLiveData.observe(this@MainActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "observe: " + it.toString())
                kategoriDatas = it
                initRecycleView(kategoriDatas!!)
            })

            loading?.observe(this@MainActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "loading:")

            })

            error?.observe(this@MainActivity, androidx.lifecycle.Observer {
                Log.e("Artyom", "error:")
            })
        }
    }
}