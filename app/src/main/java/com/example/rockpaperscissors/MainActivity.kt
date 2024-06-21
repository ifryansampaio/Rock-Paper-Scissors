package com.example.rockpaperscissors

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declarar lateinit para inicializar posteriormente
    private lateinit var userChoice: String
    private var score: Int = 0
    private lateinit var scaleAnim: Animation
    private lateinit var imageViewRock: ImageView
    private lateinit var imageViewPaper: ImageView
    private lateinit var imageViewScissors: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Carregar a animação do XML
        scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_anim)

        // Obter referências para as ImageViews
        imageViewRock = findViewById(R.id.image_rock) // Adicionar lateinit
        imageViewPaper = findViewById(R.id.image_paper) // Adicionar lateinit
        imageViewScissors = findViewById(R.id.image_scissors) // Adicionar lateinit

        // Aplicar insets de janela para compatibilidade com gestos de borda
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun choiceRock(view: View) {
        userChoice = "Rock" // Coloca a escolha do usuário na variável.
        val machineChoice = sort() // Sorteia a escolha da máquina

        imageViewRock.startAnimation(scaleAnim) // Aplica a animação à ImageView

        winner(userChoice, machineChoice) // Calcula quem é o vencedor e mostra o resultado
    }

    fun choicePaper(view: View) {
        userChoice = "Paper"
        val machineChoice = sort()

        imageViewPaper.startAnimation(scaleAnim)

        winner(userChoice, machineChoice)
    }

    fun choiceScissors(view: View) {
        userChoice = "Scissors"
        val machineChoice = sort()

        imageViewScissors.startAnimation(scaleAnim)

        winner(userChoice, machineChoice)
    }

    fun sort(): String {
        val options = arrayOf("Rock", "Paper", "Scissors")
        val num = options.indices.random()

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

    fun winner(userChoice: String, machineChoice: String) {
        val textResult = findViewById<TextView>(R.id.text_result)
        val textScore = findViewById<TextView>(R.id.text_score)
        if (userChoice == machineChoice) {
            textResult.text = "DRAW!!!" // Atualizar para usar .text ao invés de .setText
        } else {
            when {
                userChoice == "Rock" && machineChoice == "Paper" -> textResult.text = "YOU LOSE!!!"
                userChoice == "Paper" && machineChoice == "Scissors" -> textResult.text = "YOU LOSE!!!"
                userChoice == "Scissors" && machineChoice == "Rock" -> textResult.text = "YOU LOSE!!!"
                else -> {
                    textResult.text = "YOU WIN!!!"
                    score += 1
                    textScore.text = score.toString()
                }
            }
        }
    }
}