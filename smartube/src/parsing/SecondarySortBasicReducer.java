package parsing;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


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