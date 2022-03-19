package com.rba.pkce.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rba.pkce.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.apiError.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { model ->
                binding.textResult.text = model.toString()
            }
        })

        mainViewModel.error.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { message ->
                binding.textResult.text = message
            }
        })

        mainViewModel.data.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                binding.textResult.text = it.toString()
            }
        })

        mainViewModel.dataTransaction.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                binding.textResult.text = it.toString()
            }
        })

        mainViewModel.loading.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        binding.buttonGet.setOnClickListener {
            mainViewModel.get()
        }

        binding.buttonPost.setOnClickListener {
            mainViewModel.pay(clientId = "100", amount = "100")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}