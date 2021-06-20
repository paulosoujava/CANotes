package br.com.canotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.canotes.R
import br.com.canotes.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment(), ListAction {
    private val notesListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        addNote.setOnClickListener {
            gotToNoteDetails()
        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            loadingView.visibility = View.GONE
            notesListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(it.sortedBy { it -> it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }
    private fun gotToNoteDetails(id: Long = 0L){
        val action = ListFragmentDirections.actionGotToNote(id)
        Navigation.findNavController(notesListView).navigate(action)

    }

    override fun onClick(id: Long) {
        gotToNoteDetails(id)
    }

}