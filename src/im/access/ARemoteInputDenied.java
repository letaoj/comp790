package im.access;

public class ARemoteInputDenied extends AMessage<String> {
	public ARemoteInputDenied(String theUserName) {
		super(theUserName);
	}
}
