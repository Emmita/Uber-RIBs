package com.example.ribapp.root.logged_out

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
 * Builder for the {@link LoggedOutScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class LoggedOutBuilder(dependency: ParentComponent) : ViewBuilder<LoggedOutView, LoggedOutRouter, LoggedOutBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [LoggedOutRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [LoggedOutRouter].
   */
  fun build(parentViewGroup: ViewGroup): LoggedOutRouter {
    val view = createView(parentViewGroup)
    val interactor = LoggedOutInteractor()
    val component = DaggerLoggedOutBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.loggedoutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoggedOutView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return inflater.inflate(R.layout.logged_out_rib, parentViewGroup, false) as LoggedOutView
  }



  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.

    fun listener(): LoggedOutInteractor.Listener

  }

  @dagger.Module
  abstract class Module {

    @LoggedOutScope
    @Binds
    internal abstract fun presenter(view: LoggedOutView): LoggedOutInteractor.LoggedOutPresenter

    @dagger.Module
    companion object {

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: LoggedOutView,
          interactor: LoggedOutInteractor): LoggedOutRouter {
        return LoggedOutRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @LoggedOutScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<LoggedOutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LoggedOutInteractor): Builder

      @BindsInstance
      fun view(view: LoggedOutView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun loggedoutRouter(): LoggedOutRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LoggedOutScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LoggedOutInternal
}
