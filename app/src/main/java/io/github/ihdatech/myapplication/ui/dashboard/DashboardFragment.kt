package io.github.ihdatech.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.databinding.FragmentDashboardBinding

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    // private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emptyDashboard.root.visibility = View.VISIBLE
        binding.listDashboard.layoutManager = LinearLayoutManager(context)
        binding.listDashboard.visibility = View.GONE
        binding.swipeDashboard.isRefreshing = true
        // println("[TATA FUCKING IN FRAGMENT]: ${newsService.everything()}")
        // dashboardViewModel = ViewModelProvider(this, DashboardViewModelFactory())[DashboardViewModel::class.java]
        dashboardViewModel.articles.observe(viewLifecycleOwner, { listResult ->
            listResult.map {
                binding.swipeDashboard.isRefreshing = false
                if (it.articles != null) {
                    binding.emptyDashboard.root.visibility = View.GONE
                    binding.listDashboard.visibility = View.VISIBLE
                    binding.listDashboard.adapter = DashboardAdapter(it.articles)
                    binding.swipeDashboard.setOnRefreshListener {
                        binding.swipeDashboard.isRefreshing = false
                        binding.listDashboard.adapter = DashboardAdapter(it.articles)
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
}