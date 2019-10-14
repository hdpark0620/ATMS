import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sample.HelloWorld;
import com.sample.common.Controller;

public class App extends Controller {
    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    public static void main(String[] args) {
    	
    	SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
                initDB();
            }
        });
    }
    
    private static void createAndShowGUI() {
    	HelloWorld panel = new HelloWorld();
        JFrame frame = new JFrame("Histogram Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }
    
    private static void initDB() {
    	// DB connection
        try  {
        	 
        	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            System.out.println("Java JDBC PostgreSQL Example");
 
            System.out.println("Connected to PostgreSQL database!");
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
