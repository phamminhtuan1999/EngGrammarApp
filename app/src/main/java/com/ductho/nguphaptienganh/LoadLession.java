package com.ductho.nguphaptienganh;


import com.ductho.nguphaptienganh.Lesson.Action;
import com.ductho.nguphaptienganh.Lesson.An;
import com.ductho.nguphaptienganh.Lesson.ChaoHoi;
import com.ductho.nguphaptienganh.Lesson.ChoAnO;
import com.ductho.nguphaptienganh.Lesson.ConSoVaTienBac;
import com.ductho.nguphaptienganh.Lesson.DiaDiem;
import com.ductho.nguphaptienganh.Lesson.DienThoaiThuInternet;
import com.ductho.nguphaptienganh.Lesson.DuLichPhuongHuong;
import com.ductho.nguphaptienganh.Lesson.GiaiTri;
import com.ductho.nguphaptienganh.Lesson.KetBan;
import com.ductho.nguphaptienganh.Lesson.KhoKhanGiaoTiep;
import com.ductho.nguphaptienganh.Lesson.MuaSam;
import com.ductho.nguphaptienganh.Lesson.NhungThanhNguThongDung;
import com.ductho.nguphaptienganh.Lesson.ThoiGianVaNgayThang;
import com.ductho.nguphaptienganh.Lesson.ThoiTiet;
import com.ductho.nguphaptienganh.Lesson.TrangTu.NhungCauHoiThongThuong;
import com.ductho.nguphaptienganh.Lesson.TruongHopKhanCapVaSucKhoe;
import com.ductho.nguphaptienganh.Lesson.TuVungVaThanhNguVanHoa;
import com.ductho.nguphaptienganh.Lesson.ViecLam;
import com.ductho.nguphaptienganh.Model.ItemLession;

import java.util.ArrayList;

// sử dụng cho màn hình main
public class LoadLession {
    int id;
    ArrayList<ItemLession> arrayList;

    public LoadLession(int id, ArrayList<ItemLession> arrayList) {
        this.id = id;
        this.arrayList = arrayList;
        if(id == 0){
            new TruongHopKhanCapVaSucKhoe(arrayList);
        }
        if(id == 1){
            new TuVungVaThanhNguVanHoa(arrayList);
        }
        if(id == 2){
            new NhungCauHoiThongThuong(arrayList);
        }
        if(id == 3){
            new ViecLam(arrayList);
        }
        if(id == 4){
            new ThoiTiet(arrayList);
        }
        if(id == 5){
            new KhoKhanGiaoTiep(arrayList);
        }
        if(id == 6){
            new MuaSam(arrayList);
        }
        if(id == 7){
            new GiaiTri(arrayList);
        }
        if(id == 8){
            new KetBan(arrayList);
        }
        if(id == 9){
            new An(arrayList);
        }
        if(id == 10){
            new ChoAnO(arrayList);
        }
        if(id == 11){
            new ThoiGianVaNgayThang(arrayList);
        }
        if(id == 12){
            new DienThoaiThuInternet(arrayList);
        }
        if(id == 13){
            new DiaDiem(arrayList);
        }
        if(id == 14){
            new ConSoVaTienBac(arrayList);
        }
        if(id == 15){
            new DuLichPhuongHuong(arrayList);
        }
        if(id == 16){
            new ChaoHoi(arrayList);
        }
        if(id == 17){
            new NhungThanhNguThongDung(arrayList);
        }
    }
}
