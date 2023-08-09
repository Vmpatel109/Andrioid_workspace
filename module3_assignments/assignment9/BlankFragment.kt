package com.example.module3assignment9

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.module3assignment9.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inflate the layout for this fragment

         inflater.inflate(R.layout.fragment_blank, container, false)

        binding.f1.setOnClickListener {
            var i=Intent(requireActivity(),MainActivity2::class.java)
            startActivity(i)
        }

        return view
    }



}