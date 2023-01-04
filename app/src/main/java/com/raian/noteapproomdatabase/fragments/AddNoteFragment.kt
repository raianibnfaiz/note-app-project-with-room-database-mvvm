package com.raian.noteapproomdatabase.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import com.raian.noteapproomdatabase.model.Note
import kotlinx.android.synthetic.main.fragment_add_note.*


class AddNoteFragment : Fragment() {
    private var writerName: String = "Default"
    private lateinit var spinner : Spinner
    private var writers: MutableList<CharSequence> = mutableListOf()
    private lateinit var viewModel : ApplicationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        writers = mutableListOf("Add a writer")

        viewModel.readAllWriterData.observe(viewLifecycleOwner) {
            for(item in it){
                writers.add(item.name)
            }
        }
        val view: View = inflater.inflate(R.layout.fragment_add_note, container, false)
        spinner = view.findViewById<View>(R.id.writerSpinner) as Spinner
        val mSortAdapter: ArrayAdapter<CharSequence> = ArrayAdapter<CharSequence>(
            requireContext(), android.R.layout.simple_spinner_item, writers
        )
        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = mSortAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    writerName = p0?.getItemAtPosition(p2).toString()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        add_button.setOnClickListener {
            val newNoteTitle = noteTitle.text.toString()
            val newNoteDescription = noteDescription.text.toString()
            val newNoteWriter = writerName
            if(!TextUtils.isEmpty(newNoteTitle) && !TextUtils.isEmpty(newNoteDescription) && !TextUtils.isEmpty(newNoteWriter)){
                val note = Note(0,newNoteTitle,newNoteDescription, newNoteWriter)
                viewModel.addNote(note)
            }

            noteTitle.text?.clear()
            noteDescription.text?.clear()

            findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }
    }
}