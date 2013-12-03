package parsing;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/* Partitioner: SecondarySortBasicPartitioner */

public class SecondarySortBasicPartitioner extends
		Partitioner<CompositeKeyWritable, NullWritable> {

	public int getPartition(CompositeKeyWritable key, NullWritable value,
			int numReduceTasks) {

		return (key.getidNo().hashCode() % numReduceTasks);
	}
}