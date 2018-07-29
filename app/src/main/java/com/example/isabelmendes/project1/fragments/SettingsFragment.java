package com.example.isabelmendes.project1.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.isabelmendes.project1.R;

public class SettingsFragment extends PreferenceFragment {
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preferences);

        getActivity().setTitle("Configurações");
    }
}
