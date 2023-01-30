package com.example.vemprocasorio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vemprocasorio.databinding.FragmentAllGuestsBinding

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    private val binding get() = _binding!!

    //=======================  Inicia Criação da view  =========================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //=======================  Termina Criação da view  =========================

        //Instancia variaveis para serem observadas
        val textView: TextView = binding.textHome
        val textView2: TextView = binding.textHome2


        //Observação
        ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        ViewModel.text2.observe(viewLifecycleOwner) {
            textView2.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}