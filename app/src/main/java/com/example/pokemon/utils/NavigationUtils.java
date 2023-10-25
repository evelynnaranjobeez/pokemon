package com.example.pokemon.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class NavigationUtils {

    public static void setFragment(Fragment fragment, FragmentActivity context, int container) {
        FragmentTransaction t = context.getSupportFragmentManager().beginTransaction();
        t.replace(container, fragment);
        t.commit();
    }
}
