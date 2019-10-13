package jp.sakony.android.samples.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import jp.sakony.android.samples.view.dialog.model.DialogState
import jp.sakony.android.samples.view.dialog.model.DialogViewModel

/**
 * A simple dialog fragment.
 */
class SimpleDialogFragment : DialogFragment() {
    companion object {
        private const val BUNDLE_KEY_TITLE = "bundle_key_title"
        private const val BUNDLE_KEY_MESSAGE = "bundle_key_message"

        private fun newInstance() = SimpleDialogFragment()

        private fun newInstance(title: String, message: String): SimpleDialogFragment {
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

    private val viewModel: DialogViewModel by viewModels({ requireActivity() })

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
                viewModel.state.value = DialogState.Ok(this@SimpleDialogFragment)
            }
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        viewModel.state.value = DialogState.Cancel(this@SimpleDialogFragment)
    }
}
