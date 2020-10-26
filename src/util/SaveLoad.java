package util;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.GraphStore;

public class SaveLoad {
	
	
	public static void save(GraphStore gs,String location) {
		 FileOutputStream fos;
		try {
			fos = new FileOutputStream(location);
		} catch (FileNotFoundException e1) {
			fos=null;
		}
		    ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(fos);
			} catch (IOException e1) {
				oos=null;
			}
		    try {
				oos.writeObject(gs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static GraphStore load(String location) throws IOException, ClassNotFoundException {
		FileInputStream fileInStr = new FileInputStream(location);
        ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
        GraphStore graphStore = (GraphStore) objInStr.readObject();
        objInStr.close();
        fileInStr.close();

        return graphStore;
		 
	    }
	
}
