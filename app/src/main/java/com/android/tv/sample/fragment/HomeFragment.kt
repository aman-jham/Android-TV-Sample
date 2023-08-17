package com.android.tv.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.tv.sample.R
import com.android.tv.sample.data.local.DataModel
import com.android.tv.sample.databinding.FragmentHomeBinding
import com.android.tv.sample.utils.Constants
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragment : Fragment() {

    private lateinit var listFragment: ListFragment

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()


        val gson = Gson()
        val i: InputStream = requireContext().assets.open(Constants.JSON)
        val br = BufferedReader(InputStreamReader(i))
        val dataList: DataModel = gson.fromJson(br, DataModel::class.java)

        listFragment.bindData(dataList)

        listFragment.setOnContentSelectedListener {
            updateBanner(it)
        }

        listFragment.setOnItemClickListener {

        }
    }

    private fun updateBanner(dataList: DataModel.Result.Detail) {
        fragmentHomeBinding.title.text = dataList.title
        fragmentHomeBinding.description.text = dataList.overview
        val url = Constants.IMAGE_URL + dataList.backdrop_path
        Glide.with(this).load(url).into(fragmentHomeBinding.imgBanner)
    }
}