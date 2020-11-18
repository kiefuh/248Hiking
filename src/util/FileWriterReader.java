package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import store.TreeMapStore;
import store.UserStore;

public class FileWriterReader {
	public static void saveUsers(UserStore obj,String fileName)throws IOException{
	    FileOutputStream fos = new FileOutputStream(fileName,false);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(obj);
	    oos.close();
	}

	public static UserStore loadUsers(String location) throws IOException, ClassNotFoundException{
	   String fileName= location;
	   FileInputStream fin = new FileInputStream(fileName);
	   ObjectInputStream ois = new ObjectInputStream(fin);
	   UserStore userStore= (UserStore) ois.readObject();
	   try {
		ois.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return userStore;
	}
	
	public static void saveTrails(TreeMapStore obj,String fileName)throws IOException{
	    FileOutputStream fos = new FileOutputStream(fileName,false);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(obj);
	    oos.close();
	}

	public static TreeMapStore loadTreeMapStore(String location) throws IOException, ClassNotFoundException{
	   String fileName= location;
	   FileInputStream fin = new FileInputStream(fileName);
	   ObjectInputStream ois = new ObjectInputStream(fin);
	   TreeMapStore trailStore= (TreeMapStore) ois.readObject();
	   try {
		ois.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return trailStore;
	}
}
