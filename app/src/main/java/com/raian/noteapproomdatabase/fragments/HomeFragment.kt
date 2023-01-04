package com.raian.noteapproomdatabase.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.raian.noteapproomdatabase.R
import com.raian.noteapproomdatabase.model.ApplicationViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ApplicationViewModel::class.java]


        floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        noteListButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_noteListFragment)
        }
        writersButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_writerListFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addWriter ->{
                findNavController().navigate(R.id.action_homeFragment_to_addWriterFragment)
                true
            }
            R.id.home ->{
                findNavController().navigate(R.id.action_homeFragment_to_writerListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

}