import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class ParseJava {

	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
		String base="C:\\Users\\Administrator\\Desktop\\破产xml\\1";
		File file=new File(base);
		String filenames[]=file.list();
		for(String name:filenames){
		fileUtil.setS(base+"\\"+name);
		ArrayList<String> datas = fileUtil.readFromFile();
		ArrayList<String> result = parse(datas);
		if(ChineseToEnglish.ppzwzf(name)){
		name=ChineseToEnglish.getPinYinHeadChar(name);
		}
		fileUtil.setS("C:\\Users\\Administrator\\Desktop\\破产xml"+name);
		fileUtil.writeToFile(result);
		}
	}

	public static ArrayList<String> parse(ArrayList<String> datas) {
		String className = "";
		ArrayList<String> result = new ArrayList<>();
		for (String str : datas) {
			// 如果他是注释或者不是中文字符跳过，添加进来
			if (str.contains("*") || !ChineseToEnglish.ppzwzf(str)) {
				result.add(str);
				continue;
			}
			StringBuilder stringBuilder = new StringBuilder();
			// 如果他包含中文字符，将采取一系列措施
			String[] split = str.split("\\s+");

			//记录最后一个汉字
			String pre="";
			for (String tt : split) {
				if (ChineseToEnglish.ppzwzf(tt)) {
					pre=tt;
					String pinyin = ChineseToEnglish.getPinYinHeadChar(tt);
					//单独判断，因为涉及到类名 
					if (str.contains("class")) {
						className = pinyin;
					}
					stringBuilder.append(pinyin + " ");
				} else {
					stringBuilder.append(tt + " ");
				}
			}
			pre=pre.replaceAll("[;{]", "");
			if(str.contains("class")){
				result.add("@XStreamAlias(\""+pre+"\")");
			}
			if(str.contains("protected")){
				result.add("@XStreamAlias(\""+pre+"\")");
			}
			System.out.println(stringBuilder.toString());
            result.add(stringBuilder.toString());
		}
		return result;
	}

}
