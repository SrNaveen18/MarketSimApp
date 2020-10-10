package com.example.marketsimapp.ui.publicrepo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marketsimapp.BR
import com.example.marketsimapp.R
import com.example.marketsimapp.base.BaseFragment
import com.example.marketsimapp.databinding.FragmentPublicRepoBinding
import com.example.marketsimapp.extension.isNetConnected
import com.example.marketsimapp.model.PublicRepoItem
import com.example.marketsimapp.ui.publicrepo.pagingadapter.LoadingFooterAdapter
import com.example.marketsimapp.ui.publicrepo.pagingadapter.PagingAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_public_repo.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class PublicRepoListFragment : BaseFragment<FragmentPublicRepoBinding, PublicRepoViewModel>(),
    View.OnClickListener {
    private val publicRepoViewModel: PublicRepoViewModel by inject()
    private var pagingAdapter: PagingAdapter? = null

    override fun getViewModel(): PublicRepoViewModel? = publicRepoViewModel

    override fun getBindingVariable(): Int = BR.publicRepoVM

    override fun getContentView(): Int = R.layout.fragment_public_repo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        if (checkInternetAvailable()) {
            loadData()
        }
    }

    private fun initAdapter() {
        pagingAdapter = PagingAdapter(listener = this)
        recyclerView.apply {
            adapter = pagingAdapter
        }

        recyclerView.apply {
            adapter = pagingAdapter?.withLoadStateFooter(
                footer = LoadingFooterAdapter { pagingAdapter?.retry() }
            )
        }
    }

    private var searchJob: Job? = null

    private fun loadData() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            publicRepoViewModel.getPublicRepo().collectLatest {
                pagingAdapter?.submitData(it)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardView -> {
                val repoItem = v.getTag(R.id.cardView) as PublicRepoItem
                navigateToDetailsScreen(repoItem)
            }
        }
    }

    private fun navigateToDetailsScreen(repoItem: PublicRepoItem) {
        val action =
            PublicRepoListFragmentDirections.actionPublicRepoListFragmentToPublicRepoDetailsFragment(
                repoItem
            )
        findNavController().navigate(action)
    }

    private fun checkInternetAvailable(): Boolean {
        val hasInternet = requireActivity().isNetConnected()
        return if (!hasInternet) {
            showSnackBar()
            false
        } else {
            true
        }
    }

    private fun showSnackBar() {
        val snack = Snackbar.make(
            requireView(),
            getString(R.string.error_no_internet),
            Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}