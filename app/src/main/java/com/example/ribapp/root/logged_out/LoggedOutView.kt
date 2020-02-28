package com.example.ribapp.root.logged_out

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.logged_out_rib.view.*

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoggedOutView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), LoggedOutInteractor.LoggedOutPresenter {


    override fun playerNames(): Observable<Pair<String, String>> {

        return RxView.clicks(login_button)
                .map{
                    return@map Pair(player_one_text.text.toString(), player_two_name.text.toString())
                }

    }


    // override fun loginName(): Observable<String> {

  //     return RxView.clicks(login_button).map {
  //         val textView: TextView = edit_text as TextView
  //         return@map textView.text.toString()
  //     }

  // }


}