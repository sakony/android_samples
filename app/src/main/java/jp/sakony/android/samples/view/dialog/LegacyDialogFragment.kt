package jp.sakony.android.samples.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import jp.sakony.android.samples.view.dialog.model.DialogState

/**
 * A simple dialog fragment.
 */
class LegacyDialogFragment : DialogFragment() {
    companion object {
        private const val BUNDLE_KEY_TITLE = "bundle_key_title"
        private const val BUNDLE_KEY_MESSAGE = "bundle_key_message"

        private fun newInstance() = LegacyDialogFragment()

        private fun newInstance(title: String, message: String): LegacyDialogFragment {
            return newInstance().apply {
                arguments = bundleOf(
                    Pair(BUNDLE_KEY_TITLE, title),
                    Pair(BUNDLE_KEY_MESSAGE, message)
                )
            }
        }

        /**
         * Show this dialog.
         */
        fun show(title: String, message: String, fragmentManager: FragmentManager, tag: String) {
            newInstance(title, message).run {
                show(fragmentManager, tag)
            }
        }
    }

    lateinit var title: String
    lateinit var message: String

    private lateinit var listener: DialogListener

    interface DialogListener {
        fun onOkClick(dialog: LegacyDialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as DialogListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments!!.run {
            title = getString(BUNDLE_KEY_TITLE)!!
            message = getString(BUNDLE_KEY_MESSAGE)!!
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                listener.onOkClick(this)
            }
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        listener.onOkClick(this)
    }
}
