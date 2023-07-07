package com.example.dreamcraft.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dreamcraft.R
import com.example.dreamcraft.databinding.FragmentClientDetailsBinding
import com.example.dreamcraft.utils.Constants.FILL_DETAILS
import com.example.dreamcraft.utils.Constants.WHATSAPP_LINK
import com.example.dreamcraft.utils.isValidEmail
import com.example.dreamcraft.utils.isValidName
import com.example.dreamcraft.utils.validation
import com.google.firebase.database.DatabaseReference


class ClientDetailsFragment : Fragment() {
    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendWhatsappMessage()
    }

    private fun sendWhatsappMessage() {
        var nameFlag = false
        var emailFlag = false
        var siteAddressFlag = false
        var addressFlag = false
        var messageFlag = false
        binding.edtName.validation { name ->
            binding.tilName.helperText =
                if (isValidName(name, requireContext()).toString() == "null") {
                    nameFlag = true
                    ""
                } else {
                    nameFlag = false
                    isValidName(name, requireContext()).toString()
                }
        }
        binding.edtEmail.validation { email ->
            binding.tilEmail.helperText =
                if (isValidEmail(email, requireContext()).toString() == "null") {
                    emailFlag = true
                    ""
                } else {
                    emailFlag = false
                    isValidEmail(email, requireContext()).toString()
                }
        }
        binding.edtAddress.validation { name ->
            binding.tilAddress.helperText =
                if (isValidName(name, requireContext()).toString() == "null") {
                    addressFlag = true
                    ""
                } else {
                    addressFlag = false
                    isValidName(name, requireContext()).toString()
                }
        }
        binding.edtSiteAddress.validation { name ->
            binding.tilAddress.helperText =
                if (isValidName(name, requireContext()).toString() == "null") {
                    siteAddressFlag = true
                    ""
                } else {
                    siteAddressFlag = false
                    isValidName(name, requireContext()).toString()
                }
        }
        binding.edtMessage.validation { name ->
            binding.tilAddress.helperText =
                if (isValidName(name, requireContext()).toString() == "null") {
                    messageFlag = true
                    ""
                } else {
                    messageFlag = false
                    isValidName(name, requireContext()).toString()
                }
        }
        binding.contactMe.setOnClickListener {
            if (nameFlag && emailFlag && siteAddressFlag && addressFlag && messageFlag) {

                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "$WHATSAPP_LINK${
                            getString(
                                R.string.whatsappMessage,
                                binding.edtName.text.toString(),
                                binding.edtName.text.toString(),
                                binding.edtEmail.text.toString(),
                                binding.edtMobileNumber.text.toString()
                            )
                        }"
                    )
                ).apply {
                    startActivity(this)
                }
            } else {
                Toast.makeText(requireContext(), FILL_DETAILS, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}