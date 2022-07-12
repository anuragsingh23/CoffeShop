package com.example.coffeeshop.ui.historyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.databinding.FragmentHistoryBinding
import com.example.coffeeshop.domain.model.Data
import com.example.coffeeshop.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding : FragmentHistoryBinding get()= _binding!!

    private val viewModel : HistoryViewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        (activity as AppCompatActivity).supportActionBar?.title = "History"

        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryAdapter(Data.list)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = adapter

    }


    @Deprecated("Avoid for now")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        _binding = null
        super.onDestroy()
    }
}