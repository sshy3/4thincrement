package parsing;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/* SortComparator: SecondarySortBasicCompKeySortComparator */

public class SecondarySortBasicCompKeySortComparator extends WritableComparator {

	protected SecondarySortBasicCompKeySortComparator() {
		super(CompositeKeyWritable.class, true);
	}

	public int compare(WritableComparable w1, WritableComparable w2) {
		CompositeKeyWritable key1 = (CompositeKeyWritable) w1;
		CompositeKeyWritable key2 = (CompositeKeyWritable) w2;

		int cmpResult = key1.getidNo().compareTo(key2.getidNo());
		if (cmpResult == 0)// same idNo
		{
			return -key1.getLvideoIDPair().compareTo(key2.getLvideoIDPair());
			// If the minus is taken out, the values will be in
			// ascending order
		}
		return cmpResult;
	}
}
