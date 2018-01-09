package test;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * ��ֵ��صĹ�����?
 * @author zym
 *
 */
public class NumberUtil {
	/**
	
	 */
	public static String changeNumberType(BigDecimal big) {

		big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}
	public static boolean isInteger(String str){
		try {    
		    //把字符串强制转换为数�?   
		    int num=Integer.valueOf(str);   
		    //如果是数字，返回True    
		    return true;   
		} catch (Exception e) {   
		    //如果抛出异常，返回False    
		    return false;   
		}
	}
}
