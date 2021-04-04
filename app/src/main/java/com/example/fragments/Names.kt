package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.text.set
import androidx.navigation.Navigation
import com.example.fragments.databinding.FragmentNamesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Names.newInstance] factory method to
 * create an instance of this fragment.
 */
class Names : Fragment() {
    private lateinit var binding: FragmentNamesBinding
    private lateinit var submitBtn: Button
    private var fName = "Player 1"
    private var sName = "Player 2"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamesBinding.inflate(inflater, container, false)
        submitBtn = binding.submit

        submitBtn.setOnClickListener{
            if(binding.firstName.text.toString() != "") {
                fName = binding.firstName.text.toString()
            }
            if(binding.secondName.text.toString() != "") {
                sName = binding.secondName.text.toString()
            }

            val bundle = Bundle()
            bundle.putString("fName", fName)
            bundle.putString("sName", sName)

            val main = Main()
            main.arguments = bundle

            Navigation.findNavController(binding.root).navigate(R.id.action_names_to_main, bundle)

            /*activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, main)
                ?.commit()*/
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Names.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Names().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}