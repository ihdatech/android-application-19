package io.github.ihdatech.myapplication.ui.bottom.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.MyApplication
import io.github.ihdatech.myapplication.NavigationHost
import io.github.ihdatech.myapplication.R
import io.github.ihdatech.myapplication.databinding.FragmentNavigationBinding
import io.github.ihdatech.myapplication.ui.dashboard.DashboardFragment
import io.github.ihdatech.myapplication.ui.home.HomeFragment
import io.github.ihdatech.myapplication.ui.notifications.NotificationsFragment
import javax.inject.Inject

@AndroidEntryPoint
class NavigationFragment : Fragment(), NavigationHost {

    @Inject lateinit var app: MyApplication

    private lateinit var navigationViewModel: NavigationViewModel
    private var _binding: FragmentNavigationBinding? = null
    private var _onNavigationIndex = 0
    private var _onNavigationItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (_onNavigationIndex == 0) return@OnItemSelectedListener false
                _onNavigationIndex = 0
                navigateTo(HomeFragment(), true)
                return@OnItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (_onNavigationIndex == 1) return@OnItemSelectedListener false
                _onNavigationIndex = 1
                navigateTo(DashboardFragment(), true)
                return@OnItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (_onNavigationIndex == 2) return@OnItemSelectedListener false
                _onNavigationIndex = 2
                navigateTo(NotificationsFragment(), true)
                return@OnItemSelectedListener true
            }
        }
        false
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        navigationViewModel = ViewModelProvider(this)[NavigationViewModel::class.java]

        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navView: BottomNavigationView = binding.navView
        navView.setOnItemSelectedListener(_onNavigationItemSelectedListener)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Trigger a navigation to the specified fragment, optionally adding a transaction to the back
     * stack to make this navigation reversible.
     */
    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = childFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_container_bottom_navigation, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commitAllowingStateLoss()
    }
}