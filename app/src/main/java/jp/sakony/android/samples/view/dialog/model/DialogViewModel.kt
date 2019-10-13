package jp.sakony.android.samples.view.dialog.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.sakony.android.samples.view.dialog.SimpleDialogFragment

class DialogViewModel: ViewModel() {
    val state = MutableLiveData<DialogState<SimpleDialogFragment>>()
}