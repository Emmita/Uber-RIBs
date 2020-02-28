package com.example.ribapp.root.logged_in

import com.example.ribapp.root.RootView
import com.example.ribapp.root.logged_in.off_game.OffGameBuilder
import com.example.ribapp.root.logged_in.off_game.OffGameRouter
import com.example.ribapp.root.logged_in.tic_tac_toe.TicTacToeBuilder
import com.example.ribapp.root.logged_in.tic_tac_toe.TicTacToeRouter
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component,
    private val rootView: RootView,
    private val offGameBuilder: OffGameBuilder,
    private val ticTacToeBuilder: TicTacToeBuilder) : Router<LoggedInInteractor, LoggedInBuilder.Component>(interactor, component) {

    private var loggedInRouter: LoggedInRouter? = null
    private var offGameRouter: OffGameRouter? = null
    private var ticTacToeRouter: TicTacToeRouter? = null


    fun attachOffGame() {

        offGameRouter = offGameBuilder.build(rootView)
        attachChild(offGameRouter)
        rootView.addView(offGameRouter!!.view)

    }

    fun detachOffGame(){

        if (offGameRouter != null){
            detachChild(offGameRouter)
            rootView.removeView(offGameRouter!!.view)
            offGameRouter = null
        }

    }

    fun attachTicTacToe(){

        ticTacToeRouter = ticTacToeBuilder.build(rootView)
        attachChild(ticTacToeRouter)
        rootView.addView(ticTacToeRouter!!.view)

    }

    fun detachTicTacToe(){

        if (ticTacToeRouter != null){
            detachChild(ticTacToeRouter)
            rootView.removeView(ticTacToeRouter!!.view)
            ticTacToeRouter = null
        }

    }

    override fun willDetach() {
        super.willDetach()

        detachOffGame()
        detachTicTacToe()

    }
}
