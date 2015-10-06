package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import networkobjects.Node;
import menu.Menus;
import commands.Commands;

public class Driver {

	private static final String FILE_PATH_TO_MANUAL = "properties/manual.txt";
	private static final String STATIC_NETWORK_PIECE = "----router----Internet----server";
	private static final Scanner input = new Scanner(System.in);
	private static ArrayList<Node> nodes = new ArrayList<Node>();
	private static String networkState = beginningState();

	public static void main(String args[]) {
		try {
			System.out.println(Menus.welcomeScreen());
			System.out.print(Menus.mainMenuOptions());
			String option = getOption();
			while (!Commands.EXIT.equals(option)) {
				switch (option) {
				case Commands.START:
					System.out.println(networkState);
					System.out.print(Menus.gameOptions());
					String gameOption = getOption();
					while (!Commands.RETURN_TO_MAIN.equals(gameOption)) {
						switch (gameOption) {
						case Commands.ADD_NODE:
							addNode();
							break;
						case Commands.DELETE_NODE:
							deleteNode();
							break;
						case Commands.SEND_DATA_TO_SERVER:
							sendDataToServer();
							break;
						case Commands.RETURN_TO_MAIN:
							gameOption = Commands.RETURN_TO_MAIN;
							break;
						default:
							break;
						}
						if (nodes.size() == 0) {
							networkState = beginningState();
							System.out.println(networkState);
						} else {
							System.out.println(getNetworkState());
						}
						System.out.print(Menus.gameOptions());
						gameOption = getOption();
					}
					if (gameOption.equals(Commands.RETURN_TO_MAIN)) {
						System.out.print("\n" + Menus.mainMenuOptions());
						option = getOption();
					}
					break;
				case Commands.MANUAL:
					System.out.println(getManual());
					System.out.print(Menus.mainMenuOptions());
					option = getOption();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getOption() {
		String result = input.nextLine();
		return result;
	}

	private static String getManual() throws Exception {
		StringBuilder result = new StringBuilder("");
		String line = null;
		BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_PATH_TO_MANUAL)));
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		return "\n" + result.toString() + "\n";
	}

	private static String getNetworkState() {
		if (nodes.size() == 1) {
			networkState = "\n" + "node1" + STATIC_NETWORK_PIECE + "\n"; 
		} else {
			switch (nodes.size()) {
			case 2:
				networkState = "\n" + nodes.get(0).getDisplayName() + "---";
				networkState += "\n\t|";
				networkState += "\n\t " + STATIC_NETWORK_PIECE;
				networkState += "\n\t|";
				networkState += "\n" + nodes.get(1).getDisplayName() + "---\n";
				break;
			case 3:
				networkState = "\n" + nodes.get(0).getDisplayName() + "---";
				networkState += "\n\t|";
				networkState += "\n" + nodes.get(1).getDisplayName() + "---";
				networkState += " " + STATIC_NETWORK_PIECE;
				networkState += "\n\t|";
				networkState += "\n" + nodes.get(2).getDisplayName() + "---\n";
				break;
			default:
				int networkDepth = getDepth();
				buildFirstLine();
				networkState += "\n";
				appendVerticalBar(networkDepth);
				networkState += "\n";
				buildMiddleLine();
				networkState += STATIC_NETWORK_PIECE;
				networkState += "\n";
				appendVerticalBar(networkDepth);
				networkState += "\n";
				buildLastLine();
				networkState += "\n";
				break;
			}
		}
		return networkState;
	}
	
	private static String beginningState() {
		StringBuilder result = new StringBuilder("{your network}----router----Internet----server");
		return "\n" + result.toString() + "\n";
	}

	private static int getDepth() {
		int depth = 0;
		int index = 0;
		while (index <= nodes.size() - 1) {
			index += 3;
			depth++;
		}
		return depth;
	}

	private static void deleteNode() {
		if (nodes.size() != 0) {
			nodes.remove(nodes.size() - 1);
		} 
	}
	
	private static void addNode() {
		removeYourNetworkFromNetworkState();
		Node currentNode = new Node(nodes.size() + 1);
		nodes.add(currentNode);
	}
	
	private static void sendDataToServer() throws InterruptedException {
		System.out.print("Enter the data you would like to send: ");
		String data = input.nextLine();
		System.out.print("Sending data to server");
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			Thread.sleep(1000);
		}
		System.out.println("\nData transmission successful.");
	}

	private static void shiftLineByTab(int index) {
		while (index < nodes.size() - 1) {
			index += 3;
		}
		if (nodes.size() % 3 != 0 && index != nodes.size() - 1) {
			networkState += "\t";
		} 
	}

	private static void buildFirstLine() {
		int index = 0;
		networkState = "\n" + nodes.get(index).getDisplayName() + "---";
		while (index + 3 <= nodes.size() - 1) {
			index += 3;
			networkState += nodes.get(index).getDisplayName() + "---";
		}
	}

	private static void buildMiddleLine() {
		int index = 1;
		shiftLineByTab(index);
		while (index <= nodes.size() - 1) {
			networkState += nodes.get(index).getDisplayName() + "---";
			index += 3;
		}
		networkState += " ";
	}

	private static void buildLastLine() {
		int index = 2;
		shiftLineByTab(index);
		while (index <= nodes.size() - 1) {
			networkState += nodes.get(index).getDisplayName() + "---";
			index += 3;
		}
	}

	private static void removeYourNetworkFromNetworkState() {
		if (networkState.startsWith("\n{your network}")) {
			networkState = networkState.substring(15);
		}
	}

	//could use a better solution for this because the tabs are outgrown by the node chains.
	private static void appendVerticalBar(final int depth) {
		int counter = 0;
		while (counter < depth) {
			networkState += "\t";
			counter++;
		}
		networkState += "|";
	}
}
