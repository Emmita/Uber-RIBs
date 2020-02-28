package com.example.ribapp.root.logged_in.tic_tac_toe

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TicTacToeInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: TicTacToeInteractor.TicTacToePresenter
  @Mock internal lateinit var router: TicTacToeRouter

  private var interactor: TicTacToeInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestTicTacToeInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<TicTacToeInteractor.TicTacToePresenter, TicTacToeRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}