package com.example.lawadvat.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lawadvat.R
import com.example.lawadvat.databinding.ActivityReviewBinding
import com.example.lawadvat.ui.Variablecontain

class ReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        in_it()
    }

    private fun in_it() {
        val name=intent.getStringExtra(Variablecontain.name)
        val img=intent.getStringExtra(Variablecontain.img)
        binding.name.text=name.toString()
        Glide.with(this).load(img.toString()).into(binding.reviewProfile)
        val getreview=binding.review.toString()
        binding.submitBtn.setOnClickListener {
            if (!binding.like.isClickable)
            {
                Toast.makeText(this,"check the reaction button ",Toast.LENGTH_SHORT).show()

            }
            else if (getreview.isEmpty())
            {
                Toast.makeText(this,"Please review",Toast.LENGTH_SHORT).show()
            }else if (getreview.length < 10){
                Toast.makeText(this," please write review more than 10 words",Toast.LENGTH_SHORT).show()
            }
            else{
                forward_review(getreview)
            }
        }

    }

    private fun forward_review(getreview: String) {

    }
}