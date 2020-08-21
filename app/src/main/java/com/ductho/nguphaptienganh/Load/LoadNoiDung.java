package com.ductho.nguphaptienganh.Load;

import android.webkit.WebView;

import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauCamThan;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauDaoNgu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauDongTinh;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauGianTiep;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauMenhLenh;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemCauNhanManh;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemDongTuBatQuyTac;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemPhatAm_ed;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemPhatAm_s_es;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemQuyTacTrongAm;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemThanhNgu;
import com.ductho.nguphaptienganh.CacBaiHoc.ThemVitriTinhTuDanhTu;
import com.ductho.nguphaptienganh.Lesson.CauBiDong.CauTrucCauBiDong;
import com.ductho.nguphaptienganh.Lesson.CauBiDong.TruongHopDacBietCauBiDong;
import com.ductho.nguphaptienganh.Lesson.CauDieuKien.CauDieuKienDacBiet;
import com.ductho.nguphaptienganh.Lesson.CauDieuKien.CauDieuKienDaoNgu;
import com.ductho.nguphaptienganh.Lesson.CauDieuKien.CauDieuKienL1;
import com.ductho.nguphaptienganh.Lesson.CauDieuKien.CauDieuKienL2;
import com.ductho.nguphaptienganh.Lesson.CauDieuKien.CauDieuKienL3;
import com.ductho.nguphaptienganh.Lesson.CauHoiDuoi.CacDangDacBietCauHoiDuoi;
import com.ductho.nguphaptienganh.Lesson.CauHoiDuoi.CongThucCauHoiDuoi;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.BangSoSanh;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.SoSanhBang;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.SoSanhHon;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.SoSanhHonNhat;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.SoSanhHonNhieuLan;
import com.ductho.nguphaptienganh.Lesson.CauSoSanh.SoSanhKep;
import com.ductho.nguphaptienganh.Lesson.CauUoc.CauUocLoai1;
import com.ductho.nguphaptienganh.Lesson.CauUoc.CauUocLoai2;
import com.ductho.nguphaptienganh.Lesson.CauUoc.CauUocLoai3;
import com.ductho.nguphaptienganh.Lesson.CongThucVietLaiCau.Phan1;
import com.ductho.nguphaptienganh.Lesson.CongThucVietLaiCau.Phan2;
import com.ductho.nguphaptienganh.Lesson.CongThucVietLaiCau.Phan3;
import com.ductho.nguphaptienganh.Lesson.CongThucVietLaiCau.Phan4;
import com.ductho.nguphaptienganh.Lesson.DanhTu.CacLoaiDanhTu;
import com.ductho.nguphaptienganh.Lesson.DanhTu.DanhTuBatQuyTac;
import com.ductho.nguphaptienganh.Lesson.DanhTu.DanhTuDemDuocVaKhongDemDuoc;
import com.ductho.nguphaptienganh.Lesson.DanhTu.DanhTuSoItSoNhieu;
import com.ductho.nguphaptienganh.Lesson.DongTu.DongTuThieuKhuyet;
import com.ductho.nguphaptienganh.Lesson.DongTu.NoiDongTuVaNgoaiDongTu;
import com.ductho.nguphaptienganh.Lesson.GioiTu.CacLoaiGioiTu;
import com.ductho.nguphaptienganh.Lesson.GioiTu.CachDungGioiTu;
import com.ductho.nguphaptienganh.Lesson.MenhDeQuanHe.DaiTuTrangTuQuanHe;
import com.ductho.nguphaptienganh.Lesson.MenhDeQuanHe.RutGonMenhDeQuanHe;
import com.ductho.nguphaptienganh.Lesson.Thi.HienTaiDon;
import com.ductho.nguphaptienganh.Lesson.Thi.HienTaiHoanThanh;
import com.ductho.nguphaptienganh.Lesson.Thi.HienTaiHoanThanhTiepDien;
import com.ductho.nguphaptienganh.Lesson.Thi.HienTaiTiepDien;
import com.ductho.nguphaptienganh.Lesson.Thi.QuaKhuDon;
import com.ductho.nguphaptienganh.Lesson.Thi.QuaKhuHoanThanh;
import com.ductho.nguphaptienganh.Lesson.Thi.QuaKhuHoanThanhTiepDien;
import com.ductho.nguphaptienganh.Lesson.Thi.QuaKhuTiepDien;
import com.ductho.nguphaptienganh.Lesson.Thi.TuongLaiDon;
import com.ductho.nguphaptienganh.Lesson.Thi.TuongLaiHoanThanh;
import com.ductho.nguphaptienganh.Lesson.Thi.TuongLaiHoanThanhTiepDien;
import com.ductho.nguphaptienganh.Lesson.Thi.TuongLaiTiepDien;
import com.ductho.nguphaptienganh.Lesson.TinhTu.CacTinhTuThuongGap;
import com.ductho.nguphaptienganh.Lesson.TinhTu.CauTrucTratTuTinhTu;
import com.ductho.nguphaptienganh.Lesson.TinhTu.TinhTuKep;
import com.ductho.nguphaptienganh.Lesson.TinhTu.TinhTuTanCung;
import com.ductho.nguphaptienganh.Lesson.TinhTu.ViTriTinhTu;
import com.ductho.nguphaptienganh.Lesson.TrangTu.CacLoaiTrangTu;
import com.ductho.nguphaptienganh.Lesson.TrangTu.CacTrangTuThuongGap;
import com.ductho.nguphaptienganh.Lesson.TrangTu.PhanLoaiTrangTu;
import com.ductho.nguphaptienganh.Lesson.TrangTu.ViTriTrangTu;

public class LoadNoiDung {
    String id;
    WebView webView;

