package com.ashafalovich.testApplication.service;

import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    public String convert(String param) {
        try {
            return convertLongToIp(Long.parseLong(param));
        } catch (NumberFormatException e) {
            return Long.toString(convertIpToInt(param));
        }
    }

    private String convertLongToIp (Long inputParam) {
        String binarySet = Long.toBinaryString(inputParam);
        String binarySetWithPadding = String.format("%32s", binarySet).replaceAll(" ", "0");

        StringBuilder result = new StringBuilder();
        StringBuilder binaryNumber = new StringBuilder();
        int digit = 0;
        int bit = 8;

        for (int i = 0; i < 4; i++) {
            if (i != 0) result.append(".");

            for (int j = bit * i; j < bit * i + 8; j++) {
                binaryNumber.append(binarySetWithPadding.charAt(j));
            }
            digit = Integer.parseInt(binaryNumber.toString(),2);
            result.append(digit);
            binaryNumber.setLength(0);
        }
        return result.toString();
    }

    private long convertIpToInt(String inputParam) {
        String[] ipAddress = inputParam.split("\\.");
        long result = 0;

        for (int i = 0; i < ipAddress.length; i++) {
            int power = 3 - i;
            result += Integer.parseInt(ipAddress[i]) * Math.pow(256, power);
        }
        return result;
    }



}
