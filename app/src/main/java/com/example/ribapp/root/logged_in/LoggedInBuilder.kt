package com.example.ribapp.root.logged_in

import com.example.ribapp.root.RootView
import com.example.ribapp.root.logged_in.off_game.OffGameBuilder
import com.example.ribapp.root.logged_in.off_game.OffGameInteractor
import com.example.ribapp.root.logged_in.tic_tac_toe.TicTacToeBuilder
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance
import dagger.Component

import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Named

class LoggedInBuilder(dependency: ParentComponent) : Builder<LoggedInRouter, LoggedInBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [LoggedInRouter].
   *
   * @return a new [LoggedInRouter].
   */
  fun build(playerOne: String, playerTwo: String): LoggedInRouter {
    val interactor = LoggedInInteractor()
    val component = DaggerLoggedInBuilder_Component.builder()
        .parentComponent(dependency)
            .playerOne(playerOne)
            .playerTwo(playerTwo)
        .interactor(interactor)
        .build()

    return component.loggedinRouter()
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.

    fun rootView(): RootView

  }


  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @LoggedInScope
      @Provides
      @JvmStatic
      internal fun presenter(): EmptyPresenter {
        return EmptyPresenter()
      }

      @LoggedInScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, interactor: LoggedInInteractor, rootView: RootView): LoggedInRouter {
        return LoggedInRouter(interactor, component, rootView, (OffGameBuilder(component)), (TicTacToeBuilder(component)))
      }

      // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.

      @LoggedInScope
      @Provides
      fun offGameListener(loggedInInteractor: LoggedInInteractor): OffGameInteractor.Listener{
        return loggedInInteractor.OffGameListener()
      }

    }

  }


  @LoggedInScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<LoggedInInteractor>, BuilderComponent, OffGameBuilder.ParentComponent, TicTacToeBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LoggedInInteractor): Builder

      @BindsInstance
      fun playerOne(@Named("player_one") playerOne: String): Builder

      @BindsInstance
      fun playerTwo(@Named("player_two")playerTwo: String): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }

  }

  interface BuilderComponent {
    fun loggedinRouter(): LoggedInRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LoggedInScope


  @Qualifier
  @Retention(CLASS)
  internal annotation class LoggedInInternal
}
