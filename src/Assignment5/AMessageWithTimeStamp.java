package Assignment5;

import java.io.Serializable;

public class AMessageWithTimeStamp implements Serializable{

	Object message;
	VectorTimeStamp vectorTimeStamp;
	
	public AMessageWithTimeStamp (Object theMessage, VectorTimeStamp theTimeStamp) {
		message = theMessage;
		vectorTimeStamp = theTimeStamp;
	}
	
	public Object getMessage() {
		return message;
	}
	
	public VectorTimeStamp getVectorTimeStamp() {
		return vectorTimeStamp;
	}
	
	
}
