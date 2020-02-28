package com.example.ribapp.root.logged_in.tic_tac_toe

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TicTacToeRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: TicTacToeBuilder.Component
  @Mock internal lateinit var interactor: TicTacToeInteractor
  @Mock internal lateinit var view: TicTacToeView

  private var router: TicTacToeRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = TicTacToeRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

