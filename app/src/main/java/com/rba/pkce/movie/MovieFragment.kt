package com.rba.pkce.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rba.pkce.databinding.FragmentFirstBinding

class MovieFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by lazy {
        ViewModelProvider(this, MovieViewModelFactory()).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.apiError.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { model ->
                binding.textResult.text = model.toString()
            }
        })

        movieViewModel.error.observe(viewLifecycleOwner, { error ->
            error.getContentIfNotHandled()?.let { message ->
                binding.textResult.text = message
            }
        })

        movieViewModel.data.observe(viewLifecycleOwner, { data ->
            data.getContentIfNotHandled()?.let {
                binding.textResult.text = it.toString()
            }
        })

        binding.buttonGet.setOnClickListener {
            movieViewModel.get()
        }

        binding.buttonPost.setOnClickListener {
            movieViewModel.pay(clientId = "100", amount = "100")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}