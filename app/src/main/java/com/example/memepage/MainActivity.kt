package com.example.memepage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeloader()
    }


    @SuppressLint("SuspiciousIndentation")
    fun memeloader() {

// ...
  val imagee:ImageView = findViewById(R.id.imageview)
      val   progressBar:ProgressBar = findViewById(R.id.progressBar)
          progressBar.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
              val url = response.getString("url")

                Picasso.get()
                    .load(url)
                    .into(imagee, object : Callback {
                        override fun onSuccess() {
                            progressBar.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            // Handle error
                        }
                    })
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)

    }

    fun next(view: View) {
        memeloader()
    }

    fun share(view: View) {}
}


