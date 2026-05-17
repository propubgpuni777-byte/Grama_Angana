package com.gramaangana.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gramaangana.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentCalendarBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvBookingStatus.text = "Select a date to check availability"
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            val date = "%04d-%02d-%02d".format(year, month + 1, day)
            binding.tvSelectedDate.text = "📅 $date"
            // TODO: Replace with Firebase check after adding google-services.json
            binding.tvBookingStatus.text = "✅  AVAILABLE — Connect Firebase to see live status"
            binding.tvBookingStatus.setBackgroundColor(0xFF388E3C.toInt())
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
