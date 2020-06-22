package com.sulistionoadi.ngoprek.common.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class StringHelper {
 
	public static String thousandSeparator(BigDecimal value) {
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(new Locale("ID"));
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		symbols.setGroupingSeparator('.');
		formatter.setDecimalFormatSymbols(symbols);
		return formatter.format(value.longValue());
	}
	
}
