package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ticTacToe: TicTacToe
    private lateinit var main: Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        ticTacToe = TicTacToe()
        main = Main()

        setContentView(rootView)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, main)
            .commit()
    }

    fun fieldHandler(view: View){
        ticTacToe.fieldHandler(view)
    }

    fun resetHandler(view: View){
        ticTacToe.resetHandler(view)
    }
}