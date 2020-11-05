package com.knesarcreation.newsapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.knesarcreation.newsapp.R
import com.knesarcreation.newsapp.model.Articles
import com.knesarcreation.newsapp.util.Utils

class NewsAdapter(
    private val context: Context,
    private val articles: ArrayList<Articles>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgNewsThumbnail: ImageView = view.findViewById(R.id.imgNewsThumbnail)
        val txtTitle: TextView = view.findViewById(R.id.txtNewsTitle)
        val txtDescription: TextView = view.findViewById(R.id.desc)
        val author: TextView = view.findViewById(R.id.txtAuthor)
        val publishedAt: TextView = view.findViewById(R.id.txtPublishedAt)
        val newsSource: TextView = view.findViewById(R.id.txtSource)
        val time: TextView = view.findViewById(R.id.txtTime)
        val progressbar: ProgressBar = view.findViewById(R.id.prograss_load_photo)
        val rlContent: RelativeLayout = view.findViewById(R.id.rlContent)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.news_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val requestOptions = RequestOptions()
        /*Loading images using glide*/
        Glide.with(context).load(articles[position].urlToImage).apply(requestOptions)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressbar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressbar.visibility = View.GONE
                    return false
                }

            })
            .transition(DrawableTransitionOptions().crossFade())
            .into(holder.imgNewsThumbnail)

        holder.txtTitle.text = articles[position].title
        holder.txtDescription.text = articles[position].description
        holder.newsSource.text = articles[position].source.name
        holder.time.text = " \u2022" + Utils().DateToTimeFormat(articles[position].publishedAt)
        holder.publishedAt.text = Utils().DateFormat(articles[position].publishedAt)
        holder.author.text = articles[position].author

        holder.rlContent.setOnClickListener {
            listener.onItemClick(position)
        }

    }

    override fun getItemCount() = articles.size
}