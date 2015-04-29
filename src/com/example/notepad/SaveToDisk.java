package com.example.notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Environment;
import android.widget.Toast;

public class SaveToDisk {
	
	private File Dir;
	private File settingFile;
	public Boolean makedirectory()
	{

		Dir = new File(Environment.getExternalStorageDirectory(),
				"/MyNotes");
		if (!Dir.isDirectory()) {
			Dir.mkdir();
			settingFile = new File(Dir.getAbsolutePath() + File.separator
					+ "/Setting.txt");
			try {
				if (settingFile.createNewFile()) {
					BufferedWriter bwr = new BufferedWriter(
							new java.io.FileWriter(settingFile.getAbsoluteFile(), true));
					bwr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}}
		return true;
	}
	public String[][] listmaker()
	{
		int i=0;
		File file = new File(Dir.getAbsolutePath() + File.separator	+ "/Setting.txt");
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ((br.readLine()) != null) {
				i++;
				
			}
			br.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String[][] data = new String[i][3];
		Dir = new File(Environment.getExternalStorageDirectory(),"/MyNotes");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "",line2=Dir.getAbsolutePath();
			i=0;
			while ((line = br.readLine()) != null) {
				data[i][0]=line;
				data[i][1]=(String) readFile(line.substring(0, line.length()-4));
				if(data[i][1].length()>8)
				{
					data[i][1] = data[i][1].substring(0, 8).replace("\n", " ");
				}
				data[i][2]=line2;
				i++;
				
			}
			br.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public void writeFile(String title, String body, int new_old) {
		Dir = new File(Environment.getExternalStorageDirectory(),"/MyNotes");
		if (!Dir.isDirectory()) {
			Dir.mkdir();
			//if (!settingFile.isFile())
			settingFile = new File(Dir.getAbsolutePath() + File.separator + "/Setting.txt");
			Toast.makeText(null,"Your default saving location is:\n"+ Dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
			}
		
		int i1=0,flag=0;
		File file1 = new File(Dir.getAbsolutePath() + File.separator	+ "/Setting.txt");
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file1));
			String line = "";
			i1=0;
			while ((line = br.readLine()) != null) {
				if(title.equals(line.substring(0, line.length()-4)))
					flag=1;
				i1++;
				
			}
			br.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		File file = new File(Dir.getAbsolutePath() + File.separator +"/"+title + ".txt");
		if (title.equals("")) {
			for (int i = 1;; i++) {
				title = "Untitled_" + i;
				file = new File(Dir.getAbsolutePath() + File.separator +"/"+ title+ ".txt");
				if (!file.isFile())
					break;
			}
		}
		

		if (flag==1&&new_old==1) // user given titled file already present
		{
			file = new File(Dir.getAbsolutePath() + File.separator +"/"+ title + ".txt");

			if (!file.exists()) {
				try{
					file.createNewFile();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			try{
				BufferedWriter bwr = new BufferedWriter(new java.io.FileWriter(file.getAbsoluteFile(), false));
				
				bwr.write("");
				bwr.flush();
				bwr.close();
				bwr = new BufferedWriter(new java.io.FileWriter(file.getAbsoluteFile(), true));
				
				bwr.write(body);
				bwr.flush();
				
				bwr.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

		} // if user titled file is not present write the file
		else if(flag==1&&new_old==0)
		{
			title = title+(1);
			writeFile(title,body,0);
		}
		else {

			try {
				if (file.createNewFile()) {
					settingFile = new File(Dir.getAbsolutePath() + File.separator + "/Setting.txt");
					BufferedWriter bwr = new BufferedWriter(new java.io.FileWriter(file.getAbsoluteFile(), true));
					BufferedWriter setbwr = new BufferedWriter(new java.io.FileWriter(settingFile.getAbsoluteFile(), true));
					
					setbwr.write(title + ".txt\n");
					setbwr.flush();
					setbwr.close();

					bwr.write(body);
					bwr.flush();
					bwr.close();
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	public String readFile(String title) {
		BufferedReader br = null;
		FileInputStream fstream = null;
		String bodyText="";
		String currentLine = null;
		try {
			File file = new File(Dir.getAbsolutePath() + File.separator + "/" +title
					+ ".txt");
			if (file.exists()) {

				fstream = new FileInputStream(Dir.getAbsolutePath()
						+ File.separator + "/" +title + ".txt");
				br = new BufferedReader(new InputStreamReader(fstream));
				
				do {
					if (currentLine != null) {
						
						bodyText=bodyText+currentLine+'\n';
												

					}
				} while ((currentLine = br.readLine()) != null);

			} else {
				System.out.println("File Doesn't Exists");
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (br != null)
					br.close();
				if (fstream != null)
					fstream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return bodyText;

	}

}
