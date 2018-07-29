package com.example.isabelmendes.project1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isabelmendes.project1.R;

public class OrderDetailFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_orders_list,
                container, false);

        getActivity().setTitle("Detalhes");

        long orderId;
        Bundle bundle = this.getArguments();
        if ((bundle != null) && (bundle.containsKey("order_id"))) {
            orderId = bundle.getLong("order_id");

        } return rootView;

    }
}
