package com.raian.noteapproomdatabase.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import com.raian.noteapproomdatabase.model.Writer
import kotlinx.android.synthetic.main.fragment_add_writer.*


class AddWriterFragment : Fragment() {
    private lateinit var viewModel: ApplicationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_writer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        add_writer_button.setOnClickListener {
            addWriterInfo()
        }
    }


    fun addWriterInfo(){
        val name = name.text.toString()
        val age = age.text.toString()

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) ){
            val writer = Writer(0,name,age.toInt())
            viewModel.addWriter(writer)
            findNavController().navigate(R.id.action_addWriterFragment_to_writerListFragment)
        }
    }

}