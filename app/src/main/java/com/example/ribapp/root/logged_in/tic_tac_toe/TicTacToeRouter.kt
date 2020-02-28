package com.example.ribapp.root.logged_in.tic_tac_toe

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link TicTacToeBuilder.TicTacToeScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class TicTacToeRouter(
    view: TicTacToeView,
    interactor: TicTacToeInteractor,
    component: TicTacToeBuilder.Component) : ViewRouter<TicTacToeView, TicTacToeInteractor, TicTacToeBuilder.Component>(view, interactor, component)
