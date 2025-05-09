package com.example.utsfederix

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide

class ChattingListActivity : Activity() {

    data class ChatItem(val name: String, val message: String, val iconResId: Int? = null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting_list)

        val listView = findViewById<ListView>(R.id.chatting_list_view)

        val data = listOf(
            ChatItem("Federix", "Hai!", iconResId = R.drawable.cat1),
            ChatItem("Fedz", "Halo!", iconResId = R.drawable.cat2),
            ChatItem("Federix2", "Halo!", iconResId = R.drawable.cat3),
            ChatItem("Federix3", "Halo!", iconResId = R.drawable.cat4),
            ChatItem("Federix4", "Halo!", iconResId = R.drawable.cat5),
            ChatItem("Federix5", "Halo!", iconResId = R.drawable.cat6),
            ChatItem("Federix6", "Halo!", iconResId = R.drawable.cat7),
            ChatItem("Federix7", "Halo!", iconResId = R.drawable.cat8),
            ChatItem("Federix8", "Halo!", iconResId = R.drawable.cat9)
        )

        val adapter = object : BaseAdapter() {
            override fun getCount() = data.size
            override fun getItem(position: Int) = data[position]
            override fun getItemId(position: Int) = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val context = parent.context
                val layout = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(16, 16, 16, 16)
                }

                val imageView = ImageView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(100, 100)
                }

                val textLayout = LinearLayout(context).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 0, 0, 0)
                }

                val nameView = TextView(context).apply {
                    textSize = 16f
                    setTextAppearance(android.R.style.TextAppearance_Medium)
                }

                val messageView = TextView(context).apply {
                    textSize = 14f
                    setTextColor(0xFF666666.toInt())
                }

                val item = getItem(position)
                nameView.text = item.name
                messageView.text = item.message
                Glide.with(context).load(item.iconResId).into(imageView)

                textLayout.addView(nameView)
                textLayout.addView(messageView)
                layout.addView(imageView)
                layout.addView(textLayout)

                return layout
            }
        }

        listView.adapter = adapter
    }
}
