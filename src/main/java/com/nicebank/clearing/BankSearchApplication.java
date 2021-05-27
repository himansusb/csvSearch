package com.nicebank.clearing;

import com.nicebank.clearing.dao.BankDao;
import com.nicebank.clearing.dao.impl.CsvFileBankDaoImpl;
import com.nicebank.clearing.model.BankInfo;

import java.util.List;

public class BankSearchApplication {

    public static void main(String[] args) {

        if (args[0].equalsIgnoreCase("H")){
            System.out.println("This application takes following command parameters");
            System.out.println("[A|B|H] <argument(s)>");

            System.out.println("option A: one argument(By Zip code or State or City or Type or Bank Name))");
            System.out.println("option B: two arguments ( City & State)");
            System.out.println("option H: Show usage");
            System.out.println("example: A Dallas");
            System.out.println("example: B New_York NY");
            System.exit(0);
        }

        BankDao bankDao = new CsvFileBankDaoImpl();
        int count =0;
        try {
            count = bankDao.loadBankInfo("sample_banks.csv");
        }catch (Exception e){
            System.err.println("Could not load bank file " + e.getMessage());
            System.exit(1);
        }

        // Let's search bank info
        List<BankInfo> bankInfoList=null ;
        try {
            bankInfoList =  bankDao.searchBank(args);
        }catch (Exception e){
            System.err.println("Error finding banks " + e.getMessage());
            System.exit(1);
        }

        if (bankInfoList != null && (!bankInfoList.isEmpty()) ) {
            bankInfoList.stream().forEach(bank -> {
                System.out.println(bank.toString());
            });
        }
    }

}
