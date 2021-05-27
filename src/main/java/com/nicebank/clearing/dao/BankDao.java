package com.nicebank.clearing.dao;

import com.nicebank.clearing.model.BankInfo;

import java.util.List;

public interface BankDao {

    public int loadBankInfo(String ... options) throws Exception;
    public List<BankInfo> searchBank(String ...options) throws Exception;

}
