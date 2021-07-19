package com.arsy.testscreeningsatu.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsy.testscreeningsatu.databinding.ActivityHomeBinding
import com.arsy.testscreeningsatu.ui.SelectActivity.Companion.EXTRA_NAME
import java.lang.StringBuilder

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val edtNama = binding.editTextName.text
        val name: String = edtNama.toString().trim()

        if (isPalindrome(name)) {
            Toast.makeText(this@HomeActivity, "isPalindrome", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@HomeActivity, "not Palindrome", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(this, SelectActivity::class.java)
        intent.putExtra(EXTRA_NAME, name)
        startActivity(intent)
    }

    private fun isPalindrome(string: String): Boolean {
        val stringBuilder = StringBuilder(string)
        val reverseString = stringBuilder.reverse().toString()

        return string.equals(reverseString, ignoreCase = true)
    }
}