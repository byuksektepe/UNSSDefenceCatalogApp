package com.unss.defencecatalog.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unss.defencecatalog.data.model.KatgoriResponseItem
import com.unss.defencecatalog.databinding.CardCategoriesHomeBinding
import com.unss.defencecatalog.util.GlideUtil
import com.unss.defencecatalog.util.OnItemClickListener

class CategoryAdapter(
    var categories: List<KatgoriResponseItem>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CardCategoriesHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CardCategoriesHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtCategoryName.text = categories.get(position).kategoriAdi
                GlideUtil.hiddenImage(imageCat.context, categories.get(position).kategoriResimUrl, imageCat)
                cardViewCat.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(filteredKategoriList: List<KatgoriResponseItem>) {
        categories = filteredKategoriList
    }

}