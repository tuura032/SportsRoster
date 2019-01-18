package Application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PlayerDao;
import entity.Player;



public class Menu {

	private PlayerDao playerDao = new PlayerDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Show Full Roster", 
			"Show Position Group", 
			"Add player to Team",
			"Remove a Player From Team",
			"Update Player Information",
			"View Positions on Team");
	
	private List<String> positions = Arrays.asList(
			"Offensive Skills Positions: QB, RB, FB, WR, TE",
			"Line: LT, LG, C, RT, RG, DT, NT, DE",
			"Defensive Skill Positions: OLB, MLB, CB, SS, FS");
			
	public void start() {
		String selection = "";
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayFullRoster();
				} else if (selection.equals("2")) {
					displayPositionGroup();
				} else if (selection.equals("3")) {
					signPlayer();
				} else if (selection.equals("4")) {
					cutPlayer();
				} else if (selection.equals("5")) {
					updatePlayer();
				} else if (selection.equals("6")) {
					viewPossiblePositions();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an option :\n----------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i+1 + ") " + options.get(i));
		}
	}
	
	private void displayFullRoster() throws SQLException {
		for (Player player : playerDao.getFullRoster()) {
			System.out.println(player.getPlayerId() + ") " + player.getName());
		}
	}
	
	private void displayPositionGroup() throws SQLException {
		System.out.println("Enter position Group: ");
		for (String position : positions) {
			System.out.println(position);
		}
		String position = scanner.nextLine();
		
		for (Player player : playerDao.getPositionGroup(position)) {
			System.out.println("Position Group: " + player.getPosition());
			System.out.print(player.getPlayerId() + ") " + player.getName() 
				+ " (" + player.getStarter() + ")\n");
		}
	}
	
	private void signPlayer() throws SQLException {
		System.out.print("Enter Player Full Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Player Position: ");
		String position = scanner.nextLine();
		System.out.print("Enter Playing Status (Starter / Backup): ");
		String starter = scanner.nextLine();
		
		playerDao.signNewPlayer(name, position, starter);
	}
	
	private void cutPlayer() throws SQLException {
		System.out.println("Enter ID of player to cut: ");
		int id = Integer.parseInt(scanner.nextLine());
		playerDao.cutPlayer(id);
	}
	
	private void updatePlayer() throws SQLException {
		System.out.print("Enter id of player to update: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Updated Full Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Updated Position: ");
		String position = scanner.nextLine();
		System.out.print("Enter Updated Playing Status: ");
		String starter = scanner.nextLine();
		
		playerDao.updatePlayerInfo(id, name, position, starter);
	}
	
	private void viewPossiblePositions() {
		System.out.println("All possible positions\n-------------------");
		for (String position : positions) {
			System.out.println("\t" + position);
		}
	}
	
}
