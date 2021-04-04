package com.example.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.fragments.databinding.FragmentDiceBinding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Dice.newInstance] factory method to
 * create an instance of this fragment.
 */
class Dice : Fragment() {
    private lateinit var binding: FragmentDiceBinding
    var pNumber = 1
    val dices = mutableListOf<Drawable>()
    lateinit var stateText: TextView
    lateinit var playerText: TextView
    lateinit var btn: Button

    var fName = "Player 1"
    var sName = "Player 2"

    var p1Score = 0
    var p2Score = 0

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
        binding = FragmentDiceBinding.inflate(inflater, container, false)
        btn = binding.roll

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

        playerText = binding.playerName
        playerText.text = fName

        stateText = binding.gameState

        for(i in 1..6){
            var imageId = "dice_$i"
            var resId = resources.getIdentifier(imageId, "drawable", activity?.packageName)
            dices.add(resources.getDrawable(resId))
        }

        btn.setOnClickListener {
            val rnd = Random
            if(it is Button) {
                val image = binding.diceImage
                val image2 = binding.diceImage2

                val fNumber = rnd.nextInt(0, 6)
                val sNumber = rnd.nextInt(0, 6)

                image.setImageDrawable(dices[fNumber])
                image2.setImageDrawable(dices[sNumber])

                if(pNumber % 2 == 1){
                    p1Score = fNumber + sNumber
                    playerText.text = sName
                    stateText.text = "Playing..."
                }
                else{
                    p2Score = fNumber + sNumber
                    playerText.text = fName
                    when {
                        p1Score > p2Score -> {
                            stateText.text = fName + " Wins!!!"
                            p1Wins++
                        }
                        p1Score < p2Score -> {
                            stateText.text = sName + " Wins!!!"
                            p2Wins++
                        }
                        else -> {
                            stateText.text = "Draw"
                        }
                    }
                }
                pNumber++
            }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Dice.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Dice().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}