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
import com.raian.noteapproomdatabase.model.Writer
import kotlinx.android.synthetic.main.fragment_update_writer.*

class UpdateWriterFragment : Fragment() {
    lateinit var viewModel:ApplicationViewModel
    val args : UpdateWriterFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_writer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        val writer = args.writer
        nameUpdate.setText(writer.name)
        ageUpdate.setText(writer.age.toString())
        update_writer_button.setOnClickListener {
            val name = nameUpdate.text.toString()
            val age = ageUpdate.text.toString()
            val newWriter = Writer(writer.id,name,age.toInt())
            viewModel.updateWriter(newWriter)

            nameUpdate.text?.clear()
            ageUpdate.text?.clear()
            findNavController().navigate(R.id.action_updateWriterFragment_to_writerListFragment)
        }
    }
}