package com.raian.noteapproomdatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import com.raian.noteapproomdatabase.model.Note
import kotlinx.android.synthetic.main.fragment_update_note.*


class UpdateNoteFragment : Fragment() {
    lateinit var viewModel : ApplicationViewModel
    val args: UpdateNoteFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        val note = args.note
        titleNote.setText(note.title)
        descriptionNote.setText(note.description)
        writerNote.setText(note.writer)
        update_button.setOnClickListener {
            val title = titleNote.text.toString()
            val description = descriptionNote.text.toString()
            val writer = writerNote.text.toString()
            val newNote = Note(note.id,title,description,writer)
            viewModel.updateNote(newNote)


            titleNote.text?.clear()
            descriptionNote.text?.clear()
            writerNote.text?.clear()

            findNavController().navigate(R.id.action_updateNoteFragment_to_noteListFragment)
        }

    }
}