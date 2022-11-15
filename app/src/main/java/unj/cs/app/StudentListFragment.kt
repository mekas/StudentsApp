package unj.cs.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import unj.cs.app.databinding.FragmentStudentListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

import unj.cs.app.data.StudentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "argToast"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var toastParam: String? = null

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var  studentRecyclerView: RecyclerView;
    private val viewModel : StudentViewModel by viewModels(){StudentViewModel.Factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            toastParam = it.getString(ARG_PARAM1)
        }

        studentAdapter = context?.let {
            StudentAdapter(viewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        //_binding!!.viewModel = viewModel
        //load student adapter with preload data
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentRecyclerView = binding.recyclerViewStudent
        studentRecyclerView.adapter = studentAdapter
        studentRecyclerView.layoutManager = LinearLayoutManager(context)

        studentRecyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        activity?.title = "Student List"

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener(){
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment(null, null)
            view.findNavController().navigate(action)
        }
    }

    /*
    override fun onStart() {
        super.onStart()
        studentRecyclerView.invalidate()
    }*/

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            StudentListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

        var studentAdapter:StudentAdapter?=null
    }
}

