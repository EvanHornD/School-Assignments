public class testCode {
    public static void clearConsole() {
        // Clearing the console by printing a newline character multiple times
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
