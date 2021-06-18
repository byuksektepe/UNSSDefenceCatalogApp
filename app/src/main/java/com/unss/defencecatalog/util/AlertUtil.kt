package com.unss.defencecatalog.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import com.unss.defencecatalog.R
import com.unss.defencecatalog.data.model.Urunler
import com.unss.defencecatalog.databinding.ActivityUrunlerBinding
import com.unss.defencecatalog.ui.product.UrunlerAdapter

object AlertUtil {
    fun appAlert(act: Activity, head: String?, body: String?, checkAlt: Boolean) {
        val bld = AlertDialog.Builder(act)
        bld.setTitle(head)
        bld.setMessage(body)
        if (checkAlt) {
            bld.setPositiveButton(
                R.string.ayarlara_git,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    val intent = Intent(Settings.ACTION_SETTINGS)
                    act.startActivity(intent)
                    act.finish()
                })
            bld.setNegativeButton(R.string.cikis,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    act.finish()
                })
        } else {
            bld.setPositiveButton(R.string.et,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    act.finish()
                })
            bld.setNegativeButton(R.string.hy,
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        }
        bld.show()
    }

    fun invalidAlert(act: Activity, head: String?, body: String?) {
        val bld = AlertDialog.Builder(act)
        bld.setTitle(head)
        bld.setMessage(body)
        bld.setPositiveButton(
            R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()

            })
        bld.show()
    }

    fun sortingAlert(act: Activity, head: String?, list: List<Urunler>, adapter: UrunlerAdapter, binding: ActivityUrunlerBinding) {
        val bld = AlertDialog.Builder(act)
        bld.setTitle(head)
        var opts = arrayOf(act.getString(R.string.atoz), act.getString(R.string.ztoa))
        bld.setItems(opts) { dialog, position ->
            when (position) {
                0 -> {
                    list?.let{
                        var urunListSort = it.sortedBy { it.urunAdi }
                        adapter.setData(urunListSort)
                        binding.btnSort.background = binding.btnSort.context.getDrawable(R.drawable.ic_atoz)
                        adapter.notifyDataSetChanged()
                    }
                }
                1 -> {
                    list?.let{
                        var urunListSort = it.sortedByDescending { it.urunAdi }
                        adapter.setData(urunListSort)
                        binding.btnSort.background = binding.btnSort.context.getDrawable(R.drawable.ic_ztoa)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        bld.show()
    }
}