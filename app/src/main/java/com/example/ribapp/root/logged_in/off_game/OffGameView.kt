package com.example.ribapp.root.logged_in.off_game

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.off_game_rib.view.*

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), OffGameInteractor.OffGamePresenter{



    override fun startGameRequest(): Observable<Any> {

        return RxView.clicks(button_off_game)


    }

    override fun setPlayerNames(playerOne: String, playerTwo: String) {

        tv_player_one.text = playerOne
        tv_player_two.text = playerTwo

    }


}
