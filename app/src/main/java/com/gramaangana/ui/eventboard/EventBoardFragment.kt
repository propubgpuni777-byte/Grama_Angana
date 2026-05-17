package com.gramaangana.ui.eventboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramaangana.databinding.FragmentEventBoardBinding
import com.gramaangana.model.BookingRequest
import java.text.SimpleDateFormat
import java.util.*

class EventBoardFragment : Fragment() {

    private var _binding: FragmentEventBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEventBoardBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        binding.tvTodayDate.text = "📅 Today: $today"

        // Sample data — replace with Firebase listener after adding google-services.json
        val sampleEvents = listOf(
            BookingRequest(id = "1", requesterName = "Ravi Kumar",  purpose = "Health Camp",    date = today, timeSlot = "09:00-12:00", status = "approved"),
            BookingRequest(id = "2", requesterName = "Meena Devi",  purpose = "Yoga Session",   date = today, timeSlot = "06:00-07:30", status = "approved"),
            BookingRequest(id = "3", requesterName = "Youth Club",  purpose = "Cricket Practice",date = today, timeSlot = "16:00-18:00", status = "approved")
        )

        val adapter = EventAdapter()
        binding.rvEvents.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvents.adapter = adapter
        adapter.submitList(sampleEvents)
        binding.tvNoEvents.visibility = View.GONE
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
