package menu;

public class Menus {

	public static String mainMenuOptions() {
		StringBuilder result = new StringBuilder("========MAIN MENU========\n");
		result.append("What would you like to do?\n");
		result.append("1. Start the game\n");
		result.append("2. View the manual\n");
		result.append("3. Exit\n");
		result.append("Choice: ");
		return result.toString();
	}

	public static String gameOptions() {
		StringBuilder result = new StringBuilder();
		result.append("1. Add a node\n");
		result.append("2. Delete a node\n");
		result.append("3. Exit to main menu\n");
		result.append("Choice: ");
		return result.toString();
	}

	public static String welcomeScreen() {
		StringBuilder result = new StringBuilder("---------------");
		result.append("Welcome to Simple Network Game");
		result.append("---------------\n");
		return result.toString();
	}
}
