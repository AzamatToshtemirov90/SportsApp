package com.azamat.sportsapp.ui.fragment.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.azamat.sportsapp.R
import com.azamat.sportsapp.base.BaseFragment
import com.azamat.sportsapp.databinding.FragmentPlayerDetailsBinding
import com.squareup.picasso.Picasso


class PlayerDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentPlayerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDidLoad(
            imageUrl = arguments?.getString("imageUrl", "") ?: "",
            name = arguments?.getString("name", "") ?: "",
            nationality = arguments?.getString("nationality", "") ?: "",
            desc = arguments?.getString("desc", "") ?: ""
        )
    }

    private fun viewDidLoad(imageUrl: String, name: String, nationality: String, desc: String) {
        Picasso.get().load(imageUrl).into(binding.imgPlayer)
        binding.tvName.text = name
        binding.tvNationality.text = nationality
        binding.tvDesc.text = desc
        binding.ibBack.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popTo(R.id.playerListFragment)
    }


}