package com.example.androidmvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmvp.model.Post
import com.example.androidmvp.R
import com.example.androidmvp.activity.MainActivity
import com.example.androidmvp.utils.Utils
import java.util.*
import kotlin.collections.ArrayList

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PosterViewHolder) {
            val ll_poster = holder.ll_poster
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body
            tv_title.text = post.title.uppercase(Locale.getDefault())
            tv_body.text = post.body

            ll_poster.setOnLongClickListener {
                deletePostDialog(post)
                false
            }
        }
    }

    inner class PosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ll_poster: LinearLayout = view.findViewById(R.id.ll_poster)
        var tv_title: TextView = view.findViewById(R.id.tv_title)
        var tv_body: TextView = view.findViewById(R.id.tv_body)

    }

    private fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.mainPresenter.apiPostDelete(post)
            }

            override fun onNegativeClick() {

            }
        })
    }
}