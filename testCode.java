import java.io.IOException;
public class testCode {
    public static void clearConsole() {
        // Clearing the console by printing a newline character multiple times
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        try {
            // Disable line buffering so that each character is available immediately
            System.setIn(System.in);

            // Loop to continuously read single characters
            while (true) {
                // Read a single character from the console
                int input = System.in.read();

                // Check if the input is not the end of the stream or a newline character
                if (input == -1 || input == '\n' || input == '\r') {
                    break; // Exit loop if end of stream or newline character is encountered
                }

                // Convert the input integer to a character and do something with it
                char character = (char) input;
                System.out.println("You entered: " + character);

                // Add your game logic here, handling the character input
                // For example, you can process each character for your game
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions
        }
    }
}
