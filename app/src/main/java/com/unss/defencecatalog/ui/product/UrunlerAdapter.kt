package com.unss.defencecatalog.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unss.defencecatalog.data.model.Urunler
import com.unss.defencecatalog.databinding.CardProductsBinding
import com.unss.defencecatalog.util.GlideUtil
import com.unss.defencecatalog.util.OnItemClickListener

class UrunlerAdapter (
    var urunler: List<Urunler>,
    var onItemClickListener: OnItemClickListener ) : RecyclerView.Adapter<UrunlerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardProductsBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return urunler.size
    }

    fun setData(siralanmisUrunler:List<Urunler>) {
        urunler = siralanmisUrunler
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                binding.txtProductName.text = urunler.get(position).urunAdi
                GlideUtil.hiddenImage(imageProd.context, urunler.get(position).urunResim, imageProd)

                cardViewProd.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}