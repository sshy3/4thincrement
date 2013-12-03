package parsing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CompositeKeyWritable implements Writable,
		WritableComparable<CompositeKeyWritable> {

	private String id;
	private String lArtist;
	private String lvideoIDPair;

	public CompositeKeyWritable() {
	}

	public CompositeKeyWritable(String id, String lArtist) {
		this.id = id;

		this.lArtist = lvideoIDPair;
	}

	@Override
	public String toString() {
		return (new StringBuilder().append(id).append("\t").append(lArtist))
				.toString();
	}

	public void readFields(DataInput dataInput) throws IOException {
		id = WritableUtils.readString(dataInput);
		lvideoIDPair = WritableUtils.readString(dataInput);
	}

	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput, id);
		WritableUtils.writeString(dataOutput, lvideoIDPair);
	}

	public int compareTo(CompositeKeyWritable objKeyPair) {

		int result = id.compareTo(objKeyPair.id);
		if (0 == result) {
			result = lvideoIDPair.compareTo(objKeyPair.lvideoIDPair);
		}
		return result;
	}

	public String getidNo() {
		return id;
	}

	public void setidNo(String idNo) {
		this.id = idNo;
	}

	public String getLvideoIDPair() {
		return lvideoIDPair;
	}

	public void setLvideoIDPair(String lvideoPair) {
		this.lvideoIDPair = lvideoPair;
	}
}




/* Reducer: SecondarySortBasicReducer */

public class SecondarySortBasicReducer
		extends
		Reducer<CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable> {

	public void reduce(CompositeKeyWritable key, Iterable<NullWritable> values,
			Context context) throws IOException, InterruptedException {

		for (NullWritable value : values) {

			context.write(key, NullWritable.get());
		}

	}
}

/* Driver: SecondarySortBasicDriver */

public class SecondarySortBasicDriver extends Configured implements Tool {

	public int run(String[] args) throws Exception {

		if (args.length != 2) {
			System.out
					.printf("Two parameters are required for SecondarySortBasicDriver- <input dir> <output dir>\n");
			return -1;
		}

		Job job = new Job(getConf());
		job.setJobName("Secondary sort example");

		job.setJarByClass(SecondarySortBasicDriver.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(SecondarySortBasicMapper.class);
		job.setMapOutputKeyClass(CompositeKeyWritable.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setPartitionerClass(SecondarySortBasicPartitioner.class);
		job.setSortComparatorClass(SecondarySortBasicCompKeySortComparator.class);
		job.setGroupingComparatorClass(SecondarySortBasicGroupingComparator.class);
		job.setReducerClass(SecondarySortBasicReducer.class);
		job.setOutputKeyClass(CompositeKeyWritable.class);
		job.setOutputValueClass(NullWritable.class);

		job.setNumReduceTasks(8);

		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new SecondarySortBasicDriver(), args);
		System.exit(exitCode);
	}
}