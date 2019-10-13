package jp.sakony.android.samples.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.sakony.android.samples.R
import jp.sakony.android.samples.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(context, DialogActivity::class.java)
    }

    private lateinit var binding: ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog)
    }
}