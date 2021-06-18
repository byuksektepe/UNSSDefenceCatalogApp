package com.unss.defencecatalog.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unss.defencecatalog.R
import com.unss.defencecatalog.data.model.Urunler
import com.unss.defencecatalog.databinding.ActivitySsurunDetayBinding
import com.unss.defencecatalog.util.Const
import com.unss.defencecatalog.util.GlideUtil
import com.unss.defencecatalog.util.ObjectUtil

class SSUrunDetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySsurunDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivitySsurunDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val strMovedProduct = intent.getStringExtra(Const.MOVED_CATEGORY_ID)!!
        val urunModel: Urunler = ObjectUtil.jsonStringToObject(strMovedProduct)

        binding.apply {
            txtProdTitle.text = urunModel.urunAdi
            GlideUtil.hiddenImage(imageProdPhoto.context, urunModel.urunResim, imageProdPhoto)
            txtDetail.text = urunModel.urunDetay
            txtCompanyTitle.text = urunModel.urunSirket
        }
    }
}