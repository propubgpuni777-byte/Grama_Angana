package com.gramaangana.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gramaangana.databinding.FragmentBookingBinding

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentBookingBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSubmit.setOnClickListener {
            val name     = binding.etName.text.toString().trim()
            val purpose  = binding.etPurpose.text.toString().trim()
            val date     = binding.etDate.text.toString().trim()
            val timeSlot = binding.etTimeSlot.text.toString().trim()

            if (name.isEmpty() || purpose.isEmpty() || date.isEmpty() || timeSlot.isEmpty()) {
                binding.tvMessage.text = "⚠️ Please fill in all fields"
                binding.tvMessage.setTextColor(0xFFC62828.toInt())
            } else {
                // TODO: Replace with Firebase write after adding google-services.json
                binding.tvMessage.text = "✅ Request noted locally! Add Firebase to submit to Panchayat."
                binding.tvMessage.setTextColor(0xFF2E7D32.toInt())
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
