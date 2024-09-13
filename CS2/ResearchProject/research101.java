
import java.io.IOException;

public class research101 {
    public static void main(String[] args) throws IOException {
        xmlReader reader = new xmlReader("School-Assignments\\CS2\\ResearchProject\\cwec_v4.15.xml");
        reader.printWeaknessNames();
    }
}
