package com.example.ribapp.root.logged_out

import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    // TODO: Add attachment logic here (RxSubscriptions, etc.).

 //  presenter.loginName().subscribe(Consumer {name ->

 //    if (!name.isEmpty()) listener.login(name)

 //  })

    presenter.playerNames().subscribe(Consumer {(playerOne, playerTwo) ->

      if(!playerOne.isEmpty() && !playerTwo.isEmpty()){
        listener.requestLogin(playerOne, playerTwo)
      }

    })

  }


  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedOutPresenter{

    fun playerNames(): Observable<Pair<String, String>>

  }

  interface Listener{

    fun requestLogin(playerOne: String, playerTwo: String)

  }

}
