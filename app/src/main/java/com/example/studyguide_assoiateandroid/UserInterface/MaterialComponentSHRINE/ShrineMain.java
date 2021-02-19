package com.example.studyguide_assoiateandroid.UserInterface.MaterialComponentSHRINE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studyguide_assoiateandroid.R;

public class ShrineMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrine_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.shrine_container, new LoginFragment())
                    .commit();
        }
    }


//    /**
//     * Navigate to the given fragment.
//     *
//     * @param fragment       Fragment to navigate to.
//     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
//     */
//    @Override
//    public void navigateTo(Fragment fragment, boolean addToBackstack) {
//        FragmentTransaction transaction =
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, fragment);
//
//        if (addToBackstack) {
//            transaction.addToBackStack(null);
//        }
//
//        transaction.commit();
//    }
}