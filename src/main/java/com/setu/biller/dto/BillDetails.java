package com.setu.biller.dto;

import com.setu.biller.entity.BillStatus;

import java.util.ArrayList;
import java.util.List;

public class BillDetails {

    private BillStatus billFetchStatus;

    private List<BillDto> bills = new ArrayList<>();

    public BillStatus getBillFetchStatus() {
        return billFetchStatus;
    }

    public void setBillFetchStatus(BillStatus billFetchStatus) {
        this.billFetchStatus = billFetchStatus;
    }

    public List<BillDto> getBills() {
        return bills;
    }

    public void setBills(List<BillDto> bills) {
        this.bills = bills;
    }
}
