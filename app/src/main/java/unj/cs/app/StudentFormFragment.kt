package unj.cs.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import unj.cs.app.data.Student
import unj.cs.app.data.StudentList
import unj.cs.app.databinding.FragmentStudentFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ID = "argStudentId"
private const val ARG_NAME = "argStudentName"
private const val ARG_POS = "argPosition"

/**
 * A simple [Fragment] subclass.
 * Use the [StudentFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentFormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var idParam: String? = null
    private var nameParam: String? = null
    private var positionParam: Int? = null

    private var _binding: FragmentStudentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idParam = it.getString(ARG_ID)
            nameParam = it.getString(ARG_NAME)
            positionParam = it.getInt(ARG_POS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStudentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addStudentBtn = view.findViewById<Button>(R.id.addStudentButton)
        val idText = view.findViewById<TextInputEditText>(R.id.inputStudentId)
        val nameText = view.findViewById<TextInputEditText>(R.id.inputStudentName)

        if (positionParam!! >= 0) {
            addStudentBtn.text = view.context.resources.getString(R.string.edit_button_label)
        } else {
            addStudentBtn.text = view.context.resources.getString(R.string.add_button_label)
        }

        idParam?.let {
            idText.setText(idParam)
        }

        nameParam?.let {
            nameText.setText(nameParam)
        }

        addStudentBtn.setOnClickListener{
            val student = Student(idText.text.toString(), nameText.text.toString())
            val studentList: MutableList<Student> = StudentList.list
            lateinit var toastMessage: String
            idParam?.let {
                nameParam?.let {
                    studentList[positionParam!!] = student
                    toastMessage = "Student data was Edited"
                }
            } ?: run {
                studentList.add(student)
                toastMessage = "${student.name} was Added"
            }

            StudentList.list = studentList

            val action =
                StudentFormFragmentDirections.actionStudentFormFragmentToStudentListFragment()
            view.findNavController().navigate(action)
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, param1)
                    putString(ARG_NAME, param2)
                }
            }
    }
}