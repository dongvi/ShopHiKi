package com.example.shophiki.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.DetailProduct;
import com.example.shophiki.Main;
import com.example.shophiki.home.products.AdapterProductNormal;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.api.APIClient;
import com.example.shophiki.home.categaries_socua.AdapterCategory;
import com.example.shophiki.home.categaries_socua.IOnClickItemCategory;
import com.example.shophiki.R;
import com.example.shophiki.databinding.FragmentHomeBinding;
import com.example.shophiki.home.genuine_product.AdapterGP;
import com.example.shophiki.home.genuine_product.GP;
import com.example.shophiki.home.sale.AdapterSale;
import com.example.shophiki.home.trends.AdapterTrend;
import com.example.shophiki.home.trends.Trend;
import com.example.shophiki.home.utilities_category.AdapterUtiCategory;
import com.example.shophiki.home.utilities_category.ItemUtiCategory;
import com.example.shophiki.notificationdialog.ReminderLogin;
import com.example.shophiki.notificationdialog.SuccessfulDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    FragmentHomeBinding binding;
    List<Ads> imgADS;

    List<Product> productsSale;
    AdapterSale adapterSale;

    List<String> categories;
    AdapterCategory adapterCategory;

    List<Ads> adsGP;
    List<GP> gps;
    AdapterGP adapterGP;

    List<Trend> trends;
    AdapterTrend adapterTrend;

    List<ItemUtiCategory> utiCategory;
    AdapterUtiCategory adapterUtiCategory;

    List<Product> products;
    AdapterProductNormal adapterProductNormal;

    List<Product> topSale;

    Main main;

    public static FragmentHome newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        categories = new ArrayList<>();
        productsSale = new ArrayList<>();
        imgADS = new ArrayList<>();
        adsGP = new ArrayList<>();
        gps = new ArrayList<>();
        trends = new ArrayList<>();
        products = new ArrayList<>();
        utiCategory = new ArrayList<>();
        topSale = new ArrayList<>();

        binding.btnNotificationOn.setVisibility(View.GONE);

        getAPI();

        binding.viewFlipperHome.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bounce));
        binding.viewFlipperHome.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_down_out));
        previousView(binding.btnPreviousVf, binding.viewFlipperHome);
        nextView(binding.btnNextVf, binding.viewFlipperHome);

        binding.viewFlipperGP.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.scale_from_corner));
        binding.viewFlipperGP.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.scale_torwards_corner));
        previousView(binding.btnPreviousGp, binding.viewFlipperGP);
        nextView(binding.btnNextGp, binding.viewFlipperGP);


        categories.add(getResources().getString(R.string.tv_cgr_1));
        categories.add(getResources().getString(R.string.tv_cgr_2));
        categories.add(getResources().getString(R.string.tv_cgr_3));
        categories.add(getResources().getString(R.string.tv_cgr_4));
        categories.add(getResources().getString(R.string.tv_cgr_5));
        categories.add(getResources().getString(R.string.tv_cgr_6));
        categories.add(getResources().getString(R.string.tv_cgr_7));
        categories.add(getResources().getString(R.string.tv_cgr_8));
        categories.add(getResources().getString(R.string.tv_cgr_9));


        setRecycler1(binding.containerCategories);
        adapterCategory = new AdapterCategory(categories);
        binding.containerCategories.setAdapter(adapterCategory);


        utiCategory.add(new ItemUtiCategory(R.drawable.ic_coupons, getResources().getString(R.string.tv_uti_cgr_1)));
        utiCategory.add(new ItemUtiCategory(R.drawable.ic_gift, getResources().getString(R.string.tv_uti_cgr_2)));
        utiCategory.add(new ItemUtiCategory(R.drawable.ic_club, getResources().getString(R.string.tv_uti_cgr_3)));
        utiCategory.add(new ItemUtiCategory(R.drawable.ic_live, getResources().getString(R.string.tv_uti_cgr_4)));
        utiCategory.add(new ItemUtiCategory(R.drawable.ic_sale_50, getResources().getString(R.string.tv_uti_cgr_5)));

        setRecycler1(binding.containerUtiCategory);
        adapterUtiCategory = new AdapterUtiCategory(utiCategory);
        binding.containerUtiCategory.setAdapter(adapterUtiCategory);

        main = (Main) getActivity();

        binding.parentMenuHome.setVisibility(View.GONE);
        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.parentMenuHome.setVisibility(View.VISIBLE);

                binding.btnChangeLg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.parentMenuHome.setVisibility(View.GONE);
                    }
                });

                binding.btnChangeLogOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(main.isSignIn()) {
                            main.setSignIn(false);
                            binding.parentMenuHome.setVisibility(View.GONE);
                            SuccessfulDialog successfulLogout = new SuccessfulDialog(getString(R.string.tv_title_success_log_out), getString(R.string.tv_description_success_log_out));
                            successfulLogout.newInstance().show(getFragmentManager(), null);
                        }
                        else{
                            binding.parentMenuHome.setVisibility(View.GONE);
                            ReminderLogin.newInstance().show(getFragmentManager(), null);
                        }
                    }
                });

                binding.btnCloseMenuHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.parentMenuHome.setVisibility(View.GONE);
                    }
                });
            }
        });



        return binding.getRoot();
    }


    public void getAPI(){
        Call<List<Product>> callSale = APIClient.create().onGetProductSale();
        callSale.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                for (Product i : response.body()) {
                    if(i.getId() == 1 || i.getId() == 2 || i.getId() == 3)
                        topSale.add(i);
                    else
                        productsSale.add(i);
                }

                setRecycler1(binding.containerSale);
                adapterSale = new AdapterSale(productsSale);
                binding.containerSale.setAdapter(adapterSale);
                binding.containerSale.setNestedScrollingEnabled(false);

                Picasso.get().load(topSale.get(0).getPic()[0]).into(binding.imgPrTop1);
                Picasso.get().load(topSale.get(1).getPic()[0]).into(binding.imgPrTop2);
                Picasso.get().load(topSale.get(2).getPic()[0]).into(binding.imgPrTop3);

                adapterSale.setClick(new IOnClickProduct() {
                    @Override
                    public void onClick(Product product) {
                        FragmentManager fragmentManager = getFragmentManager();
                        DetailProduct detailProduct = new DetailProduct(product);
                        detailProduct.newInstance().show(fragmentManager, null);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        Call<List<Ads>> callAds = APIClient.create().onGetAds();
        callAds.enqueue(new Callback<List<Ads>>() {
            @Override
            public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                imgADS = response.body();
                setFlipper(imgADS, binding.viewFlipperHome);
            }

            @Override
            public void onFailure(Call<List<Ads>> call, Throwable t) {

            }

        });

        Call<List<Ads>> callAdsGP = APIClient.create().onGetAdsGP();
        callAdsGP.enqueue(new Callback<List<Ads>>() {
            @Override
            public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                adsGP = response.body();
                setFlipper(adsGP, binding.viewFlipperGP);
            }

            @Override
            public void onFailure(Call<List<Ads>> call, Throwable t) {

            }

        });



        Call<List<GP>> callGP = APIClient.create().onGetGP();
        callGP.enqueue(new Callback<List<GP>>() {
            @Override
            public void onResponse(Call<List<GP>> call, Response<List<GP>> response) {
                gps = response.body();
                setRecycler1(binding.containerGP);
                adapterGP = new AdapterGP(gps);
                binding.containerGP.setAdapter(adapterGP);
            }

            @Override
            public void onFailure(Call<List<GP>> call, Throwable t) {
            }
        });


        Call<List<Trend>> callTrend = APIClient.create().onGetTrend();
        callTrend.enqueue(new Callback<List<Trend>>() {
            @Override
            public void onResponse(Call<List<Trend>> call, Response<List<Trend>> response) {
                trends = response.body();
                setRecycler2(binding.containerTrends);
                adapterTrend = new AdapterTrend(trends);
                binding.containerTrends.setAdapter(adapterTrend);
            }

            @Override
            public void onFailure(Call<List<Trend>> call, Throwable t) {
            }
        });

        Call<List<Product>> callProduct = APIClient.create().onGetProduct();
        callProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                for(Product i : response.body()){
                    products.add(i);
                }

                RecyclerView.LayoutManager layoutPN = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
                binding.containerAllProduct.setLayoutManager(layoutPN);
                adapterProductNormal = new AdapterProductNormal(products);
                binding.containerAllProduct.setAdapter(adapterProductNormal);
                binding.containerAllProduct.setNestedScrollingEnabled(false);
                binding.containerAllProduct.setItemViewCacheSize(5);

                adapterProductNormal.setClick(new IOnClickProduct() {
                    @Override
                    public void onClick(Product product) {
                        FragmentManager fragmentManager = getFragmentManager();
                        DetailProduct detailProduct = new DetailProduct(product);
                        detailProduct.newInstance().show(fragmentManager, null);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });


    }


    public void previousView(View btn, ViewFlipper viewFlipper){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewFlipper.isAutoStart()) {
                    viewFlipper.stopFlipping();
                    viewFlipper.showPrevious();
                    viewFlipper.startFlipping();
                    viewFlipper.setAutoStart(true);
                }
            }
        });
    }

    public void nextView(View btn, ViewFlipper viewFlipper){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewFlipper.isAutoStart()) {
                    viewFlipper.stopFlipping();
                    viewFlipper.showNext();
                    viewFlipper.startFlipping();
                    viewFlipper.setAutoStart(true);
                }
            }
        });
    }



    public void setRecycler1(RecyclerView view){
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        view.setLayoutManager(layout);
        view.setHasFixedSize(true);
    }

    public void setRecycler2(RecyclerView view){
        RecyclerView.LayoutManager layout = new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(layout);
    }

    public void setFlipper(List<Ads> img, ViewFlipper flipper){
        for(Ads pic : img){
            ImageView view = new ImageView(getContext());
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.get().load(pic.getUrl()).into(view);
            flipper.addView(view);
        }
    }
}
