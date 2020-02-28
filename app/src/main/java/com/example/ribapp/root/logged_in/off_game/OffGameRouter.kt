package com.example.ribapp.root.logged_in.off_game

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link OffGameBuilder.OffGameScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class OffGameRouter(
    view: OffGameView,
    interactor: OffGameInteractor,
    component: OffGameBuilder.Component) : ViewRouter<OffGameView, OffGameInteractor, OffGameBuilder.Component>(view, interactor, component){


}
