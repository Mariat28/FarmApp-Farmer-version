package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.adapterobjects.ExpenseClass;
import ug.global.glofarmedited.farmfinancials.adapters.ExpenseAdapter;

public class Expenses extends Fragment {
    private ArrayList<ExpenseClass> expenseclass;

    public Expenses() {
        expenseclass = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.expense_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ExpenseAdapter adapter;

        adapter = new ExpenseAdapter(getActivity(), expenseclass);
        for (int x = 0; x < 10; x++) {
            ExpenseClass expenses = new ExpenseClass("Transport", "2000000");
            expenseclass.add(expenses);
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);
        FloatingActionButton expensefab = view.findViewById(R.id.expensefab);
        expensefab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InflateParams")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());/*
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.expense_alert, null));*/
                builder.setView(R.layout.expense_alert);
                builder.setTitle("Add Expense Details");
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.create();
                builder.show();
            }

        });

        return view;
    }

    /*Enable options menu in this fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //hide item (sort)
        menu.findItem(R.id.newproduct).setVisible(false);


        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.expenses) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are You sure you want to delete all expenses");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();

        }


        return super.onOptionsItemSelected(item);
    }

}