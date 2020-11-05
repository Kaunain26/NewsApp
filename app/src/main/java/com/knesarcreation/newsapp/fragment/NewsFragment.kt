package com.knesarcreation.newsapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knesarcreation.newsapp.R
import com.knesarcreation.newsapp.activity.NewsDetailsActivity
import com.knesarcreation.newsapp.adapter.NewsAdapter
import com.knesarcreation.newsapp.api.ApiClient
import com.knesarcreation.newsapp.api.ApiInterface
import com.knesarcreation.newsapp.model.Articles
import com.knesarcreation.newsapp.model.News
import com.knesarcreation.newsapp.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment(), NewsAdapter.OnItemClickListener {

    lateinit var mRecyclerView: RecyclerView
    var articles = arrayListOf<Articles>()
    lateinit var mAdapter: NewsAdapter
    var pos: Int = 0

    companion object {
        const val API_KEY = "d2e192c365e147df821d05e75d19f830"

        const val KEY_CATEGORY = "category"
        fun newInstance(position: Int): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_CATEGORY, position)
                    pos = position
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        mRecyclerView = view.findViewById(R.id.mRecyclerView)

        /*setting up recycler view*/
        setUpRecyclerView()

        /*loading json data from server*/
        when (pos) {
            0 -> loadTopHeadLines("business")
            1 -> loadTopHeadLines("sports")
            2 -> loadNews("the-wall-street-journal")
            3 -> loadNews("fox-news")
            4 -> loadNews("the-hill")
            5 -> loadNews("techcrunch")
            6 -> loadNews("reuters")
            7 -> loadNews("engadget")
            8 -> loadNews("techradar")
            9 -> loadNews("mashable")
        }

        return view
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(activity as Context)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun loadNews(source: String) {
        val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        val call: Call<News>
        call = apiInterface.getNews(source, API_KEY)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()?.articles != null) {
                    if (articles.isEmpty()) {
                        articles.clear()
                    }
                    articles = response.body()?.articles!!
                    mAdapter = NewsAdapter(activity as Context, articles, this@NewsFragment)
                    mRecyclerView.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity as Context, "No Result", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(
                    activity as Context,
                    "Some unexpected error occurred",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun loadTopHeadLines(category: String) {
        val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        val country = Utils().getCountry()
        val call: Call<News>
        call = apiInterface.getTopHeadLines(country!!, category, API_KEY)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()?.articles != null) {
                    if (articles.isEmpty()) {
                        articles.clear()
                    }
                    articles = response.body()!!.articles
                    mAdapter = NewsAdapter(activity as Context, articles, this@NewsFragment)
                    mRecyclerView.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(activity as Context, "No Result", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(
                    activity as Context,
                    "Some unexpected error occurred",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onItemClick(position: Int) {
        startActivity(
            Intent(activity as Context, NewsDetailsActivity::class.java).putExtra(
                "url",
                articles[position].url
            )
        )
    }
}