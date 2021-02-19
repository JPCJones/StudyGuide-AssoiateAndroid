package com.example.studyguide_assoiateandroid.UserInterface.MaterialComponentSHRINE;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyguide_assoiateandroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProductGridFragment extends Fragment {
    private static final String TAG = "ProductGridFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ***************** enable the app bar **********************
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_page_fragment, container, false);
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "bag", "a thing you carry", 12, "http://via.placeholder.com/300.png"));
        products.add(new Product(2, "jacket", "a thing you ware", 20, "https://images-dynamic-arcteryx.imgix.net/S21/450x625/Rico-Jacket-Dracaena.png?auto=format"));
        products.add(new Product(3, "not a bag", "a thing you carry", 12, "https://fossil.scene7.com/is/image/FossilPartners/ZB7957200_main?$sfcc_fos_hi-res$"));
        products.add(new Product(4, "defiantly not a bag", "a thing you dont carry", 300, "https://fossil.scene7.com/is/image/FossilPartners/ZB7957200_main?$sfcc_fos_hi-res$"));
        products.add(new Product(1, "bag", "a thing you carry", 12, "http://via.placeholder.com/300.png"));
        products.add(new Product(2, "jacket", "a thing you ware", 20, "https://images-dynamic-arcteryx.imgix.net/S21/450x625/Rico-Jacket-Dracaena.png?auto=format"));
        products.add(new Product(3, "not a bag", "a thing you carry", 12, "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8aHVtYW58ZW58MHx8MHw%3D&ixlib=rb-1.2.1&w=1000&q=80"));
        products.add(new Product(4, "defiantly not a bag", "a thing you dont carry", 300, "https://fossil.scene7.com/is/image/FossilPartners/ZB7957200_main?$sfcc_fos_hi-res$"));

        // Set up the toolbar
        setUpToolbar(view);
        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.product_recycler_view);
        recyclerView.setHasFixedSize(true);
        // grid layout for recycler
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(getContext());
        Log.d(TAG, "onCreateView: in here");
        adapter.setProducts(products);
        recyclerView.setAdapter(adapter);

//        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
       // recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        // background code **************************
        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           NestedScrollView nestedScrollView = view.findViewById(R.id.shrine_product_grid);
           nestedScrollView.setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        final boolean[] isCollapsed = {true};
        NestedScrollView nestedScrollView = view.findViewById(R.id.shrine_product_grid);
        float initialHeight = nestedScrollView.getY() + 180;
        // ***************** get size of screen *********************
        WindowMetrics displayMetrics = ((Activity) getContext()).getWindowManager().getCurrentWindowMetrics();
        int screenHeight = displayMetrics.getBounds().height();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateMenu(nestedScrollView, screenHeight, initialHeight);
//                if(isCollapsed[0]){
//                    nestedScrollView.setY((screenHeight - 750));
//                    isCollapsed[0] = false;
//                }else{
//                    nestedScrollView.setY(initialHeight);
//                    isCollapsed[0] = true;
//                }

            }
        });
    }


    private final AnimatorSet animatorSet = new AnimatorSet();
    private Context context;
    private View sheet;
    private Interpolator interpolator;
    private int height;
    private boolean backdropShown = false;

    private void animateMenu(View view, int screenHeight, float initialHeight){

        interpolator = new AccelerateDecelerateInterpolator();
        context = getContext();
        backdropShown = !backdropShown;

        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        final int translateY = (int)(screenHeight * .6);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", backdropShown ? translateY : 0);
        animator.setDuration(500);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animatorSet.play(animator);
        animator.start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_item:
                Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_item:
                Toast.makeText(getContext(), "settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       inflater.inflate(R.menu.shine_menu, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }
}
