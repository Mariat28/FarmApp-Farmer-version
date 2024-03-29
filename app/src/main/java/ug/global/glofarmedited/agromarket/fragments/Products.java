package ug.global.glofarmedited.agromarket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Objects;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.AddNewProduct;
import ug.global.glofarmedited.agromarket.adapterobjects.ProductObjects;
import ug.global.glofarmedited.agromarket.adapters.ProductsAdapter;

public class Products extends Fragment {
    private ArrayList<ProductObjects> productObjectsArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlertDialog alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        FloatingActionButton addproduct = view.findViewById(R.id.addproduct);
        recyclerView = view.findViewById(R.id.productsrecyclerview);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ProductsAdapter productsAdapter = new ProductsAdapter(productObjectsArrayList, getActivity());
        final String key = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        // final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        DatabaseReference product_reference = FirebaseDatabase.getInstance().getReference("/products");

        Query query = product_reference.orderByChild("userid").equalTo(key);


        query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {

                    ProductObjects products = dataSnapshot.getValue(ProductObjects.class);
                    assert products != null;
                    String name = products.getProductname();
                    int description = products.getProductavailability();
                    String price = products.getProductprice();
                    String unit = products.getProductunit();

                    ProductObjects productObjects = new ProductObjects(name, description, price, unit);
                    productObjectsArrayList.add(productObjects);
                    productsAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(productsAdapter);

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddNewProduct.class));
            }
        });
        return view;
    }


    }


