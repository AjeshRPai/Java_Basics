package FinanceRelated;

import java.math.*;

public class RoundingModeDifferences
{

	private static final double decimalValue  = 3.147500;

	private static final double decimalValue2  = 3.500;

	private static final double decimalValue3  = 3.55;

	private static final double decimalValue4 =  3.5;

	private static final double decimalVaue5 = 3.11;


	public static void main(String[] args)
	{
		BigDecimal decimal = new BigDecimal(decimalValue).setScale(2, RoundingMode.DOWN);
		System.out.println("RoundingMode.DOWN  original  = "+decimalValue+ " value = "+decimal);

		BigDecimal decimal2 = new BigDecimal(decimalValue).setScale(2, RoundingMode.UP);
		System.out.println("RoundingMode.UP original =  "+decimalValue+ " value = "+decimal2);

		//HALF_UP Rounds off to nearest UP if the UP and Down is equidistant

		BigDecimal decimal3 = new BigDecimal(decimalValue3).setScale(2, RoundingMode.HALF_UP);
		System.out.println("RoundingMode.HALF_UP original =  "+decimalValue3+ " value = "+decimal3);

		//HALF_DOWN Rounds off to nearest DOWN if the UP and Down is equidistant

		BigDecimal decimal4 = new BigDecimal(decimalValue3).setScale(2, RoundingMode.HALF_DOWN);
		System.out.println("RoundingMode.HALF_DOWN original =  "+decimalValue3+ " value = "+decimal4);

		// HALF_EVEN Rounds off to nearest even number if the UP and Down is equidistant
		// Known as bankers rounding

		BigDecimal decimal5 = new BigDecimal(decimalValue4).setScale(0, RoundingMode.HALF_EVEN);
		System.out.println("RoundingMode.HALF_EVEN original =  "+decimalValue4+ " value = "+decimal5);

	}






}

