package com.example.shophiki.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.shophiki.R;
import com.example.shophiki.databinding.FragmentOrderBinding;
import com.example.shophiki.notificationdialog.SuccessfulDialog;

import java.util.ArrayList;
import java.util.List;

public class FragmentVerification extends DialogFragment {

    FragmentOrderBinding binding;
    AdapterProvince adapterAddress;
    List<Province> addresses;
    boolean ok;
    FragmentCart fragmentCart;

    public FragmentVerification(FragmentCart fragmentCart) {
        this.fragmentCart = fragmentCart;
    }

    public FragmentVerification newInstance() {

        Bundle args = new Bundle();

        FragmentVerification fragment = new FragmentVerification(fragmentCart);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        addresses = new ArrayList<>();

        String[] t_SC_TN = {"Phường Lương Châu", "Phường Mỏ Chè", "Phường Cải Đan", "Phường Thắng Lợi",
                "Phường Phố Cò", "Phường Bách Quang", "Phường Lương Sơn", "Xã Vinh Sơn", "Xã Tân Quang", "Xã Bình Sơn", "Xã Bá Xuyên"};


        String[] t_TN_TN = {"Phường Cam Giá", "Phường Chùa Hang", "Phường Đồng Bẩm", "Phường Đồng Quang", "Phường Gia Sàng",
                "Phường Hoàng Văn Thụ", "Phường Hương Sơn", "Phường Phan Đình Phùng", "Phường Phú Xá", "Phường Quan Triều", "Phường Quang Trung",
                "Phường Quang Vinh", "Phường Tân Lập", "Phường Tân Long", "Phường Tân Thành", "Phường Tân Thịnh", "Phường Thịnh Đán",
                "Phường Tích Lương", "Phường Trung Thành", "Phường Trưng Vương", "Phường Túc Duyên", "Xã Quyết Thắng", "Xã Cao Ngạn",
                "Xã Đồng Liên", "Xã Huống Thượng", "Xã Linh Sơn", "Xã Phúc Hà", "Xã Phúc Trìu", "Xã Phúc Xuân", "Xã Sơn Cẩm", "Xã Tân Cương",
                "Xã Thịnh Đức"};

        String[] t_PY_TN = {"Phường Ba Hàng", "Phường Bãi Bông", "Phường Bắc Sơn", "Phường Đồng Tiến", "Xã Đắc Sơn", "Xã Đông Cao",
                "Xã Hồng Tiến", "Xã Minh Đức", "Xã Nam Tiến", "Xã Phúc Thuận", "Xã Phúc Tân", "Xã Tân Hương", "Xã Tân Phú", "Xã Thành Công",
                "Xã Thuận Thành", "Xã Tiên Phong", "Xã Trung Thành", "Xã Vạn Phái"};



        String[] t_DT_TN = {"Thị trấn Hùng Sơn (huyện lỵ)", "Thị trấn Quân Chu", "Xã An Khánh", "Xã Bản Ngoại", "Xã Bình Thuận", "Xã Cát Nê",
                "Xã Cù Vân", "Xã Đức Lương", "Xã Hà Thượng", "Xã Hoàng Nông", "Xã Khôi Kỳ", "Xã Ký Phú", "Xã La Bằng", "Xã Lục Ba", "Xã Minh Tiến",
                "Xã Mỹ Yên", "Xã Na Mao", "Xã Phú Cường", "Xã Phú Lạc", "Xã Phú Thịnh", "Xã Phú Xuyên", "Xã Phúc Lương", "Xã Phục Linh", "Xã Quân Chu",
                "Xã Tân Linh", "Xã Tân Thái", "Xã Tiên Hội", "Xã Vạn Thọ", "Xã Văn Yên", "Xã Yên Lãng"};



        String[] t_DH_TN = {"Thị trấn Chợ Chu", "Xã Bảo Cường", "Xã Bảo Linh", "Xã Bình Thành", "Xã Bình Yên", "Xã Bộc Nhiêu", "Xã Điềm Mặc",
                "Xã Định Biên", "Xã Đồng Thịnh", "Xã Kim Phượng", "Xã Lam Vỹ", "Xã Linh Thông", "Xã Phú Đình", "Xã Phú Tiến", "Xã Phúc Chu",
                "Xã Phượng Tiến", "Xã Quy Kỳ", "Xã Sơn Phú", "Xã Tân Dương", "Xã Tân Thịnh", "Xã Thanh Định", "Xã Trung Hội", "Xã Trung Lương"};

        String[] t_DHy_TN = {"Thị trấn Chùa Hang", "Thị trấn Sông Cầu", "Thị trấn Trại Cau", "Xã Cây Thị", "Xã Hoà Bình", "Xã Hoá Thượng",
                "Xã Hoá Trung", "Xã Hợp Tiến", "Xã Huống Thượng", "Xã Khe Mo", "Xã Linh Sơn", "Xã Minh Lập", "Xã Nam Hoà", "Xã Quang Sơn",
                "Xã Tân Lợi", "Xã Tân Long", "Xã Văn Hán", "Xã Văn Lăng"};

        String[] t_PB_TN = {"Thị trấn Hương Sơn", "Xã Bàn Đạt", "Xã Bảo Lý", "Xã Dương Thành", "Xã Đào Xá", "Xã Điềm Thụy", "Xã Hà Châu",
                "Xã Kha Sơn", "Xã Lương Phú", "Xã Nga My", "Xã Nhã Lộng", "Xã Tân Đức", "Xã Tân Hòa", "Xã Tân Khánh", "Xã Tân Kim", "Xã Tân Thành",
                "Xã Thanh Ninh", "Xã Thượng Đình", "Xã Úc Kỳ", "Xã Xuân Phương"};

        String[] t_PL_TN = {"Thị trấn Đu", "Thị trấn Giang Tiên", "Xã Cổ Lũng", "Xã Động Đạt", "Xã Hợp Thành", "Xã Ôn Lương", "Xã Phấn Mễ",
                "Xã Phú Đô", "Xã Phủ Lý", "Xã Sơn Cẩm", "Xã Tức Tranh", "Xã Vô Tranh", "Xã Yên Đổ", "Xã Yên Lạc", "Xã Yên Ninh", "Xã Yên Trạch"};


        String[] t_VN_TN = {"Thị trấn Đình Cả", "Xã Tràng Xá", "Xã Liên Minh", "Xã Phương Giao", "Xã Dân Tiến", "Xã Bình Long", "Xã Lâu Thượng",
                "Xã Phú Thượng", "Xã La Hiên", "Xã Cúc Đường", "Xã Vũ Chấn", "Xã Nghinh Tường", "Xã Sảng Mộc", "Xã Thượng Nung", "Xã Thần Sa"};

        District[] dtThaiNguyen = {
                new District("Thành phố Sông Công", t_SC_TN),
                new District("Thành phố Thái Nguyên", t_TN_TN),
                new District("Thị xã Phổ Yên", t_PY_TN),
                new District("Huyện Đại Từ", t_DT_TN),
                new District("Huyện Định Hóa", t_DHy_TN),
                new District("Huyện Đồng Hỷ", t_DHy_TN),
                new District("Huyện Phú Bình", t_PB_TN),
                new District("Huyện Phú Lương", t_PL_TN),
                new District("Huyện Võ Nhai", t_VN_TN)};

        addresses.add(new Province("Thái Nguyên", dtThaiNguyen));

        String[] t_1 = {"1","2","3"};
        String[] t_2 = {"4","5","6"};
        String[] t_3 = {"7","8","9"};

        District[] dtHanoi = {new District("a", t_1), new District("b", t_2), new District("c", t_3)};

        addresses.add(new Province("Hà Nội", dtHanoi));


        binding.tvDistrictRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragAddress();
            }
        });

        binding.tvProvinceRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragAddress();
            }
        });

        binding.tvTownRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragAddress();
            }
        });

        btnOrder();

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ok) {
                    FragmentManager fragmentManager = getFragmentManager();
                    SuccessfulDialog successfulDialog = new SuccessfulDialog(getString(R.string.tv_title_order_successful), getString(R.string.tv_description_order_successful));
                    successfulDialog.newInstance().show(fragmentManager, null);
                    dismiss();
                    fragmentCart.removeAllPro();
                    fragmentCart.dismiss();
                }
            }
        });

        return binding.getRoot();
    }

    public void fragAddress(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentAddress fragmentAddress = new FragmentAddress(addresses, this);
        fragmentAddress.newInstance().show(fragmentManager, null);
    }

    public void setProvince(String name){
        binding.tvProvinceRec.setText(name);
    }

    public void setDistrict(String name){
        binding.tvDistrictRec.setText(name);
    }

    public void setTown(String name){
        binding.tvTownRec.setText(name);
    }

    public void btnOrder(){
        if(binding.etNameRec.length() != 0 &&
                binding.etPhoneRec.length() != 0 &&
                binding.etAddressRec.length() != 0 &&
                binding.tvProvinceRec.length() != 0 &&
                binding.tvDistrictRec.length() != 0 &&
                binding.tvTownRec.length() != 0) {
            ok = true;
            binding.btnConfirm.setBackgroundResource(R.drawable.button);
        }
        else {
            ok = false;
            binding.btnConfirm.setBackgroundResource(R.drawable.button_off);
        }
    }

    public void resetData(){
        binding.tvProvinceRec.setText("");
        binding.tvDistrictRec.setText("");
        binding.tvTownRec.setText("");
    }
}
