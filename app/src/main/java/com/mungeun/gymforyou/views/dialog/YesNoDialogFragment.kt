package com.mungeun.gymforyou.views.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class YesNoDialogFragment : AppCompatDialogFragment() {

    companion object{
        lateinit var listener : onClickYesNo
        private const val TITLE = "title"
        private const val MESSAGE = "message"
        private const val NEGATIVE = "negative"
        private const val POSITIVE = "positive"
        fun newInstance(
            title : String,
            message : String,
            negative : String,
            positive : String,
            onClickYesNo: onClickYesNo
        ) : YesNoDialogFragment {
            val bundle = Bundle().apply {
                putString(TITLE,title)
                putString(MESSAGE,message)
                putString(NEGATIVE,negative)
                putString(POSITIVE,positive)
            }
            listener = onClickYesNo
            return YesNoDialogFragment().apply {
                arguments = bundle
            }
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = requireArguments()
        return MaterialAlertDialogBuilder(requireContext()).
                setTitle(args.getString(TITLE))
            .setMessage(args.getString(MESSAGE))
            .setNegativeButton(args.getString(NEGATIVE),null)
            .setPositiveButton(args.getString(POSITIVE)){ _,_ ->
                listener.clickYes()
            }.create()

    }
}
interface onClickYesNo {
    fun clickYes()
    fun clickNo()
}

