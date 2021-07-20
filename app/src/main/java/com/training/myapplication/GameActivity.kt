package com.training.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private var turnName: ArrayList<String> = ArrayList()
    private var turnIndex = 1
    private var board = Array(3) {Array(3) {"N"} }
    private var score = HashMap<String, Int>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        turnName.add(intent.getStringExtra("P1")?: "Player 1")
        turnName.add(intent.getStringExtra("P2")?: "Player 2")
        score.put(turnName[0], 0)
        score.put(turnName[1], 0)
        topLeft.setOnClickListener { this.getText(topLeft, 0, 0) }
        topMid.setOnClickListener { this.getText(topMid, 0, 1) }
        topRight.setOnClickListener { this.getText(topRight, 0, 2) }
        midLeft.setOnClickListener { this.getText(midLeft, 1, 0) }
        midMid.setOnClickListener { this.getText(midMid, 1, 1) }
        midRight.setOnClickListener { this.getText(midRight, 1, 2) }
        botLeft.setOnClickListener { this.getText(botLeft, 2, 0) }
        botMid.setOnClickListener { this.getText(botMid, 2, 1) }
        botRight.setOnClickListener { this.getText(botRight, 2, 2) }

        textView.text = "${turnName[0]} turn"
    }

    fun getText(button: TextView, x: Int, y: Int): String {
        println(x.toString() + y.toString())
        val text = if (turnIndex == 1) "X" else "O"
        textView.text = "${turnName[turnIndex]} turn"
        this.board[x][y] = text
        this.changeTurn(button)
        button.text = text
        if (this.isScored(button)) {
            println("RESET")}
        return text
    }

    fun isScored(button: TextView): Boolean {
        for (i in 0..2){
            var score = HashMap<String, Int>()
            for (j in 0..2){
                if (this.board[i][j] == button.text){
                    score.put(button.text.toString(), (score.get(button.text)?:0) + 1)
                }
                if (this.checkWinners(score)) {return true}
            }
        }

        for (i in 0..2){
            var score = HashMap<String, Int>()
            for (j in 0..2){
                if (this.board[j][i] == button.text){
                    score.put(button.text.toString(), (score.get(button.text)?:0) + 1)
                }

                if (this.checkWinners(score)) {return true}
            }
        }
        return false
    }

    fun checkWinners(score: HashMap<String, Int>): Boolean {
        if (score.get("X") == 3) {
            println("X WINS")
            score.put("P1", (score.get("P1")?:0) + 1)
            return true
        }

        if (score.get("O") == 3) {
            println("O WINS")
            score.put("P2", (score.get("P2")?:0) + 1)
            return true
        }
        return false
    }

    fun resetBoard() {
        this.board = Array(3) {Array(3) {""} }
    }

    fun changeTurn(button: View) {
        button.isEnabled = false;
        this.turnIndex = 1 - this.turnIndex
    }

}