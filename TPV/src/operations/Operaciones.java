package operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Operaciones {

	public static String readFile(String file)
	{
		String fichero="";
		try (BufferedReader br=new BufferedReader(new FileReader(file))) {
			String linea;
			while((linea=br.readLine())!=null){
				fichero+=linea;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return fichero;
	}
	
	public static void writeFile(String data, String file)
	{
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
			bw.write(data);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}
