package com.android.movies.presentation.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.movies.databinding.HolderPhotoBinding
import com.android.movies.domain.model.MovieList
import com.android.movies.presentation.viewModel.MovieViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


internal class MovieItemAdapter(private val listener: OnItemClickListener, private val drawable : Int) :
    PagingDataAdapter<MovieList, MovieItemAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            HolderPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: HolderPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(movie: MovieList) {
            binding.movieViewModel = MovieViewModel(movie)
            binding.apply {
                val requestOptions = RequestOptions()
                requestOptions.placeholder(drawable)
                requestOptions.error(drawable)
                Glide.with(itemView)
                    .load(movie.poster)
                    .centerCrop()
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.photoProgressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.photoProgressBar.visibility = View.GONE
                            return false
                        }

                    })
                    .into(binding.photoImageView)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: MovieList)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieList>() {
            override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList) =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList) =
                oldItem == newItem
        }
    }
}
