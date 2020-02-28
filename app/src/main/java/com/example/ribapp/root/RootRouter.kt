package com.example.ribapp.root

import com.example.ribapp.root.logged_in.LoggedInBuilder
import com.example.ribapp.root.logged_out.LoggedOutBuilder
import com.example.ribapp.root.logged_out.LoggedOutRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter constructor (
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loggedOutBuilder: LoggedOutBuilder,
    private val loggedInBuilder: LoggedInBuilder) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component){

    private var loggedOutRouter: LoggedOutRouter? = null

    fun attachLoggedOut() {
        loggedOutRouter = loggedOutBuilder.build(view)
        attachChild(loggedOutRouter)
        view.addView(loggedOutRouter!!.view)
    }

    fun detachLoggedOut(){

        if(loggedOutRouter != null){
            detachChild(loggedOutRouter)
            view.removeView(loggedOutRouter!!.view)
            loggedOutRouter = null
        }

    }

    fun attachLoggedIn(playerOne: String, playerTwo: String){

        attachChild(loggedInBuilder.build(playerOne, playerTwo))

    }

}
