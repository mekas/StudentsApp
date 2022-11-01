package unj.cs.app

import android.view.View
import android.widget.TextView
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import unj.cs.app.data.Student
import unj.cs.app.data.StudentList

class StudentAdapter(context: Context): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var studentList:MutableList<Student> = StudentList.list
    init{
        if(studentList.isEmpty()){
            val names = context.resources.getStringArray(R.array.student_names).toList()
            val ids = context.resources.getStringArray(R.array.student_ids).toList()
            for (i in names.indices){
                val student = Student(ids[i], names[i])
                studentList.add(student)
            }
        }
    }

    class StudentViewHolder(val view:View): RecyclerView.ViewHolder(view){
        val nameTextView:TextView = view.findViewById<TextView>(R.id.nameTextView)
        val idTextView:TextView = view.findViewById<TextView>(R.id.idTextView)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view_student, parent, false)
        return StudentViewHolder(layout)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        //val context = holder.view.context
        val id:String = studentList[position].id
        val name:String = studentList[position].name
        holder.idTextView.text = id
        holder.nameTextView.text = name

        holder.itemView.setOnClickListener(){
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment( argPosition = position, argStudentId = id, argStudentName = name)
            holder.view.findNavController().navigate(action)
        }
    }
}