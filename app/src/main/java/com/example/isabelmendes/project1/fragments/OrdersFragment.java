package com.example.isabelmendes.project1.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isabelmendes.project1.R;
import com.example.isabelmendes.project1.adapters.OrderAdapter;
import com.example.isabelmendes.project1.models.Order;
import com.example.isabelmendes.project1.tasks.OrderEvents;
import com.example.isabelmendes.project1.tasks.OrderTasks;
import com.example.isabelmendes.project1.util.CheckNetworkConnection;
import com.example.isabelmendes.project1.webservice.WebServiceResponse;
import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment implements OrderEvents {

    private ListView listViewOrders;
    private List<Order> orders;
    public OrdersFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_orders_list,
                container, false);
        getActivity().setTitle("Orders");

        listViewOrders = (ListView) rootView.
                findViewById(R.id.orders_list);

        listViewOrders.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        startOrderDetail(id);
                    }
                });

        if (CheckNetworkConnection.isNetworkConnected(getActivity())) {
            OrderTasks orderTasks = new OrderTasks(getActivity(), this);
            orderTasks.getOrders();
        }
        return rootView;
    }

    private void startOrderDetail (long orderId) {
        Class fragmentClass;
        Fragment fragment = null;
        fragmentClass = OrderDetailFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            if (orderId >= 0) {
                Bundle args = new Bundle();
                args.putLong("order_id", orderId);
                fragment.setArguments(args);
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction =
                    fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment,
                    OrderDetailFragment.class.getCanonicalName());
            transaction.addToBackStack(
                    OrdersFragment.class.getCanonicalName());
            transaction.commit();
        } catch (Exception e) {
            try {
                Toast.makeText(getActivity(),
                        "Erro ao tentar abrir detalhes do pedido",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e1) {}
        }
    }
    @Override
    public void getOrdersFinished(List<Order> orders) {
        OrderAdapter orderAdapter = new OrderAdapter(
                getActivity(), orders);
        listViewOrders.setAdapter(orderAdapter);
    }
    @Override
    public void getOrdersFailed(WebServiceResponse webServiceResponse) {
        Toast.makeText(getActivity(), "Falha na consulta da lista de pedidos"
                +
                webServiceResponse.getResultMessage() + " - Código do erro: " +
                webServiceResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getOrderByIdFinished(Order order) {
    }
    @Override
    public void getOrderByIdFailed(WebServiceResponse
                                           webServiceResponse) {
    }
}
