package io.github.ihdatech.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    // private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.empty.root.visibility = View.VISIBLE
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.visibility = View.GONE
        binding.swipe.isRefreshing = true
        // homeViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        homeViewModel.list.observe(viewLifecycleOwner, { listResult ->
            listResult.map {
                binding.swipe.isRefreshing = false
                if (it.data != null) {
                    binding.empty.root.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE
                    binding.list.adapter = HomeAdapter(requireContext(), it.data)
                    binding.swipe.setOnRefreshListener {
                        binding.swipe.isRefreshing = false
                        binding.list.adapter = HomeAdapter(requireContext(), it.data)
                    }
                } else {
                    binding.empty.root.visibility = View.VISIBLE
                    binding.list.visibility = View.GONE
                }
            }
        })
        homeViewModel.listFake.observe(viewLifecycleOwner, { listResult ->
            listResult.map {
                println("[TATA FUCKING IN FRAGMENT]: $it")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}