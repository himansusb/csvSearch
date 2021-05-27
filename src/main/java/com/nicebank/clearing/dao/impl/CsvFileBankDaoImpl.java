package com.nicebank.clearing.dao.impl;

import com.nicebank.clearing.dao.BankDao;
import com.nicebank.clearing.model.BankInfo;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class CsvFileBankDaoImpl implements BankDao {

    Map<Integer,BankInfo> bankCache = new HashMap<>();

    @Override
    public int loadBankInfo(String... options) throws Exception {

        if (options.length < 1){
            throw new Exception("Bank database File not specified");
        }

        String bankFile = options[0];

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(bankFile);

        if (inputStream == null){
            throw new Exception("File "+ bankFile + " not found in classpath");
        }

        //ID,Bank Name,Type,City,State,Zip code
        Scanner bankScanner = new Scanner(inputStream);
        String line;
        int recordCount = 0;
        while (bankScanner.hasNextLine()){
            line = bankScanner.nextLine();
            String[] fields = line.split(",");

            if (fields.length < 6 ){
                System.err.println("File has invalid data");
                bankCache.clear();
                break;
            }
            if (fields[0].equalsIgnoreCase("ID")){
                System.out.println("This is header record");
                continue;
            }
            recordCount++;
            // Create a new Bank object
            BankInfo bankInfo = new BankInfo();
            bankInfo.setBankId(Integer.valueOf(fields[0]));
            bankInfo.setBankName(fields[1]);
            bankInfo.setBankType(fields[2]);
            bankInfo.setCityName(fields[3]);
            bankInfo.setStateCode(fields[4]);
            bankInfo.setZipCode(fields[5]);
            bankCache.put(bankInfo.getBankId(),bankInfo);
            //System.out.println(bankInfo.toString());
        }

        bankScanner.close();
        System.out.println("Loaded " + recordCount + " bank details");
        return recordCount;
    }

    @Override
    public List<BankInfo> searchBank(String... options) throws Exception {

        // options[0] = option 1 or 2
        // Option A: By Zip code or State or City or Type or Bank Name
        // Option B : By City & State

        // option[1] = zip code or State or City or Type or Bank Name  (Option A)
        // option[1] = City (Option B)
        // option[2] = State (Option B)

        List<BankInfo> searchResult;

        String option = options[0];
        switch (option){
            case "A":
                String searchValue = options[1];
                ;
                System.out.println(String.format("Search Value: %s" ,searchValue));
                searchResult =
                bankCache.values()
                         .stream()
                         .filter(b-> b.getZipCode().equals(searchValue) ||
                                     b.getStateCode().equals(searchValue) ||
                                     b.getCityName().equals(searchValue.replace("_"," ")) ||
                                     b.getBankType().equals(searchValue) ||
                                     b.getBankName().equals(searchValue))
                         .collect(Collectors.toList());
                break;
            case "B":
                String city = options[1];
                String state = options[2];
                System.out.println(String.format("City: %s, State : %s" ,city,state));
                searchResult =
                        bankCache.values()
                                .stream()
                                .filter(b-> b.getCityName().equals(city.replace("_"," ")) && b.getStateCode().equals(state))
                                .collect(Collectors.toList());
                break;
            default:
                // return empty list
                searchResult= new ArrayList<>();
                break;
        }

        if (!searchResult.isEmpty()){
            System.out.println("Found " + searchResult.size() + " results as per search");
        }

        return searchResult;
    }
}
