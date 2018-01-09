package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static List getContext(String html) {
        List resultList = new ArrayList();
        Pattern p = Pattern.compile(">([^</]+)</");//正则表达式 commend by danielinbiti
        Matcher m = p.matcher(html );//
        while (m.find()) {
            resultList.add(m.group(1));//
        }
        return resultList;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "<doc>abc</doc>";
	    List list = getContext(a);
	    System.out.println(list);
	}
}
