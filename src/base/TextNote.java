package base;
import java.io.*;
public class TextNote extends Note implements Serializable{
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title,String content) {
		super(title);
		this.content=content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content=getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result="";
		File file=null;
		InputStream is=null;
		try {
			file=new File(absolutePath);
			is=new FileInputStream(file);
			//result+=is.readAllBytes();
			is.close();
		}catch(Exception e) {
			return null;
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		String temp=super.getTitle().replace(' ', '_');
		File file=new File(pathFolder+File.separator+temp+".txt");
		try {
			FileWriter fw=new FileWriter(file.getName());
			fw.write(temp);
			fw.close();
		}catch(Exception e) {
			return;
		}
	}
	public String getContent() {
		return content;
	}
	public void saveContent(String c) {
		content=c;
	}
}
