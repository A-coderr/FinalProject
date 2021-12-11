package com.example.ak_lg_sa_finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This class is used to implement fragment_about layout
 * @author Sara Asefi
 * @version 1.0
 */
public class AboutFragment extends Fragment {

    /**
     * this method will inflate fragment_about layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return inflater
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}