    public LoadNoiDung(String id, WebView webView) {
        this.id = id;
        this.webView = webView;
        load();
    }

    private void load(){
        if(id.equals("1.1")){
            new HienTaiDon(webView);
        }

        if(id.equals("1.2")){
            new HienTaiTiepDien(webView);
        }

        if(id.equals("1.3")){
            new HienTaiHoanThanh(webView);
        }
        if(id.equals("1.4")){
            new HienTaiHoanThanhTiepDien(webView);
        }
        if(id.equals("1.5")){
            new QuaKhuDon(webView);
        }
        if(id.equals("1.6")){
            new QuaKhuTiepDien(webView);
        }
        if(id.equals("1.7")){
            new QuaKhuHoanThanh(webView);
        }
        if(id.equals("1.8")){
            new QuaKhuHoanThanhTiepDien(webView);
        }
        if(id.equals("1.9")){
            new TuongLaiDon(webView);
        }
        if(id.equals("1.10")){
            new TuongLaiTiepDien(webView);
        }
        if(id.equals("1.11")){
            new TuongLaiHoanThanh(webView);
        }
        if(id.equals("1.12")){
            new TuongLaiHoanThanhTiepDien(webView);
        }

        if(id.equals("2.1")){
            new CauTrucCauBiDong(webView);
        }

        if(id.equals("2.2")){
            new TruongHopDacBietCauBiDong(webView);
        }

        if(id.equals("3.1")){
            new CauUocLoai1(webView);
        }
        if(id.equals("3.2")){
            new CauUocLoai2(webView);
        }
        if(id.equals("3.3")){
            new CauUocLoai3(webView);
        }
        if(id.equals("4")){
            new ThemCauGianTiep(webView);
        }
        if(id.equals("5.1")){
            new CauDieuKienL1(webView);
        }
        if(id.equals("5.2")){
            new CauDieuKienL2(webView);
        }
        if(id.equals("5.3")){
            new CauDieuKienL3(webView);
        }
        if(id.equals("5.4")){
            new CauDieuKienDaoNgu(webView);
        }
        if(id.equals("5.5")){
            new CauDieuKienDacBiet(webView);
        }
        if(id.equals("6.1")){
            new SoSanhBang(webView);
        }
        if(id.equals("6.2")){
            new SoSanhHon(webView);
        }
        if(id.equals("6.3")){
            new SoSanhHonNhat(webView);
        }
        if(id.equals("6.4")){
            new SoSanhKep(webView);
        }
        if(id.equals("6.5")){
            new SoSanhHonNhieuLan(webView);
        }
        if(id.equals("6.6")){
            new BangSoSanh(webView);
        }
        if(id.equals("7.1")){
            new DaiTuTrangTuQuanHe(webView);
        }
        if(id.equals("7.2")){
            new RutGonMenhDeQuanHe(webView);
        }
        if(id.equals("8")){
            new ThemCauCamThan(webView);
        }
        if(id.equals("9.1")){
            new CongThucCauHoiDuoi(webView);
        }
        if(id.equals("9.2")){
            new CacDangDacBietCauHoiDuoi(webView);
        }
        if(id.equals("10")){
            new ThemCauDaoNgu(webView);
        }
        if(id.equals("11")){
            new ThemCauMenhLenh(webView);
        }
        if(id.equals("12")){
            new ThemCauNhanManh(webView);
        }
        if(id.equals("13.1")){
            new Phan1(webView);
        }
        if(id.equals("13.2")){
            new Phan2(webView);
        }
        if(id.equals("13.3")){
            new Phan3(webView);
        }
        if(id.equals("13.4")){
            new Phan4(webView);
        }
        if(id.equals("14")){
            new ThemThanhNgu(webView);
        }
        if(id.equals("15")){
            new ThemCauDongTinh(webView);
        }
        if(id.equals("16")){
            new ThemDongTuBatQuyTac(webView);
        }
        if(id.equals("17.1")){
            new CacLoaiDanhTu(webView);
        }
        if(id.equals("17.2")){
            new DanhTuDemDuocVaKhongDemDuoc(webView);
        }
        if(id.equals("17.3")){
            new DanhTuSoItSoNhieu(webView);
        }
        if(id.equals("17.4")){
            new DanhTuBatQuyTac(webView);
        }
        if(id.equals("18.1")){
            new DongTuThieuKhuyet(webView);
        }
        if(id.equals("18.2")){
            new NoiDongTuVaNgoaiDongTu(webView);
        }
        if(id.equals("19.1")){
            new ViTriTinhTu(webView);
        }
        if(id.equals("19.2")){
            new TinhTuTanCung(webView);
        }
        if(id.equals("19.3")){
            new TinhTuKep(webView);
        }
        if(id.equals("19.4")){
            new CauTrucTratTuTinhTu(webView);
        }
        if(id.equals("19.5")){
            new CacTinhTuThuongGap(webView);
        }
        if(id.equals("20.1")){
            new ViTriTrangTu(webView);
        }
        if(id.equals("20.2")){
            new CacLoaiTrangTu(webView);
        }
        if(id.equals("20.3")){
            new PhanLoaiTrangTu(webView);
        }
        if(id.equals("20.4")){
            new CacTrangTuThuongGap(webView);
        }
        if(id.equals("21.1")){
            new CachDungGioiTu(webView);
        }
        if(id.equals("21.2")) {
            new CacLoaiGioiTu(webView);
        }
        if(id.equals("22")) {
            new ThemQuyTacTrongAm(webView);
        }
        if(id.equals("23")) {
            new ThemPhatAm_s_es(webView);
        }
        if(id.equals("24")) {
            new ThemPhatAm_ed(webView);
        }
        if(id.equals("25")) {
            new ThemVitriTinhTuDanhTu(webView);
        }

    }
}
