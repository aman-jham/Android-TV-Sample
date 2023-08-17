package com.android.tv.sample

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.tv.sample.databinding.ActivityMainBinding
import com.android.tv.sample.fragment.GenresFragment
import com.android.tv.sample.fragment.HomeFragment
import com.android.tv.sample.fragment.LanguageFragment
import com.android.tv.sample.fragment.MovieFragment
import com.android.tv.sample.fragment.SearchFragment
import com.android.tv.sample.fragment.SettingsFragment
import com.android.tv.sample.fragment.SportsFragment
import com.android.tv.sample.fragment.TvShowFragment
import com.android.tv.sample.utils.Common
import com.tutorial.tvapp.utils.Constants

class MainActivity : FragmentActivity(), View.OnKeyListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private var SIDE_MENU = false
    private var selectedMenu = Constants.MENU_HOME
    private lateinit var lastSelectedMenu: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)

    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER -> {

                lastSelectedMenu.isActivated = false
                view?.isActivated = true
                lastSelectedMenu = view!!

                when (view.id) {
                    R.id.btn_search -> {
                        selectedMenu = Constants.MENU_SEARCH
                        changeFragment(SearchFragment())
                    }

                    R.id.btn_home -> {
                        selectedMenu = Constants.MENU_HOME
                        changeFragment(HomeFragment())
                    }

                    R.id.btn_tv -> {
                        selectedMenu = Constants.MENU_TV
                        changeFragment(TvShowFragment())
                    }

                    R.id.btn_movies -> {
                        selectedMenu = Constants.MENU_MOVIE
                        changeFragment(MovieFragment())
                    }

                    R.id.btn_sports -> {
                        selectedMenu = Constants.MENU_SPORTS
                        changeFragment(SportsFragment())
                    }

                    R.id.btn_settings -> {
                        selectedMenu = Constants.MENU_SETTINGS
                        changeFragment(SettingsFragment())
                    }

                    R.id.btn_language -> {
                        selectedMenu = Constants.MENU_LANGUAGE
                        changeFragment(LanguageFragment())
                    }

                    R.id.btn_genre -> {
                        selectedMenu = Constants.MENU_GENRES
                        changeFragment(GenresFragment())
                    }
                }

            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (!SIDE_MENU) {
                    switchToLastSelectedMenu()

                    openMenu()
                    SIDE_MENU = true
                }
            }
        }
        return false
    }


    override fun onBackPressed() {
        if (SIDE_MENU) {
            SIDE_MENU = false
            closeMenu()
        } else {
            super.onBackPressed()
        }
    }

    fun switchToLastSelectedMenu() {
        when (selectedMenu) {
            Constants.MENU_SEARCH -> {
                activityMainBinding.btnSearch.requestFocus()
            }
            Constants.MENU_HOME -> {
                activityMainBinding.btnHome.requestFocus()
            }
            Constants.MENU_TV -> {
                activityMainBinding.btnTv.requestFocus()
            }
            Constants.MENU_MOVIE -> {
                activityMainBinding.btnMovies.requestFocus()
            }
            Constants.MENU_SPORTS -> {
                activityMainBinding.btnSports.requestFocus()
            }
            Constants.MENU_LANGUAGE -> {
                activityMainBinding.btnLanguage.requestFocus()
            }
            Constants.MENU_GENRES -> {
                activityMainBinding.btnGenre.requestFocus()
            }
            Constants.MENU_SETTINGS -> {
                activityMainBinding.btnSettings.requestFocus()
            }
        }
    }

    private fun openMenu() {
        val animSlide: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        activityMainBinding.blfNavBar.startAnimation(animSlide)

        activityMainBinding.blfNavBar.requestLayout()
        activityMainBinding.blfNavBar.layoutParams.width = Common.getWidthInPercent(this, 16)
    }

    private fun closeMenu() {
        activityMainBinding.blfNavBar.requestLayout()
        activityMainBinding.blfNavBar.layoutParams.width = Common.getWidthInPercent(this, 5)

        activityMainBinding.container.requestFocus()
        SIDE_MENU = false
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
        closeMenu()
    }
}