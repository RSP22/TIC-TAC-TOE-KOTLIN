package com.example.tictactoeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoeproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0
    var boardstatus = Array(3) { IntArray(3) }
    lateinit var board: Array<Array<Button>>

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        board = arrayOf(
            arrayOf(binding.button1, binding.button2, binding.button3),
            arrayOf(binding.button4, binding.button5, binding.button6),
            arrayOf(binding.button7, binding.button8, binding.button9),

            )
        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        intializeBoardStatus() // initialize the board

        binding.resetBtn.setOnClickListener {
            PLAYER = true
            updateDisplay("Player X Turn")
            TURN_COUNT = 0
            intializeBoardStatus()
        }
    }

    private fun intializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardstatus[i][j] = -1
            }
        }
        // setting all the buttons enabled and blank buttons
        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)

            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)

            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)

            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)

            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)

            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)

            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)

            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)

            }

        }
        TURN_COUNT++ //increase the count
        PLAYER = !PLAYER    // change the player turn and change the value btw x and o

        if (PLAYER) {
            updateDisplay("Player X Turn")
        } else {
            updateDisplay("Player O Turn")
        }
        if (TURN_COUNT == 9) {
            updateDisplay("Game Draw")
        }
        checkWinner()

    }

    private fun checkWinner() {
        //horizontal rows winner
        for (i in 0..2) {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2]) {
                if (boardstatus[i][0] == 1) {
                    updateDisplay("PLAYER X IS WINNER")
                    disableButtons()
                    break
                } else if (boardstatus[i][0] == 0) {
                    updateDisplay("PLAYER 0 IS WINNER")
                    disableButtons()
                    break
                }
            }
        }
        // vertical columns winner
        for (j in 0..2) {
            if (boardstatus[0][j] == boardstatus[1][j] && boardstatus[0][j] == boardstatus[2][j]) {
                if (boardstatus[0][j] == 1) {
                    updateDisplay("PLAYER X IS WINNER")
                    disableButtons()
                    break
                } else if (boardstatus[0][j] == 0) {
                    updateDisplay("PLAYER 0 IS WINNER")
                    disableButtons()
                    break
                }
            }
        }
        //first diagonal
        if (boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2]) {
            if (boardstatus[0][0] == 1) {
                updateDisplay("PLAYER X IS WINNER")
                disableButtons()
            } else if (boardstatus[0][0] == 0) {
                updateDisplay("PLAYER 0 IS WINNER")
                disableButtons()
            }

        }
        // second diagonal
        if (boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0]) {
            if (boardstatus[0][2] == 1) {
                updateDisplay("PLAYER X IS WINNER")
                disableButtons()
            } else if (boardstatus[0][2] == 0) {
                updateDisplay("PLAYER 0 IS WINNER")
                disableButtons()
            }

        }

    }

    private fun disableButtons() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }

    private fun updateDisplay(txt: String) {
        binding.displaytv.text = txt // changing the top text to display player turn ..player x turn or player o turn
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardstatus[row][col] = value

    }
}