package com.ch02;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTempReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		int maxValue=Integer.MIN_VALUE;
		for (IntWritable val : values) {
			maxValue = Math.max(maxValue, val.get());
		}
		context.write(key, new IntWritable(maxValue));
	}

}
