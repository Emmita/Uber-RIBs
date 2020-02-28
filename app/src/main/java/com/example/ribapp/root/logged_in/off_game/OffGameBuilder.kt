package com.example.ribapp.root.logged_in.off_game

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
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link OffGameScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class OffGameBuilder(dependency: ParentComponent) : ViewBuilder<OffGameView, OffGameRouter, OffGameBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [OffGameRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [OffGameRouter].
   */
  fun build(parentViewGroup: ViewGroup): OffGameRouter {
    val view = createView(parentViewGroup)
    val interactor = OffGameInteractor()
    val component = DaggerOffGameBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.offgameRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): OffGameView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.off_game_rib, parentViewGroup, false) as OffGameView
  }



  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
      @Named("player_one") fun playerOne(): String
      @Named("player_two") fun playerTwo(): String
      fun listener(): OffGameInteractor.Listener

  }

  @dagger.Module
  abstract class Module {

    @OffGameScope
    @Binds
    internal abstract fun presenter(view: OffGameView): OffGameInteractor.OffGamePresenter

    @dagger.Module
    companion object {

      @OffGameScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: OffGameView,
          interactor: OffGameInteractor): OffGameRouter {
        return OffGameRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @OffGameScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<OffGameInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: OffGameInteractor): Builder

      @BindsInstance
      fun view(view: OffGameView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun offgameRouter(): OffGameRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class OffGameScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class OffGameInternal
}
