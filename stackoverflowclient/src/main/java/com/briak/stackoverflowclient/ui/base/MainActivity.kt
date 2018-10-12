package com.briak.stackoverflowclient.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.StackOverflowClientApplication
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject
import ru.terrakok.cicerone.commands.Replace


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private var navigator = object : SupportAppNavigator(this, R.id.mainContainer) {
        override fun applyCommands(commands: Array<out Command>?) {
            super.applyCommands(commands)

            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        StackOverflowClientApplication.component.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.TagsListScreen)))
        }
    }

    override fun onResume() {
        super.onResume()

        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()

        super.onPause()
    }
}
