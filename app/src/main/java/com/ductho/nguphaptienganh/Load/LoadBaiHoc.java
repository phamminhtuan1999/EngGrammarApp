package com.ductho.nguphaptienganh.Load;

import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauBiDong;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauDieuKien;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauHoiDuoi;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauSoSanh;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauUoc;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCongThucVietLaiCau;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemDaiTuQuanHe;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemDanhTu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemDongTu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemGioiTu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemThi;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemTinhTu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemTrangTu;
import com.ductho.nguphaptienganh.Model.BaiHoc;

import java.util.ArrayList;

public class LoadBaiHoc {
    String id;
    ArrayList<BaiHoc> arrayList;

    public LoadBaiHoc(String id, ArrayList<BaiHoc> arrayList) {
        this.id = id;
        this.arrayList = arrayList;
        load();
    }

    private void load(){
        if(id.equals("1")){
            new ThemThi(arrayList);
        }
        if(id.equals("2")){
            new ThemCauBiDong(arrayList);
        }
        if(id.equals("3")){
            new ThemCauUoc(arrayList);
        }
        if(id.equals("5")){
            new ThemCauDieuKien(arrayList);
        }

        if(id.equals("6")){
            new ThemCauSoSanh(arrayList);
        }
        if(id.equals("7")){
            new ThemDaiTuQuanHe(arrayList);
        }
        if(id.equals("9")){
            new ThemCauHoiDuoi(arrayList);
        }
        if(id.equals("13")){
            new ThemCongThucVietLaiCau(arrayList);
        }
        if(id.equals("17")){
            new ThemDanhTu(arrayList);
        }
        if(id.equals("18")){
            new ThemDongTu(arrayList);
        }
        if(id.equals("19")){
            new ThemTinhTu(arrayList);
        }
        if(id.equals("20")){
            new ThemTrangTu(arrayList);
        }
        if(id.equals("21")){
            new ThemGioiTu(arrayList);
        }

    }
}
