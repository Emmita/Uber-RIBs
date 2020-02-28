package com.example.ribapp.root.logged_in.tic_tac_toe

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Top level view for {@link TicTacToeBuilder.TicTacToeScope}.
 */
class TicTacToeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), TicTacToeInteractor.TicTacToePresenter
