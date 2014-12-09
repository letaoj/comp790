package echo.modular;

public class NonTracingLaunch {
	public static void main (String[] args) {
		AnEchoComposerAndLauncher.traceUnawareLaunch(args);
	}

}
