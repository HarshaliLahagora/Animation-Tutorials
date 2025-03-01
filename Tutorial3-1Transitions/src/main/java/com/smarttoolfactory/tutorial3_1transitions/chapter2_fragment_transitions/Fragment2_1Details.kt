package com.smarttoolfactory.tutorial3_1transitions.chapter2_fragment_transitions
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.smarttoolfactory.tutorial3_1transitions.R

class Fragment2_1Details : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment2_1details, container, false)

        prepareSharedElementTransition()
        postponeEnterTransition()
        return view
    }


    private fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_main_detail)
        sharedElementEnterTransition = transition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivPhoto = view.findViewById<ImageView>(R.id.ivPhoto)
        val imageRes = arguments?.getInt("imageRes", -1) ?: -1

        Glide
            .with(this)
            .load(imageRes)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(
                        requireContext(),
                        "Glide onLoadFailed()",
                        Toast.LENGTH_SHORT
                    ).show()

                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(
                        requireContext(),
                        "Glide onResourceReady()",
                        Toast.LENGTH_SHORT
                    ).show()
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(ivPhoto)
    }


    /**
     * Listeners for enter, exit transitions
     */
    private fun addTransitionListeners() {


    }

}
