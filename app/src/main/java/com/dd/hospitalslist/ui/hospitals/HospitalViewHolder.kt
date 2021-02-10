package com.dd.hospitalslist.ui.hospitals

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dd.hospitalslist.R
import com.dd.hospitalslist.data.entities.Hospital
import com.dd.hospitalslist.utils.Animations.disableViewDuringAnimation

class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val TAG = "HospitalViewHolder"

    private val tvMedicalFacility = itemView.findViewById<TextView>(R.id.tvMedicalFacility)
    private val tvRegion = itemView.findViewById<TextView>(R.id.tvRegion)
    private val tvLocation = itemView.findViewById<TextView>(R.id.tvLocation)
    private val tvPhone = itemView.findViewById<TextView>(R.id.tvPhone)
    private val ivIconPhone = itemView.findViewById<ImageView>(R.id.ivIconPhone)
    private val ivLocation = itemView.findViewById<ImageView>(R.id.ivIconLocation)
    private val ivCopy = itemView.findViewById<ImageView>(R.id.ivCopy)
    private val ivShare = itemView.findViewById<ImageView>(R.id.ivShare)
    private val ivWhatsApp = itemView.findViewById<ImageView>(R.id.ivWhatsApp)
    private val ivTelegram = itemView.findViewById<ImageView>(R.id.ivTelegram)

    companion object {
        fun create(parent: ViewGroup): HospitalViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview, parent, false)




            return HospitalViewHolder(view)
        }
    }

    init {


        ivCopy.setOnClickListener { view ->
            scalingAnimationOf(view)
            copyToClipboard(view.context, getTextToShare())

        }

        ivShare.setOnClickListener { view ->
            scalingAnimationOf(view)
            shareText(view.context, getTextToShare())
        }

        ivTelegram.setOnClickListener { view ->
            scalingAnimationOf(view)
            onClickTelegram(view.context, getTextToShare())
        }

        ivWhatsApp.setOnClickListener { view ->
            scalingAnimationOf(view)
            onClickWhatsApp(view.context, getTextToShare())
        }

        ivIconPhone.setOnClickListener { view ->
            scalingAnimationOf(view)
            dialPhone(view.context, getPhoneNumber())
        }

        tvPhone.setOnClickListener { view ->
            scalingAnimationOf(view)
            dialPhone(view.context, getPhoneNumber())
        }

        ivLocation.setOnClickListener { view ->
            scalingAnimationOf(view)
            shareLocation(view.context, getLocationAddress())
        }

        tvLocation.setOnClickListener { view ->
            scalingAnimationOf(view)
            shareLocation(view.context, getLocationAddress())
        }

    }

    fun bind(hospital: Hospital) {
        tvMedicalFacility.text = hospital.branch
        tvRegion.text = hospital.region
        tvLocation.text = hospital.address
        tvPhone.text = hospital.phone?.replace("\\n", "\n")
    }

    private fun getTextToShare(): String {
        return "Регион: ${tvRegion.text}, " +
                "Название: ${tvMedicalFacility.text}, " +
                "Адрес: ${tvLocation.text}, " +
                "Контакты: ${tvPhone.text}"
    }

    private fun getPhoneNumber(): String {
        return tvPhone.text.toString()
    }

    private fun getLocationAddress(): String {
        return tvLocation.text.toString()
    }


    private fun scalingAnimationOf(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.8f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.8f)
        ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }


    private fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("Makal text", text)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(context, R.string.text_copied, Toast.LENGTH_SHORT).show()
    }

    private fun shareText(context: Context, text: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, text)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(
            Intent.createChooser(
                sharingIntent,
                context.resources.getString(R.string.share_using)
            )
        )
    }

    private fun onClickWhatsApp(context: Context, text: String) {
        val pm: PackageManager = context.packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp")
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(
                Intent.createChooser(
                    waIntent,
                    context.getString(R.string.share_using)
                )
            )
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(
                context,
                context.getString(R.string.whatsapp_not_installed),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun onClickTelegram(context: Context, text: String) {
        val pm: PackageManager = context.packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            pm.getPackageInfo("org.telegram.messenger", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("org.telegram.messenger")
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(
                Intent.createChooser(
                    waIntent,
                    context.getString(R.string.share_using)
                )
            )
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(
                context,
                context.getString(R.string.telegram_not_installed),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun dialPhone(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$text")
        context.startActivity(intent)
    }

    private fun shareLocation(context: Context, text: String) {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/search/?api=1&query=$text")
            )
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT)
                .show()
        }
    }
}