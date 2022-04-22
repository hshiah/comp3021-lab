package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.io.*;
public class Folder implements Comparable<Folder>,Serializable{
	private ArrayList<Note> notes;
	private String name;
	
	
	public Folder(String name) {
		notes=new ArrayList<Note>();
		this.name=name;
	}
	
	public void addNote(Note newNote) {
		notes.add(newNote);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public String toString() {
		int nText=0;
		int nImage=0;
		
		for(Note n:notes) {
			if(n instanceof TextNote) nText++;
			else if(n instanceof ImageNote) nImage++;
		}
		return name+":"+nText+":"+nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	public int compareTo(Folder f) {
		return name.compareTo(f.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> temp=new ArrayList<Note>();
		keywords=keywords.toLowerCase();
		keywords=keywords.replace(" or ", "\t");
		String[] com=keywords.split(" ");
		for(Note n:notes) {
			if(n instanceof ImageNote) {
				boolean tempCom=true;
				for(String s:com) {
					if(s.indexOf("\t")==-1) {
						if(n.getTitle().toLowerCase().indexOf(s)==-1) {
							tempCom=false; break;
						}
					}else {
						String[] com2=s.split("\t");
						if(n.getTitle().toLowerCase().indexOf(com2[0])==-1&&n.getTitle().toLowerCase().indexOf(com2[1])==-1) {
							tempCom=false; break;
						}
					}
				}
				if(tempCom) temp.add(n);
			}else if(n instanceof TextNote){
				TextNote n1=(TextNote)n;
				boolean tempCom=true;
				for(String s:com) {
					if(s.indexOf("\t")==-1) {
						if(n1.getTitle().toLowerCase().indexOf(s)==-1&&n1.getContent().toLowerCase().indexOf(s)==-1) {
							tempCom=false; break;
						}
					}else {
						String[] com2=s.split("\t");
						if((n1.getTitle().toLowerCase().indexOf(com2[0])==-1&&n1.getTitle().toLowerCase().indexOf(com2[1])==-1)&&
							(n1.getContent().toLowerCase().indexOf(com2[0])==-1&&n1.getContent().toLowerCase().indexOf(com2[1])==-1)) {
							tempCom=false; break;
						}
					}
				}
				if(tempCom) temp.add(n);
			}
		}
		return temp;
	}
	public boolean removeNotes(String title) {
		for(Note n:notes) {
			if(n.getTitle().equals(title)) {
				notes.remove(n);
				return true;
			}
		}return false;
	}
	
}
