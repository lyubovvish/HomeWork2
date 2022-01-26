package com.example.lr3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework3.Communicator
import com.example.lr3.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {

    private lateinit var comm: Communicator
    private lateinit var binding: FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        comm = requireActivity() as Communicator

        binding.pls.setOnClickListener{
            comm.passDataCom3(binding.pls.text.toString())
        }
        binding.mns.setOnClickListener{
            comm.passDataCom3(binding.mns.text.toString())
        }
        binding.dvd.setOnClickListener{
            comm.passDataCom3(binding.dvd.text.toString())
        }
        binding.multi.setOnClickListener{
            comm.passDataCom3(binding.multi.text.toString())
        }

        return view
    }
}