package com.example.rockpaperscissors

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    lateinit var userChoice: String

    fun choiceRock (view: View) {
        userChoice = "Rock" //Coloca a escolha do usuario na variavel.
        val machineChoice = sort() //Sorteia a escolha da maquina.

        winner(userChoice, machineChoice) //Calcula quem é o vencedor e mostra no resultado.
    }

    fun choicePaper (view: View) {
        userChoice = "Paper"
        val machineChoice = sort()

        winner(userChoice, machineChoice)
    }

    fun choiceScissors (view: View) {
        userChoice = "Scissors"
        val machineChoice = sort()

        winner(userChoice, machineChoice)
    }


    fun sort (): String {
        val options = arrayOf("Rock", "Paper", "Scissors")
        val num = (0 until options.size).random()

        val imageApp = findViewById<ImageView>(R.id.image_app)
        when (options[num]) {
            "Rock" -> {
                imageApp.setImageResource(R.drawable.rock_icon)
            }

            "Paper" -> {
                imageApp.setImageResource(R.drawable.paper_icon)
            }

            "Scissors" -> {
                imageApp.setImageResource(R.drawable.scissors_icon)
            }
        }

        return options[num]
    }

    fun winner (userChoice: String, machineChoice: String) {
        val textResult = findViewById<TextView>(R.id.text_result)

        if (userChoice == machineChoice) {
            textResult.setText("DRAW!!!")
        } else {
            when {
                userChoice == "Rock" && machineChoice == "Paper" -> textResult.setText("YOU LOSE!!!")
                userChoice == "Paper" && machineChoice == "Scissors" -> textResult.setText("YOU LOSE!!!")
                userChoice == "Scissors" && machineChoice == "Rock" -> textResult.setText("YOU LOSE!!!")
                userChoice == "Rock" && machineChoice == "Scissors" -> textResult.setText("YOU WIN!!!")
                userChoice == "Paper" && machineChoice == "Rock" -> textResult.setText("YOU WIN!!!")
                userChoice == "Scissors" && machineChoice == "Paper" -> textResult.setText("YOU WIN!!!")
            }
        }
    }






}