package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.fragments.databinding.FragmentTicTacToeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TicTacToe.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicTacToe : Fragment() {
    private lateinit var binding: FragmentTicTacToeBinding

    var arr = mutableListOf<Button>()
    var pNumber = 1
    var flag = 0
    lateinit var stateText: TextView

    var fName = "Player 1"
    var sName = "Player 2"

    var p1Wins = 0
    var p2Wins = 0

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
        binding = FragmentTicTacToeBinding.inflate(inflater, container, false)

        arr.add(binding.button1)
        arr.add(binding.button2)
        arr.add(binding.button3)
        arr.add(binding.button4)
        arr.add(binding.button5)
        arr.add(binding.button6)
        arr.add(binding.button7)
        arr.add(binding.button8)
        arr.add(binding.button9)

        val firstName = arguments?.getString("fName")
        val secondName = arguments?.getString("sName")

        val firstWins = arguments?.getInt("fWins")
        val secondWins = arguments?.getInt("sWins")

        if(firstName != null){
            fName = firstName
        }
        if(secondName != null){
            sName = secondName
        }

        if(firstWins != null){
            p1Wins = firstWins
        }
        if(secondWins != null){
            p2Wins = secondWins
        }

        stateText = binding.gameState
        stateText.text = fName

        for(button in arr) {
            button.setOnClickListener {
                fieldHandler(it)
            }
        }

        binding.reset.setOnClickListener{
            resetHandler(it)
        }

        binding.exit.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("fName", fName)
            bundle.putString("sName", sName)
            bundle.putInt("fWins", p1Wins)
            bundle.putInt("sWins", p2Wins)

            var fragment = Main()
            fragment.arguments = bundle

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commit()
        }

        return binding.root
    }

    fun fieldHandler(view: View){
        if(flag == 0 && view is Button){
            if(view.text == "X" || view.text == "O"){
                return
            }

            when(pNumber % 2){
                1 -> {
                    view.text = "X"
                    pNumber++
                }
                0 ->{
                    view.text = "O"
                    pNumber++
                }
            }

            if(pNumber % 2 == 1){
                stateText.text = fName
            }
            else{
                stateText.text = sName
            }

            flag = checkWin(arr, pNumber)
            if(flag == 1){
                if(pNumber % 2 == 1){
                    stateText.text = sName + " Wins!!!"
                    p2Wins++
                }
                else{
                    stateText.text = fName + " Wins!!!"
                    p1Wins++
                }
            }
            else if(flag == -1){
                stateText.text = "Draw!!!"
            }
        }
    }

    fun resetHandler(view: View){
        for(i in 0..8){
            arr[i].text = ""
        }
        flag = 0
        pNumber = 1
        stateText.text = fName
    }

    private fun checkWin(arr: MutableList<Button>, count: Int): Int{
        if(arr[0].text.toString() == arr[1].text.toString() && arr[1].text.toString() == arr[2].text.toString() && arr[1].text.toString() != ""
            || arr[3].text.toString() == arr[4].text.toString() && arr[4].text.toString() == arr[5].text.toString() && arr[3].text.toString() != ""
            || arr[6].text.toString() == arr[7].text.toString() && arr[7].text.toString() == arr[8].text.toString() && arr[6].text.toString() != ""

            || arr[0].text.toString() == arr[3].text.toString() && arr[3].text.toString() == arr[6].text.toString() && arr[0].text.toString() != ""
            || arr[1].text.toString() == arr[4].text.toString() && arr[4].text.toString() == arr[7].text.toString() && arr[1].text.toString() != ""
            || arr[2].text.toString() == arr[5].text.toString() && arr[5].text.toString() == arr[8].text.toString() && arr[2].text.toString() != ""

            || arr[0].text.toString() == arr[4].text.toString() && arr[4].text.toString() == arr[8].text.toString() && arr[0].text.toString() != ""
            || arr[2].text.toString() == arr[4].text.toString() && arr[4].text.toString() == arr[6].text.toString() && arr[2].text.toString() != ""){
            return 1
        }
        else if(count > 9){
            return -1
        }
        return 0
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TicTacToe.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TicTacToe().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}