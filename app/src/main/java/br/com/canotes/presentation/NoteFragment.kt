package br.com.canotes.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.com.canotes.R
import br.com.canotes.framework.NoteViewModel
import br.com.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        arguments?.let{
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if(noteId != 0L){
            viewModel.getNote(noteId)
        }

        checkButton.setOnClickListener {
            if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                val time: Long = System.currentTimeMillis()
                currentNote.title = titleView.text.toString()
                currentNote.content = contentView.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else
                Navigation.findNavController(it).popBackStack()
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(titleView).popBackStack()
            }else{
                Toast.makeText(context, "Ops big fail", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                currentNote = it
                titleView.setText( it.title, TextView.BufferType.EDITABLE)
                contentView.setText( it.content, TextView.BufferType.EDITABLE)
            }
        })
    }
    private fun hideKeyboard(){
        val imm : InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote -> {
                if(context != null  && noteId != 0L){
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are your sure you want delete this note?")
                        .setPositiveButton("YES"){ _, _ ->  viewModel.deleteNote(currentNote)}
                        .setNegativeButton("CANCEL"){_, _ -> }
                        .create()
                        .show()

                }
            }

        }
        return true
    }
}