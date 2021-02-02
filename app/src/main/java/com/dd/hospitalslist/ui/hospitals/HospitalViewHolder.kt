package com.dd.hospitalslist.ui.hospitals

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.recyclerview.widget.RecyclerView
import com.dd.hospitalslist.R
import com.dd.hospitalslist.data.entities.Hospital

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

    companion object {
        fun create(parent: ViewGroup): HospitalViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview, parent, false)
            return HospitalViewHolder(view)
        }


    }

    init {
        ivCopy.setOnClickListener { view ->

            ObjectAnimator.ofFloat(view, View.ROTATION, -360f, 0f).apply {
                duration = 1000
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        ivCopy.isEnabled = false
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        ivCopy.isEnabled = true
                    }
                })
                start()
            }




//            Log.i(TAG, "ivCopy.setOnClickListener: true")
//            ObjectAnimator.ofFloat(view, View.ROTATION, -360f, 0f).apply {
//                duration = 1000
//                addListener {
//                    doOnEnd { }
//                    doOnStart { }
//                }
//                start()
//            }
        }


    }

    fun bind(hospital: Hospital) {
        tvAddress.text = hospital.address
        tvJobTitle.text = hospital.branch
        tvOfficeHours.text = hospital.branch
        tvPhone.text = hospital.phone
    }

}