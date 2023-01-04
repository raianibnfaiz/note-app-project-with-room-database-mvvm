package com.raian.noteapproomdatabase.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.adapter.WriterAdapter
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import kotlinx.android.synthetic.main.fragment_writer_list.*


class WriterListFragment : Fragment() {
    private lateinit var viewModel : ApplicationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]
        val adapter = WriterAdapter(requireContext(), viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllWriterData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.writer_list_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.home ->{
                findNavController().navigate(R.id.action_writerListFragment_to_homeFragment)
                true
            }else->super.onOptionsItemSelected(item)
        }

    }
}