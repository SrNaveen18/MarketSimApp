package com.example.marketsimapp.ui.publirepodetails

import androidx.lifecycle.MutableLiveData
import com.example.marketsimapp.base.BaseViewModel
import com.example.marketsimapp.model.PublicRepoItem

class PublicRepoDetailsViewModel : BaseViewModel() {

     var repoItem  = MutableLiveData<PublicRepoItem>()
}