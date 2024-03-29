package com.example.isabelmendes.project1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isabelmendes.project1.R;
import com.example.isabelmendes.project1.adapters.PedidoAdapter;
import com.example.isabelmendes.project1.models.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidosFragments extends Fragment {


    private ListView listViewPedidos;
    private List<Pedido> pedidos;
    public ListaPedidosFragments(){}


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pedidos_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_pedido:
                Toast.makeText(getActivity(),
                        "Novo pedido", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_lista_pedidos,
                        container, false);

        getActivity().setTitle("Pedidos");
        setHasOptionsMenu(true);

        pedidos = new ArrayList<Pedido>();

        for (int j = 0; j < 5; j++) {
            Pedido pedidoAux = new Pedido();
            pedidoAux.setOrderId(j);

            pedidoAux.setDataPedido("10/04/2016 11:50:00");
            pedidos.add(pedidoAux);
        }
        listViewPedidos = (ListView) rootView.
                findViewById(R.id.lista_pedidos);

        PedidoAdapter pedidoAdapter = new PedidoAdapter(
                getActivity(), pedidos);
        listViewPedidos.setAdapter(pedidoAdapter);

        return rootView;
    }
}
