import java.awt.HeadlessException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Joel Parfitt - 23020948
 */

// Start of Program -----------------------------

public class Lecturer {

    public static void moduleAnnouncement() {
        final JPanel panel = new JPanel();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter module annoucment to broadcast to all users:");
            String announcement = scanner.nextLine();

            JOptionPane.showMessageDialog(panel, announcement, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (HeadlessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
