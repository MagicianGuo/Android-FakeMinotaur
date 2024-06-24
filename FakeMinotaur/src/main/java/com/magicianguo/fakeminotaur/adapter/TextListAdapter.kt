package com.magicianguo.fakeminotaur.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.magicianguo.fakeminotaur.R
import com.magicianguo.fakeminotaur.bean.BeanText
import com.magicianguo.fakeminotaur.util.SPUtils
import com.magicianguo.fakeminotaur.view.MainView

class TextListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mData = mutableListOf<BeanText?>(null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TextListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_text_list_item, parent, false)
        )
    }

    fun updateList(list: List<BeanText>) {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextListHolder) {
            val beanText = mData[position]
            holder.flDefault.isVisible = beanText != null
            holder.flAdd.isVisible = beanText == null
            if (beanText != null) {
                holder.etText.setText(beanText.text)
                holder.btnSelect.isVisible = !beanText.selected
                holder.btnDelete.isVisible = !beanText.selected
                holder.tvSelected.isVisible = beanText.selected
                if (beanText.selected) {
                    holder.flDefault.setBackgroundResource(R.drawable.bg_text_list_item_selected)
                } else {
                    holder.flDefault.setBackgroundResource(R.drawable.bg_text_list_item_normal)
                }
                holder.itemView.setOnClickListener {
                    if (!beanText.selected) {
                        selectItem(position)
                    }
                }
                holder.btnSelect.setOnClickListener {
                    if (!beanText.selected) {
                        selectItem(position)
                    }
                }
                holder.btnEdit.setOnClickListener {
                    val enabled = holder.etText.isEnabled
                    if (enabled) {
                        holder.etText.isEnabled = false
                        holder.btnEdit.text = "编辑"
                        holder.btnSelect.isVisible = true
                        beanText.text = holder.etText.text.toString()
                        SPUtils.setTextList(mData)
                        notifyItemChanged(position)
                    } else {
                        holder.etText.isEnabled = true
                        holder.btnEdit.text = "保存"
                        holder.etText.requestFocus()
                        holder.btnSelect.isVisible = false
                    }
                }
                holder.btnDelete.setOnClickListener {
                    mData.remove(beanText)
                    notifyDataSetChanged()
                }
            } else {
                holder.itemView.setOnClickListener {
                    mData.add(mData.size - 1, BeanText("", false))
                    SPUtils.setTextList(mData)
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun selectItem(position: Int) {
        MainView.notifyTextChange(mData[position]!!.text)
        mData.forEachIndexed { index, beanText ->
            beanText?.selected = index == position
        }
        SPUtils.setTextList(mData)
        notifyDataSetChanged()
    }

    private class TextListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flAdd: FrameLayout = itemView.findViewById(R.id.fl_add)
        val flDefault: FrameLayout = itemView.findViewById(R.id.fl_default)
        val etText: EditText = itemView.findViewById(R.id.et_text)
        val btnSelect: Button = itemView.findViewById(R.id.btn_select)
        val btnEdit: Button = itemView.findViewById(R.id.btn_edit)
        val btnDelete: Button = itemView.findViewById(R.id.btn_delete)
        val tvSelected: TextView = itemView.findViewById(R.id.tv_selected)
    }
}