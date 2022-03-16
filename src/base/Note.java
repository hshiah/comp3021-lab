package base;

import java.util.Date;
import java.util.Objects;
import java.io.*;
public class Note implements Comparable<Note>,Serializable{
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title=title;
		date=new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	public int compareTo(Note n) {
		return date.compareTo(n.date);
	}
	
	public String toString() {
		return date.toString()+"\t"+title;
	}

}
