package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders=new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note=new TextNote(title);
		return insertNote(folderName,note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note=new ImageNote(title);
		return insertNote(folderName,note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean insertNote(String folderName,Note note) {
		Folder f=null;
		for(Folder f1:folders) {
			if(f1.getName()==folderName) f=f1;
		}
		if(f==null) {
			f=new Folder(folderName);
			folders.add(f);
		}
		for(Note n:f.getNotes()) {
			if(n.equals(note)) {
				System.out.println("Creating note "+note.getTitle()+" under folder "+folderName+" failed");
				return false;
			}
		}
		f.getNotes().add(note);
		return true;
	}
	
	public void sortFolders(){
		for(Folder i:folders) {
			i.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note=new TextNote(title,content);
		return insertNote(folderName,note);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note>temp=new ArrayList<Note>();
		for(Folder f:folders) {
			List<Note> temp2=f.searchNotes(keywords);
			if(temp2.size()!=0){
				temp.addAll(temp2);
			}
		}
		return temp;
	}
}
