package com.example.isabelmendes.project1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isabelmendes.project1.R;
import com.example.isabelmendes.project1.models.Order;
import com.example.isabelmendes.project1.tasks.OrderEvents;
import com.example.isabelmendes.project1.tasks.OrderTasks;
import com.example.isabelmendes.project1.webservice.WebServiceResponse;

import java.io.Serializable;
import java.util.List;

public class OrderDetailFragment extends Fragment implements OrderEvents{

    private TextView txtIdPedido;
    private TextView txtEmail;
    private TextView txtFreight;
    private TextView txtNumItems;

    private Order order;
    public OrderDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_detail,
                container, false);

        getActivity().setTitle("Detalhes");

        txtIdPedido = rootView.findViewById(R.id.txtIdPedido);
        txtEmail = rootView.findViewById(R.id.txtEmail);
        txtFreight = rootView.findViewById(R.id.txtFreight);
        txtNumItems = rootView.findViewById(R.id.txtNumItems);

        if ((savedInstanceState != null) && (savedInstanceState.containsKey("pedido"))){
            this.order = (Order) savedInstanceState.getSerializable("pedido");
            showOrder(this.order);
        }else{

        long orderId;
        Bundle bundle = this.getArguments();
        if ((bundle != null) && (bundle.containsKey("order_id"))) {
            orderId = bundle.getLong("order_id");
            Toast.makeText(getActivity(), "id" + orderId, Toast.LENGTH_LONG).show();

            OrderTasks orderTasks = new OrderTasks(getActivity(), this);
            orderTasks.getOrderById(orderId);
        }
        }
        return rootView;

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("pedido", this.order);
        super.onSaveInstanceState(outState);
    }

    private void showOrder(Order order){
        txtIdPedido.setText(String.valueOf(order.getId()));
        txtEmail.setText(order.getEmail());
        txtFreight.setText(String.valueOf(order.getFreightPrice()));
        txtNumItems.setText(String.valueOf(order.getOrderItems().size()));
    }

    @Override
    public void getOrdersFinished(List<Order> orders) {

    }

    @Override
    public void getOrdersFailed(WebServiceResponse webServiceResponse) {

    }

    @Override
    public void getOrderByIdFinished(Order order) {
        this.order = order;
        showOrder(order);

    }

    @Override
    public void getOrderByIdFailed(WebServiceResponse webServiceResponse) {
        Toast.makeText(getActivity(), "Falha na consulta da lista de pedidos" +
                webServiceResponse.getResultMessage() + " - Código do erro: " +
                webServiceResponse.getResponseCode(), Toast.LENGTH_SHORT).show();

    }
}
