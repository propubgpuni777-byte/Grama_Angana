package com.gramaangana.ui.maintenance

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramaangana.data.AppDatabase
import com.gramaangana.data.MaintenanceItem
import com.gramaangana.databinding.FragmentMaintenanceBinding
import kotlinx.coroutines.launch

class MaintenanceFragment : Fragment() {

    private var _binding: FragmentMaintenanceBinding? = null
    private val binding get() = _binding!!
    private val dao by lazy { AppDatabase.getInstance(requireContext()).maintenanceDao() }
    private lateinit var adapter: MaintenanceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentMaintenanceBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MaintenanceAdapter { item ->
            viewLifecycleOwner.lifecycleScope.launch { dao.pledge(item.id, 50) }
        }
        binding.rvMaintenance.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMaintenance.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            dao.getAll().collect { adapter.submitList(it) }
        }

        binding.btnAddItem.setOnClickListener { showAddDialog() }
    }

    private fun showAddDialog() {
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 20, 60, 0)
        }
        val etName = EditText(requireContext()).apply { hint = "Item name (e.g. Fan, Bulbs)" }
        val etTarget = EditText(requireContext()).apply {
            hint = "Target amount (₹)"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        layout.addView(etName)
        layout.addView(etTarget)

        AlertDialog.Builder(requireContext())
            .setTitle("Add Maintenance Item")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val name   = etName.text.toString().trim()
                val target = etTarget.text.toString().toIntOrNull() ?: 0
                if (name.isNotEmpty() && target > 0) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        dao.insert(MaintenanceItem(itemName = name, targetAmount = target))
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
