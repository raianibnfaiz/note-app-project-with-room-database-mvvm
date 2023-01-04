package com.raian.noteapproomdatabase.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.raian.noteapproomdatabase.fragments.NoteListFragmentDirections
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import com.raian.noteapproomdatabase.model.Note
import kotlinx.android.synthetic.main.list_item.view.*

class NoteAdapter(val context : Context, val viewModel : ApplicationViewModel):
RecyclerView.Adapter<NoteAdapter.NoteViewHolder>()

{
    private var noteList = viewModel.readAllNoteData.value
    class NoteViewHolder(view:View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return NoteViewHolder(root)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentData = noteList?.get(position)
        holder.itemView.title.setText("Title: ${currentData?.title}")
        holder.itemView.description.setText("Description: ${currentData?.description}")
        holder.itemView.noteWriter.setText("Writer: ${currentData?.writer}")
        holder.itemView.update.setOnClickListener{
            val action = currentData?.let { it1 ->
                NoteListFragmentDirections.actionNoteListFragmentToUpdateNoteFragment(
                    it1
                )
            }
            if (action != null) {
                Navigation.findNavController(holder.itemView).navigate(action)
            }
//            val action = currentData?.let { it1 ->
//                NoteListFragmentDirections.actionNoteListFragmentToUpdateNoteFragment(
//                    it1
//                )
//            }
//            if (action != null) {
//                Navigation.findNavController(holder.itemView).navigate(action)
//            }

        }
        holder.itemView.delete.setOnClickListener{

            val builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(true)
                .setPositiveButton("Yes") { dialog, id ->
                    if (currentData != null) {
                        viewModel.deleteNote(currentData)
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
        return noteList?.size ?: 0
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}