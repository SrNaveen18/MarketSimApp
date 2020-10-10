package com.example.marketsimapp.ui.publirepodetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.marketsimapp.BR
import com.example.marketsimapp.R
import com.example.marketsimapp.base.BaseFragment
import com.example.marketsimapp.databinding.FragmentPublicRepoDetailsBinding
import org.koin.android.ext.android.inject

class PublicRepoDetailsFragment : BaseFragment<FragmentPublicRepoDetailsBinding, PublicRepoDetailsViewModel>() {
    private val publicRepoViewModel: PublicRepoDetailsViewModel by inject()

    override fun getViewModel(): PublicRepoDetailsViewModel? = publicRepoViewModel

    override fun getBindingVariable(): Int = BR.publicRepoDetailsVM

    override fun getContentView(): Int = R.layout.fragment_public_repo_details

    private val navArgs : PublicRepoDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        publicRepoViewModel.repoItem.value = navArgs.RepoItem

    }



}