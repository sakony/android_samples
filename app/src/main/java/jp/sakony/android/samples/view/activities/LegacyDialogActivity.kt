package jp.sakony.android.samples.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.sakony.android.samples.R
import jp.sakony.android.samples.databinding.ActivityLegacyDialogBinding
import jp.sakony.android.samples.view.dialog.LegacyDialogFragment

class LegacyDialogActivity : AppCompatActivity(), LegacyDialogFragment.DialogListener {
    companion object {
        val TAG = LegacyDialogActivity::class.java.simpleName ?: ""
    }

    private lateinit var binding: ActivityLegacyDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_legacy_dialog)
        binding.button.setOnClickListener {
            LegacyDialogFragment.show(
                "legacy",
                "Are you sure you want to finish this activity?",
                supportFragmentManager,
                TAG
            )
        }
    }

    override fun onOkClick(dialog: LegacyDialogFragment) {
        finish()
    }
}
