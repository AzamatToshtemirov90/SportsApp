package com.azamat.sportsapp.ui.activity

import android.os.Bundle
import com.azamat.sportsapp.R
import com.azamat.sportsapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeNavController(R.id.nav_host_fragment)
    }
}