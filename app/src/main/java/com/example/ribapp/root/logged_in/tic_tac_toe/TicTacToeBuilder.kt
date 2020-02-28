package com.example.ribapp.root.logged_in.tic_tac_toe

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ribapp.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link TicTacToeScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class TicTacToeBuilder(dependency: ParentComponent) : ViewBuilder<TicTacToeView, TicTacToeRouter, TicTacToeBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [TicTacToeRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [TicTacToeRouter].
   */
  fun build(parentViewGroup: ViewGroup): TicTacToeRouter {
    val view = createView(parentViewGroup)
    val interactor = TicTacToeInteractor()
    val component = DaggerTicTacToeBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.tictactoeRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): TicTacToeView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.tic_tac_toe_rib, parentViewGroup, false) as TicTacToeView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @TicTacToeScope
    @Binds
    internal abstract fun presenter(view: TicTacToeView): TicTacToeInteractor.TicTacToePresenter

    @dagger.Module
    companion object {

      @TicTacToeScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: TicTacToeView,
          interactor: TicTacToeInteractor): TicTacToeRouter {
        return TicTacToeRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @TicTacToeScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<TicTacToeInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: TicTacToeInteractor): Builder

      @BindsInstance
      fun view(view: TicTacToeView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun tictactoeRouter(): TicTacToeRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class TicTacToeScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class TicTacToeInternal
}
