package com.ductho.nguphaptienganh.CacBaiHoc;

import com.ductho.nguphaptienganh.Model.BaiHoc;

import java.util.ArrayList;

public class ThemCauUoc {
    ArrayList<BaiHoc> arrayList;

    public ThemCauUoc(ArrayList<BaiHoc> arrayList) {
        this.arrayList = arrayList;
        add();
    }

    private void add() {
        arrayList.add(new BaiHoc("Câu ước loại 1 (Tương lai)", "Định nghĩa, công thức, ví dụ", "3.1"));
        arrayList.add(new BaiHoc("Câu ước loại 2 (Hiện tại)", "Định nghĩa, công thức, ví dụ", "3.2"));
        arrayList.add(new BaiHoc("Câu ước loại 3 (Quá khứ)", "Định nghĩa, công thức, ví dụ", "3.3"));
    }
}
