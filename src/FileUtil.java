

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class FileUtil {
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	private String s;

	public void writeToFile(@SuppressWarnings("rawtypes") ArrayList list) {
		String path = s;
		File file = new File(path);
		if (!file.exists()) {
			String str = null;
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String str = null;
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> readFromFile() {
		File file = new File(s);
		String str = null;
		ArrayList<String> list = new ArrayList<String>();
		FileReader fr = null;
		if (file.exists()) {
			try {
				list.add(getStringFromFile(file, "utf-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list = new ArrayList<String>();
		}
		return list;
	}
	public static String getStringFromFile(File f, String charset)
			throws IOException {
		StringBuffer sb = new StringBuffer(); 
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis, charset);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		br.close();
		isr.close();
		fis.close();
		return sb.toString();
	}
	public static void fileCopy(String path1,String file1,String path2,String file2) throws IOException{
		File file = new File(path2);
		if(!file.exists()&& !file.isDirectory()){
			file.mkdir();
		}
        FileInputStream fis = new FileInputStream(path1+"\\"+file1);
        FileOutputStream fos = new FileOutputStream(path2+"\\"+file2);
 
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
	}
	public void append(String str) {
		String path = s;
		File file = new File(path);

		try {

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bufw = new BufferedWriter(fw);

			bufw.write(str);
			bufw.newLine();

			bufw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    //将byte数组写入文件  
  public  static void createFile(String path, byte[] content) throws IOException {  

      FileOutputStream fos = new FileOutputStream(path);  

      fos.write(content);  
      fos.close();  
  }  
	public static byte[] getContent(String filePath) throws IOException {  
        File file = new File(filePath);  
        long fileSize = file.length();  
        if (fileSize > Integer.MAX_VALUE) {  
            System.out.println("file too big...");  
            return null;  
        }  
        FileInputStream fi = new FileInputStream(file);  
        byte[] buffer = new byte[(int) fileSize];  
        int offset = 0;  
        int numRead = 0;  
        while (offset < buffer.length  
        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
            offset += numRead;  
        }  
        // 确保所有数据均被读取  
        if (offset != buffer.length) {  
        throw new IOException("Could not completely read file "  
                    + file.getName());  
        }  
        fi.close();  
        return buffer;  
    }  
	public static void writeToFile(List<String> list,String path) {
		File file = new File(path);
		if (!file.exists()) {
			String str = null;
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String str = null;
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bufw = new BufferedWriter(fw);
				for (int i = 0; i < list.size(); i++) {
					if ((str = (String) list.get(i)) != null) {
						bufw.write(str);
						bufw.newLine();
					}
				}
				bufw.close();
				fw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
