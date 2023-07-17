package ie.atu.sw;

public class Runner {
	public static void main(String[] args) {
		Menu m = new Menu();
		m.showHeader();
		try {
			Menu.showMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}