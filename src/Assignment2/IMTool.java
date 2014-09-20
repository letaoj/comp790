package Assignment2;


import java.util.List;
import java.util.Scanner;

public class IMTool {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MyBean myBean = new MyBean();
		SingleUI alice = new SingleUI("Alice", myBean);
		SingleUI bob = new SingleUI("Bob", myBean);
		SingleUI cathy = new SingleUI("Catch", myBean);
		myBean.addPropertyChangeListener(alice.listeners);
		myBean.addPropertyChangeListener(bob.listeners);
		myBean.addPropertyChangeListener(cathy.listeners);
		
		String input = "";
		while (!"quit".equals(input)) {
			System.out.println("please enter an input line or quit or history");
			input = scanner.nextLine();
			if ("history".equals(input)) {
				List<String> history = myBean.getValue();
				for (int i = 0; i < history.size(); i++) {
					System.out.print(history.get(i));
					if (i != history.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("\n");
			} else if ("quit".equals(input)) {
				break;
			} else {
				myBean.setValue("console", input);
			}
		}
		myBean.close();
		scanner.close();
	}
}
