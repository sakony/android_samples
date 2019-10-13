package jp.sakony.android.samples.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import jp.sakony.android.samples.R
import jp.sakony.android.samples.databinding.FragmentDialogShowBinding
import jp.sakony.android.samples.view.dialog.SimpleDialogFragment
import jp.sakony.android.samples.view.dialog.model.DialogState
import jp.sakony.android.samples.view.dialog.model.DialogViewModel

class DialogShowFragment : Fragment() {
    companion object {
        val TAG = DialogShowFragment::class.java.simpleName ?: ""
    }

    private lateinit var binding: FragmentDialogShowBinding

    private val dialogViewModel: DialogViewModel by viewModels({ requireActivity() })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_show, container, false)

        binding.showDialogButton.setOnClickListener {
            SimpleDialogFragment.show("from fragment.", "Are you sure you want to finish this activity?", childFragmentManager, TAG)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialogViewModel.state.observe(this) {
            when (it) {
                is DialogState.Ok -> requireActivity().finish()
                is DialogState.Cancel -> {
                    // do nothing.
                }
            }
        }
    }
}