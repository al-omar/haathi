package com.ch03;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class FileStatus {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String uri = args[0];
		Configuration conf=new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		Path paths[]=new Path[args.length];
		
		for(int i=0;i<paths.length;i++){
			paths[i]=new Path(args[i]);
		}
		org.apache.hadoop.fs.FileStatus[] fileStatus=fs.listStatus(paths);
		Path[] listedPaths = FileUtil.stat2Paths(fileStatus);
		for(int i=0;i<fileStatus.length;i++){
		Path p = listedPaths[i];
		org.apache.hadoop.fs.FileStatus fstatus = fileStatus[i];
			System.out.println(p);
			System.out.println(fstatus.getLen()); //will come zero because of no hflush
			System.out.println(fstatus+"\n");
		}
		
		
		

	}

}
