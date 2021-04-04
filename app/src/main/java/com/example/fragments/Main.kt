package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fragments.databinding.FragmentMainBinding
import java.nio.BufferUnderflowException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Main.newInstance] factory method to
 * create an instance of this fragment.
 */
class Main : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var ticTacToeBtn: Button
    private lateinit var diceBtn: Button
    private lateinit var editBtn: Button

    private var fName = "Player 1"
    private var sName = "Player 2"

    private var fWins = 0
    private var sWins = 0

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
        binding = FragmentMainBinding.inflate(inflater, container, false)
        ticTacToeBtn = binding.tictactoeBtn
        diceBtn = binding.diceBtn
        editBtn = binding.editBtn

        val firstName = arguments?.getString("fName")
        val secondName = arguments?.getString("sName")

        if(firstName != null){
            fName = firstName
        }
        if(secondName != null){
            sName = secondName
        }

        val firstWins = arguments?.getInt("fWins")
        val secondWins = arguments?.getInt("sWins")

        if(firstWins != null){
            fWins = firstWins
        }
        if(secondWins != null){
            sWins = secondWins
        }

        binding.player1Name.text = fName
        binding.player2Name.text = sName

        binding.player1Score.text = fWins.toString()
        binding.player2Score.text = sWins.toString()

        ticTacToeBtn.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("fName", fName)
            bundle.putString("sName", sName)
            bundle.putInt("fWins", fWins)
            bundle.putInt("sWins", sWins)

            val fragment = TicTacToe()
            fragment.arguments = bundle

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commit()
        }

        diceBtn.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("fName", fName)
            bundle.putString("sName", sName)
            bundle.putInt("fWins", fWins)
            bundle.putInt("sWins", sWins)

            val fragment = Dice()
            fragment.arguments = bundle

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commit()
        }

        editBtn.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, Names())
                ?.commit()
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
         * @return A new instance of fragment Main.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Main().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}