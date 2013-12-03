package parsing;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/* Mapper: SecondarySortBasicMapper */
public class SecondarySortBasicMapper extends
		Mapper<LongWritable, Text, CompositeKeyWritable, NullWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		if (value.toString().length() > 0) {
			String arrvideoAttributes[] = value.toString().split("\\t");

			context.write(
					new CompositeKeyWritable(
							arrvideoAttributes[6].toString(),
							(arrvideoAttributes[3].toString() + "\t" + arrvideoAttributes[2].toString() + "\t" + arrvideoAttributes[0]
									.toString())), NullWritable.get());
		}

	}
}
