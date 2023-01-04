package com.raian.noteapproomdatabase.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.raian.noteapproomdatabase.fragments.NoteListFragmentDirections
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.fragments.WriterListFragmentDirections
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import com.raian.noteapproomdatabase.model.Note
import com.raian.noteapproomdatabase.model.Writer
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.writer_list_item.view.*

class WriterAdapter(val context : Context, val viewModel : ApplicationViewModel):
RecyclerView.Adapter<WriterAdapter.WriterViewHolder>()
{
    private var writerList = viewModel.readAllWriterData.value
    class WriterViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriterViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.writer_list_item, parent,false)
        return WriterViewHolder(root)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WriterViewHolder, position: Int) {
        val currentData = writerList?.get(position)
        holder.itemView.name.setText("Name: ${currentData?.name}")
        holder.itemView.age.setText("Age: ${currentData?.age}")
        holder.itemView.updateWriter.setOnClickListener{
            val action = currentData?.let { it1 ->
                WriterListFragmentDirections.actionWriterListFragmentToUpdateWriterFragment(
                    it1
                )
            }
            if (action != null) {
                Navigation.findNavController(holder.itemView).navigate(action)
            }
        }
        holder.itemView.deleteWriter.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(true)
                .setPositiveButton("Yes") { dialog, id ->
                    if (currentData != null) {
                        viewModel.deleteWriter(currentData)
                    }
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        }

    }

    override fun getItemCount(): Int {
        //Toast.makeText(context, writerList?.size.toString(), Toast.LENGTH_SHORT).show()
        return writerList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(writer: List<Writer>){
        this.writerList = writer
        notifyDataSetChanged()
    }


}