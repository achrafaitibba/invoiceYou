package com.onxshield.invoiceyou.invoicestatement.util;

import java.text.DecimalFormat;

public class doubleTwoDigitConverter {

    public static double twoDigitTVA(Double tva){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(tva));
    }
}
