package com.example.percobaantensorflow.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.percobaantensorflow.R
import com.example.percobaantensorflow.data.Message
import com.example.percobaantensorflow.databinding.ActivityMainBinding
import com.example.percobaantensorflow.utils.BotResponse
import com.example.percobaantensorflow.utils.Constants.OPEN_GOOGLE
import com.example.percobaantensorflow.utils.Constants.OPEN_SEARCH
import com.example.percobaantensorflow.utils.Constants.RECEIVE_ID
import com.example.percobaantensorflow.utils.Constants.SEND_ID
import com.example.percobaantensorflow.utils.Time
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"


    private lateinit var binding: ActivityMainBinding


    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        supportActionBar?.hide()



        setTitle("Chatbot Sederhana")

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")

    }


    //Function ClickEvent
    private fun clickEvents() {

        //Send a message
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    //Function RecyclerView
    private fun recyclerView() {
        adapter = MessagingAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)

    }

    //jika ada pesan, gulir ke bawah saat membuka kembali aplikasi
    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    //Function Kirim Pesan
    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            binding.etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    // Response dari chatbot
    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }



    private fun customBotMessage(message: String){
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

}




