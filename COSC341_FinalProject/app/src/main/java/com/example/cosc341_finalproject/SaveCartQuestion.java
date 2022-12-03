package com.example.cosc341_finalproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaveCartQuestion#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SaveCartQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaveCartQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static SaveCartQuestion newInstance(String param1, String param2) {
        SaveCartQuestion fragment = new SaveCartQuestion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SaveCartQuestion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_save_cart_question, container, false);
        Button CancelBut = (Button) v.findViewById(R.id.CancelBut);
        Button NoBut = (Button) v.findViewById(R.id.NoBut);
        Button YesBut = (Button) v.findViewById(R.id.YesBut);
        CancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }


            });
        NoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.newcart_items = new ArrayList<>(); //clear user cart
                Intent intent = new Intent(view.getContext().getApplicationContext(), ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }


        });
        YesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.SavedCarts.add(new SavedStoreCarts(Global.newcart_items)); //save the cart
                Global.newcart_items = new ArrayList<>(); //clear user cart
                Intent intent = new Intent(view.getContext().getApplicationContext(), ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }


        });
        return v;

    }}


