package com.example.picwrite.ui.album

import AlbumViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picwrite.databinding.FragmentAlbumBinding
import com.example.picwrite.ui.calendar.CalendarAdapter

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // GridLayoutManager 설정
        val layoutManager = GridLayoutManager(requireContext(), 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }

        binding.albumRecyclerView.layoutManager = layoutManager

        // ViewModel의 데이터를 관찰하여 RecyclerView에 연결
        viewModel.items.observe(viewLifecycleOwner, Observer { items ->
            binding.albumRecyclerView.adapter = CalendarAdapter(items)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}