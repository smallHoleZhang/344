package com.bignerdranch.android.beom.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beom.R;

/**
 * 项目名： begin
 * 包名：   com.bignerdranch.android.begin
 * 文件名： ButleFragment
 * 创建者： Zhang
 * 描述：   TODO
 */

public class ButleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.butler_fragment_layout,container,false);
        return v;
    }
}
