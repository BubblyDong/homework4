import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;


public class RelationB implements WritableComparable<RelationB>{
	private int id;
	private boolean gender;
	private double height;
	
    public RelationB(){}
	
	public RelationB(int id, boolean gender, double height){
		this.setId(id);
		this.setGender(gender);
		this.setHeight(height);
	}
	
	public RelationB(String line){
		String[] value = line.split(",");
		this.setId(Integer.parseInt(value[0]));
		if(value[1].equals("1")) this.setGender(true);
		else this.setGender(false);
		this.setHeight(Double.parseDouble(value[2]));
	}

	public String getCol(int col){
		switch(col){
		case 0: return String.valueOf(id);
		case 1: {
			if(gender) return "1";
			else return "0";
		} 
		case 2: return String.valueOf(height);
		default: return null;
		}
	}
	
	public String getValueExcept(int col){
		switch(col){
		case 0: {
			if(gender) return "1"+ "," + String.valueOf(height);
			else return "0"+ "," + String.valueOf(height);
		}
		case 1: return String.valueOf(id) + "," + String.valueOf(height);
		case 2: return String.valueOf(id) + "," + String.valueOf(gender);
		default: return null;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(id);
		out.writeBoolean(gender);
		out.writeDouble(height);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		id = in.readInt();
		gender = in.readBoolean();
		height = in.readDouble();
	}

	@Override
	public int compareTo(RelationB o) {
		if(id == o.getId() && gender == o.isGender() && height == o.getHeight())
			return 0;
		else if(id < o.getId())
			return -1;
		else
			return 1;
	}
}
