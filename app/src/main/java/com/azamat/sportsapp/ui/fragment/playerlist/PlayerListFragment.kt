package com.azamat.sportsapp.ui.fragment.playerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.azamat.sportsapp.R
import com.azamat.sportsapp.base.BaseFragment
import com.azamat.sportsapp.databinding.FragmentListPlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerListFragment : BaseFragment() {
    private lateinit var binding: FragmentListPlayerBinding
    private val viewModel: PlayerListViewModel by viewModel()
    lateinit var adapter: PlayerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPlayerBinding.inflate(inflater, container, false)
        binding.ivSearch.setOnClickListener {
            getPlayerDetails()
        }
        binding.etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                getPlayerDetails()
            }
            return@setOnEditorActionListener true
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewDidLoad()
    }

    private fun viewDidLoad() {
        initRecyclerView()
        getPlayerDetails()
        observeData()
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.tvErrorNotification.visibility = View.VISIBLE

            it?.let {
                if (it.contains("2147483647")) {
                    Toast.makeText(
                        requireContext(),
                        "NO INTERNET CONNECTION",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.tvErrorNotification.text = it

                }
            }
        }
    }

    fun getPlayerDetails() {
        val searchText = binding.etSearch.text.toString()
        if (searchText.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a valid name", Toast.LENGTH_LONG)
                .show()
        } else {
            fetchPlayerList(searchText)
        }
    }

    private fun initRecyclerView() {
        adapter = PlayerListAdapter(PlayerListAdapter.OnItemClickListener { player ->
            val bundle = bundleOf(
                "imageUrl" to player.strThumb,
                "name" to player.strPlayer,
                "nationality" to player.strNationality,
                "desc" to player.strDescriptionEN
            )
            navigate(R.id.playerDetailsFragment, bundle)
        })
        binding.rvHotels.adapter = adapter
        binding.rvHotels.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchPlayerList(query: String) {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPlayerDetails(query)

        viewModel.playerDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.items = it.player
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE

            }
        }

    }
}