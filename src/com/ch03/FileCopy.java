package com.ch03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String localSrc = args[0];
		String fileName="";
		//extract file name from complete path
		int fileExtension = localSrc.lastIndexOf(".");
		if (fileExtension != -1){
			int lastSlash = localSrc.lastIndexOf("/");
			if (lastSlash == -1){
				fileName= localSrc;
			}else {
				fileName = localSrc.substring(lastSlash);
			}
		}
		String copyTo = args[1];
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		FSDataOutputStream out = fs.create(new Path(copyTo+fileName), true,4096,new Progressable() {
			public void progress() {
				System.out.print(".");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
		
	}

}
