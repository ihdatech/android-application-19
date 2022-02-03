package io.github.ihdatech.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.databinding.FragmentDashboardBinding
import android.annotation.SuppressLint
import android.provider.Settings
import android.os.Build

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var columnCount = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emptyDashboard.root.visibility = View.VISIBLE
        binding.listDashboard.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        binding.listDashboard.visibility = View.GONE
        binding.swipeDashboard.isRefreshing = true
        println("[TATA FUCKING IN FRAGMENT]: ${getAndroidId()}")
        println("[TATA FUCKING IN FRAGMENT]: ${Build.ID}")
        // dashboardViewModel = ViewModelProvider(this, DashboardViewModelFactory())[DashboardViewModel::class.java]
        dashboardViewModel.list.observe(viewLifecycleOwner, { listResult ->
            listResult.map {
                binding.swipeDashboard.isRefreshing = false
                if (it.isNotEmpty()) {
                    binding.emptyDashboard.root.visibility = View.GONE
                    binding.listDashboard.visibility = View.VISIBLE
                    binding.listDashboard.adapter = DashboardAdapter(requireContext(), it)
                    binding.swipeDashboard.setOnRefreshListener {
                        binding.swipeDashboard.isRefreshing = false
                        binding.listDashboard.adapter = DashboardAdapter(requireContext(), it)
                    }
                } else {
                    binding.emptyDashboard.root.visibility = View.VISIBLE
                    binding.listDashboard.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Returns the Android hardware device ID that is unique between the device + user and app
     * signing. This key will change if the app is uninstalled or its data is cleared. Device factory
     * reset will also result in a value change.
     *
     * @return The android ID
     */
    @SuppressLint("HardwareIds")
    private fun getAndroidId(): String? {
        return Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
    }

    companion object {}
}