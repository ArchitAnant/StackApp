package com.example.stackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.stackapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(StackFragment())

        binding.stack.setOnClickListener{
            replaceFragment(StackFragment())
        }
        binding.notation.setOnClickListener{
            replaceFragment(NotationFragment())
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.mainFragment,fragment)
        fragTransaction.commit()
    }
}