package io.github.ihdatech.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.MyApplication
import io.github.ihdatech.myapplication.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject lateinit var app: MyApplication
    private lateinit var homeViewModel: HomeViewModel
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
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        homeViewModel.homeList.observe(viewLifecycleOwner, {
            binding.swipe.isRefreshing = false
            if (it != null) {
                binding.empty.root.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
                binding.list.adapter = HomeAdapter(it)
                binding.swipe.setOnRefreshListener {
                    binding.swipe.isRefreshing = false
                    binding.list.adapter = HomeAdapter(it)
                }
            } else {
                binding.empty.root.visibility = View.VISIBLE
                binding.list.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}