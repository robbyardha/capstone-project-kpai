package com.example.percobaantensorflow.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.percobaantensorflow.data.Message
import com.example.percobaantensorflow.databinding.ItemMessageBinding
import com.example.percobaantensorflow.utils.Constants.RECEIVE_ID
import com.example.percobaantensorflow.utils.Constants.SEND_ID

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {



    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder( private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            with(binding){
                val currentMessage = messagesList[position]
                when (currentMessage.id) {
                    SEND_ID -> {
                        tvMessage.apply {
                            text = currentMessage.message
                            visibility = View.VISIBLE
                        }
                        tvBotMessage.visibility = View.GONE
                    }
                    RECEIVE_ID -> {
                        tvBotMessage.apply {
                            text = currentMessage.message
                            visibility = View.VISIBLE
                        }
                        tvMessage.visibility = View.GONE
                    }
                }
            }
        }

        init {
            itemView.setOnClickListener {

                //Remove message on the item clicked
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind()
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}