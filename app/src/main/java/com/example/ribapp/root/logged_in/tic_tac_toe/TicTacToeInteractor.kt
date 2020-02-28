package com.example.ribapp.root.logged_in.tic_tac_toe

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [TicTacToeScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class TicTacToeInteractor : Interactor<TicTacToeInteractor.TicTacToePresenter, TicTacToeRouter>() {

  @Inject
  lateinit var presenter: TicTacToePresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface TicTacToePresenter
}
