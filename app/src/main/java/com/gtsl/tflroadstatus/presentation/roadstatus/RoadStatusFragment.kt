package com.gtsl.tflroadstatus.presentation.roadstatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gtsl.tflroadstatus.databinding.FragmentRoadStatusBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RoadStatusFragment : Fragment() {

    private val viewModel: RoadStatusViewModel by sharedViewModel()
    private lateinit var adapter: RoadStatusAdapter
    lateinit var binding: FragmentRoadStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = RoadStatusAdapter()
        binding = FragmentRoadStatusBinding.inflate(inflater, container, false)
        binding.roadStatusListRecyclerView.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.roadStatusLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }
}