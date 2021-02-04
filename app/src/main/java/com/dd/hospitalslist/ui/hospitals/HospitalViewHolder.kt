package com.dd.hospitalslist.ui.hospitals

import android.animation.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dd.hospitalslist.R
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.utils.Animations.disableViewDuringAnimation

class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val TAG = "HospitalViewHolder"

    private val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
    private val tvJobTitle = itemView.findViewById<TextView>(R.id.tvJobTitle)
    private val tvOfficeHours = itemView.findViewById<TextView>(R.id.tvOfficeHours)
    private val ivIconPhone = itemView.findViewById<ImageView>(R.id.ivIconPhone)
    private val tvPhone = itemView.findViewById<TextView>(R.id.tvPhone)
    private val ivLocation = itemView.findViewById<ImageView>(R.id.ivIconOfficeHours)
    private val ivCopy = itemView.findViewById<ImageView>(R.id.ivCopy)
    private val ivShare = itemView.findViewById<ImageView>(R.id.ivShare)
    private val ivWhatsApp = itemView.findViewById<ImageView>(R.id.ivWhatsApp)
    private val constraintLayoutItem = itemView.findViewById<ConstraintLayout>(R.id.constraintLayoutItem)

    companion object {
        fun create(parent: ViewGroup): HospitalViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recyclerview, parent, false)
            return HospitalViewHolder(view)
        }
    }

    init {
        ivCopy.setOnClickListener { view ->
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.8f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.8f)
            ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE
                disableViewDuringAnimation(view)
                start()
            }

        }

        itemView.setOnClickListener { view ->
            ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 50f).apply {
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE
                disableViewDuringAnimation(view)
                start()
            }

        }


        constraintLayoutItem.setOnLongClickListener { view ->
            ObjectAnimator.ofArgb(view.parent,
                    "backgroundColor",
                    ContextCompat.getColor(itemView.context, R.color.primary),
                    ContextCompat.getColor(itemView.context, R.color.primary_dark)
            ).apply {
                duration = 500
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE
                disableViewDuringAnimation(view)
                start()
            }

            false
        }

        ivWhatsApp.setOnClickListener { view ->
            ObjectAnimator.ofFloat(view, View.ROTATION, -360f, 0f).apply {
                duration = 1000
                disableViewDuringAnimation(view)
                start()
            }
        }

        ivIconPhone.setOnClickListener { view ->
            ObjectAnimator.ofFloat(view, View.ALPHA, 0f).apply {
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE
                disableViewDuringAnimation(view)
                start()
            }

        }

        ivLocation.setOnClickListener { view ->

        }

        ivShare.setOnClickListener { view ->
            val container = view.parent as ViewGroup
            val containerW = container.width
            val containerH = container.height
            var starW: Float = view.width.toFloat()
            var starH: Float = view.height.toFloat()


            val newStar = AppCompatImageView(itemView.context)
            newStar.setImageResource(R.drawable.ic_action_share)
            newStar.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT)
            container.addView(newStar)

            newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
            newStar.scaleY = newStar.scaleX
            starW *= newStar.scaleX
            starH *= newStar.scaleY

            newStar.translationX = Math.random().toFloat() *
                    containerW - starW / 2


            val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y,
                    -starH, containerH + starH)
            mover.interpolator = AccelerateInterpolator(1f)
            val rotator = ObjectAnimator.ofFloat(newStar, View.ROTATION,
                    (Math.random() * 1080).toFloat())
            rotator.interpolator = LinearInterpolator()

            val set = AnimatorSet()
            set.playTogether(mover, rotator)
            set.duration = (Math.random() * 1500 + 500).toLong()

            set.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    container.removeView(newStar)
                }
            })
            set.start()
        }
    }

    fun bind(hospital: Hospital) {
        tvAddress.text = hospital.branch
        tvJobTitle.text = hospital.region
        tvOfficeHours.text = hospital.address
        tvPhone.text = hospital.phone
    }
}