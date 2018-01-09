

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DOM4JTest <T> {
	/**  
     * DMO4Jд��XML  
     * @param obj        ���Ͷ���  
     * @param entityPropertys ���Ͷ����List����  
     * @param Encode     XML�Զ����������(�Ƽ�ʹ��GBK)  
     * @param XMLPathAndName    XML�ļ���·�����ļ���  
     */  
    public void writeXmlDocument(T obj, List<T> entityPropertys, String Encode,   
            String XMLPathAndName) {   
        long lasting = System.currentTimeMillis();//Ч�ʼ��   
  
        try {   
            XMLWriter writer = null;// ����дXML�Ķ���    
            OutputFormat format = OutputFormat.createPrettyPrint();   
            format.setEncoding(Encode);// ����XML�ļ��ı����ʽ   
  
            String filePath = XMLPathAndName;//����ļ���ַ   
            File file = new File(filePath);//����ļ�     
  
            if (file.exists()) {   
                file.delete();   
  
            }   
            // �½�student.xml�ļ�����������   
            Document document = DocumentHelper.createDocument();   
            String rootname = obj.getClass().getSimpleName();//�������   
            Element root = document.addElement(rootname + "s");//��Ӹ��ڵ�   
            Field[] properties = obj.getClass().getDeclaredFields();//���ʵ�������������   
            
            for (T t : entityPropertys) {                                //�ݹ�ʵ��   
                Element secondRoot = root.addElement(rootname);            //�����ڵ�   
                
                for (int i = 0; i < properties.length; i++) {                      
                    //����get����       
                    Method meth = t.getClass().getMethod(                      
                            "get"  
                                    + properties[i].getName().substring(0, 1)   
                                            .toUpperCase()   
                                    + properties[i].getName().substring(1));   
                    //Ϊ�����ڵ�������ԣ�����ֵΪ��Ӧ���Ե�ֵ   
                    secondRoot.addElement(properties[i].getName()).setText(   
                            meth.invoke(t).toString());   
  
                }   
            }   
            //����XML�ļ�   
            writer = new XMLWriter(new FileWriter(file), format);   
            writer.write(document);   
            writer.close();   
            long lasting2 = System.currentTimeMillis();   
            System.out.println("д��XML�ļ�����,��ʱ"+(lasting2 - lasting)+"ms");   
        } catch (Exception e) {   
            System.out.println("XML�ļ�д��ʧ��");   
        }   
  
    }  
    
    /**  
     *   
     * @param XMLPathAndName XML�ļ���·���͵�ַ  
     * @param t     ���Ͷ���  
     * @return  
     */     
        @SuppressWarnings("unchecked")   
        public List<T> readXML(String XMLPathAndName, T t) {   
            long lasting = System.currentTimeMillis();//Ч�ʼ��   
            List<T> list = new ArrayList<T>();//����list����   
            try {   
                File f = new File(XMLPathAndName);//��ȡ�ļ�   
                SAXReader reader = new SAXReader();   
                Document doc = reader.read(f);//dom4j��ȡ   
                Element root = doc.getRootElement();//��ø��ڵ�   
                Element foo;//�����ڵ�   
                Field[] properties = t.getClass().getDeclaredFields();//���ʵ��������   
                //ʵ����get����   
                Method getmeth;   
                //ʵ����set����   
                Method setmeth;   
                   
                for (Iterator i = root.elementIterator(t.getClass().getSimpleName()); i.hasNext();) {//����t.getClass().getSimpleName()�ڵ�   
                    foo = (Element) i.next();//��һ�������ڵ�   
                       
                   t=(T)t.getClass().newInstance();//��ö�����µ�ʵ��   
      
                   for (int j = 0; j < properties.length; j++) {//�����������ӽڵ�   
                           
      
                        //ʵ����set����   
                          setmeth = t.getClass().getMethod(   
                                "set"  
                                        + properties[j].getName().substring(0, 1)   
                                                .toUpperCase()   
                                        + properties[j].getName().substring(1),properties[j].getType());   
                      //properties[j].getType()Ϊset������ڲ����Ĳ�������(Class����)   
                        setmeth.invoke(t, foo.elementText(properties[j].getName()));//����Ӧ�ڵ��ֵ����   
            
                    }   
                      
                    list.add(t);   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
            long lasting2 = System.currentTimeMillis();   
            System.out.println("��ȡXML�ļ�����,��ʱ"+(lasting2 - lasting)+"ms");   
            return list;   
        }  
        
        public static void main(String[] args) {
		}
}
