package com.katfox.movieappsimple.feature_movie.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katfox.movieappsimple.core.util.loadWithCoil
import com.katfox.movieappsimple.core.util.toImage
import com.katfox.movieappsimple.databinding.MovieItemBinding
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.RvViewHolder>() {
    inner class RvViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<MovieInfo>() {
        override fun areItemsTheSame(
            oldItem: MovieInfo,
            newItem: MovieInfo
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieInfo,
            newItem: MovieInfo
        ): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvDesc.text = item.overview
            loadWithCoil(iv, item.posterPath.toImage())

            root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((MovieInfo) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieInfo) -> Unit) {
        onItemClickListener = listener
    }
}