package com.example.shophiki;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.api.APIClient;
import com.example.shophiki.databinding.DetailProductBinding;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.notificationdialog.ErrorDialog;
import com.example.shophiki.notificationdialog.SuccessfulDialog;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends DialogFragment {

    DetailProductBinding binding;
    Product product;
    DecimalFormat format = new DecimalFormat("0.###");
    int i = 0;
    int sl = 3;
    Main main;

    List<Review> reviews;
    AdapterReview adapterReview;

    public DetailProduct(Product product) {
        this.product = product;
    }

    public DetailProduct newInstance() {

        Bundle args = new Bundle();

        DetailProduct fragment = new DetailProduct(product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_product, container, false);
        main = (Main) getActivity();

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        for(String i : product.getPic()) {
            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.get().load(i).into(img);

            binding.flipperPro.addView(img);
        }
        binding.flipperPro.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_2));
        binding.flipperPro.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_2));
        binding.btnNextVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.flipperPro.showNext();
            }
        });
        binding.btnPreviousVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.flipperPro.showPrevious();
            }
        });

        binding.tvNameOfPro.setText(product.getName());


        if(product.getCurrentAmount() != 0) {
            int sold = product.getOriginalAmount() - product.getCurrentAmount();
            binding.tvSold.setText(R.string.tv_sold);
            if (sold == 0) {
                binding.tvSold.setText(binding.tvSold.getText().toString() + "0");
            } else {
                if (sold < 10000)
                    binding.tvSold.setText(binding.tvSold.getText().toString() + String.valueOf(product.getOriginalAmount() - product.getCurrentAmount()));
                else if (sold < 1000000) {
                    binding.tvSold.setText(binding.tvSold.getText().toString() + String.valueOf(sold / 1000) + "N+");
                } else if (sold < 1000000000) {
                    binding.tvSold.setText(binding.tvSold.getText().toString() + String.format("%.1f", sold / 1000000.0) + "Tr+");
                } else {
                    binding.tvSold.setText(binding.tvSold.getText().toString() + String.format("%.1f", sold / 1000000000.0) + "T+");
                }
            }
        }
        else{
            binding.tvSold.setText(R.string.tv_out_of_stock);
        }
        if(product.getSale() == 0) {
            binding.tvIsSale.setVisibility(View.GONE);
            binding.tvOriPrice.setText(String.format("%,.0f",product.getPrice()) + " ₫");
        }
        else {
            binding.tvIsSale.setVisibility(View.VISIBLE);
            binding.tvSaleDp.setText(format.format(product.getSale()) + "%");
            binding.tvOriPrice.setText(String.format("%,.0f",product.getPrice()) + " ₫");
            binding.tvOriPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            double price = Double.parseDouble(format.format(product.getPrice() - product.getPrice() * product.getSale()/100));
            binding.tvPriceSaleDp.setText(String.format("%,.0f", price) + " ₫");
        }

        if(product.getVoteStar() == 0) {
            binding.detailVote.setVisibility(View.GONE);
            binding.tvDetailVote.setText(R.string.tv_vote);
            binding.tvAmountVote.setVisibility(View.GONE);
            binding.layoutReviews.setVisibility(View.GONE);
        }
        else {
            binding.detailVote.setVisibility(View.VISIBLE);
            binding.tvAmountVote.setVisibility(View.VISIBLE);
            binding.tvAmountVote.setText("(10)");
            binding.detailVote.setRating(Float.parseFloat(String.valueOf(product.getVoteStar())));
            binding.layoutReviews.setVisibility(View.VISIBLE);
        }

        if(product.getBrand() == null){
            binding.parentBrandDp.setVisibility(View.GONE);
            binding.tvBrand.setText(getString(R.string.tv_unknown));
        }
        else if(Pattern.matches("[A-Za-z0-9,.\\p{L} -]+", product.getBrand())){
            binding.parentBrandDp.setVisibility(View.GONE);
            binding.tvBrand.setText(product.getBrand());
        }
        else {
            binding.tvBrand.setVisibility(View.GONE);
            binding.parentBrandDp.setVisibility(View.VISIBLE);
            Picasso.get().load(product.getBrand()).into(binding.imgBrandDp);
        }

        if(product.getOrigin() == null)
            binding.tvOriginDp.setText(getString(R.string.tv_unknown));
        else
            binding.tvOriginDp.setText(product.getOrigin());

        if(product.getYear() == null)
            binding.tvYearDp.setText(getString(R.string.tv_unknown));
        else
            binding.tvYearDp.setText(product.getYear());

        if(product.getWarranty() == null)
            binding.tvWarranty.setText(getString(R.string.tv_unknown));
        else
            binding.tvWarranty.setText(product.getWarranty());

        if(product.getDetailInfo() == null){
            binding.tvProDesDp.setText(getString(R.string.tv_unknown));
        }
        else {
            binding.tvProDesDp.setText(product.getDetailInfo());
        }
        binding.tvDetailVote2.setText(String.format("%.1f", product.getVoteStar()));
        binding.detailVote2.setRating(Float.parseFloat(String.valueOf(product.getVoteStar())));
        binding.tvAmountReviews.setText("10" + getString(R.string.tv_amount_reviews));

        binding.icMore.setVisibility(View.VISIBLE);
        binding.icLess.setVisibility(View.GONE);
        binding.detailVoteTotal.setVisibility(View.GONE);

        binding.btnDetailVoteSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 1){
                    binding.detailVoteTotal.setVisibility(View.GONE);
                    binding.icMore.setVisibility(View.VISIBLE);
                    binding.icLess.setVisibility(View.GONE);
                    i = 0;
                }
                else {
                    binding.detailVoteTotal.setVisibility(View.VISIBLE);
                    binding.icMore.setVisibility(View.GONE);
                    binding.icLess.setVisibility(View.VISIBLE);
                    i = 1;
                }
            }
        });

        reviews = new ArrayList<>();
        getAPI();
        binding.btnMoreReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl+=3;
                getAPI();
            }
        });

        binding.btnHideReview.setVisibility(View.GONE);

        binding.btnHideReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sl = 3;
                getAPI();
                binding.btnHideReview.setVisibility(View.GONE);
            }
        });

        binding.btnAddPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelected = false;
                FragmentManager fragmentManager = getFragmentManager();
                for(Product j : main.getProductsSelected())
                    if(j.getId() == product.getId()) {
                        isSelected = true;
                        break;
                    }

                if(!isSelected){
                    SuccessfulDialog addSuccess = new SuccessfulDialog(getString(R.string.tv_title_add_success), getString(R.string.tv_description_add_success));
                    addSuccess.newInstance().show(fragmentManager, null);
                    product.setAmount(1);
                    main.getProductsSelected().add(product);
                }
                else {
                    ErrorDialog addError = new ErrorDialog(getString(R.string.tv_title_add_error), getString(R.string.tv_description_add_error));
                    addError.newInstance().show(fragmentManager, null);
                }
            }
        });

        binding.btnCart.setVisibility(View.GONE);

        return binding.getRoot();
    }


    public void getAPI(){
        Call<List<Review>> callRv = APIClient.create().onGetReview();
        callRv.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                reviews = response.body();

                List<Review> x = new ArrayList<>();

                for(int j = 0; j < sl; j++)
                    if(j < reviews.size())
                        x.add(reviews.get(j));

                binding.containerReviews.setNestedScrollingEnabled(false);
                adapterReview = new AdapterReview(x);
                RecyclerView.LayoutManager layoutReview = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                binding.containerReviews.setLayoutManager(layoutReview);
                binding.containerReviews.setAdapter(adapterReview);
                binding.containerReviews.setItemViewCacheSize(3);

                if(reviews.size() < 4){
                    binding.btnMoreReview.setVisibility(View.GONE);
                }
                else if (x.size() == reviews.size()){
                    binding.btnMoreReview.setVisibility(View.GONE);
                    binding.btnHideReview.setVisibility(View.VISIBLE);
                }
                else {
                    binding.btnMoreReview.setVisibility(View.VISIBLE);
                    binding.btnHideReview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
            }
        });
    }
}
