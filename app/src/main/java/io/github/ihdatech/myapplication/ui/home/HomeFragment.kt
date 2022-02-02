package io.github.ihdatech.myapplication.ui.home

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.ihdatech.myapplication.databinding.FragmentHomeBinding
import java.io.File

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

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
                binding.empty.root.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
                binding.list.adapter = HomeAdapter(it)
                binding.swipe.setOnRefreshListener {
                    binding.swipe.isRefreshing = false
                    binding.list.adapter = HomeAdapter(it)
                }
            }
        })
        // homeViewModel.text.observe(viewLifecycleOwner, {
        //     println("[TATA FUCKING IN FRAGMENT]: $it")
        // })
        requireContext().assets.open("products.json").bufferedReader().readLine().apply {
            println("[TATA FUCKING IN FRAGMENT]: $this")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}