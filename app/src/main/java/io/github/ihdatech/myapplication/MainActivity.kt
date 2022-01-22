package io.github.ihdatech.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.databinding.ActivityMainBinding
import io.github.ihdatech.myapplication.ui.bottom.navigation.NavigationFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationHost {

    @Inject lateinit var app: MyApplication
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment_activity_main, NavigationFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commitAllowingStateLoss()
        }

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        if (fragment.tag == null) {
            fragment.isRemoving
        }
    }
}