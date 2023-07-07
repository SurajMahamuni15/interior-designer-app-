package com.example.dreamcraft.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import com.example.dreamcraft.R
import com.google.android.material.textfield.TextInputEditText

fun Fragment.isWhatsappInstalled(): Boolean {
    val packageManager = requireActivity().packageManager
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                "com.whatsapp",
                PackageManager.PackageInfoFlags.of(PackageManager.GET_ACTIVITIES.toLong())
            )
            true
        } else {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        }
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}
fun redirectToURL(context: Context,appLink: String?) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appLink))
        context.startActivity(intent)
    }catch (e:Exception){
        e.printStackTrace()
    }

}

fun TextInputEditText.validation(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(text: Editable?) {
            afterTextChanged.invoke(text.toString())
        }
    })
}

fun isValidName(name: String, context: Context): String? {
    if (name.isBlank()) {
        return context.getString(R.string.empty_,"name")
    }
    if (name.length <= 1) {
        return context.getString(R.string.name_length)
    }
    return null
}
fun isValidEmail(email: String, context: Context): String? {
    if (email.isBlank()) {
        return context.getString(R.string.empty_,"Email")
    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        return context.getString(R.string.enter_valid_email)
    }
    return null
}

fun isValidMobileNumber(number: String, context: Context): String? {
    if (number.isBlank()) {
        return context.getString(R.string.empty_,"Mobile Number")
    }
    if (number.length != 10) {
        return context.getString(R.string.mobile_number_length)
    }
    if (!number.matches("^[6-9][0-9]{9}$".toRegex())) {
        return context.getString(R.string.enter_valid_number)
    }
    return null
}