package echo.modular;

import trace.echo.modular.EchoTracerSetter;

public class TestableTracingLaunch {
	public static void main (String[] args) {
		EchoTracerSetter.traceEchoer();
		AnEchoComposerAndLauncher.traceUnawareLaunch(args);
	}

}
