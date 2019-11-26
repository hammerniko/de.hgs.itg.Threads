package c4_ThreadsInSwingGui_JProgressbar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Gui extends JFrame {

	private static final String TITLE = "Threads in Swing";
	private static final String TU_WAS = "Tu was...";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	protected static final int MAX = 30;
	protected static final int MIN = 0;

	JPanel contenPane;
	JButton btnLoad;
	JProgressBar pb;
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Gui gui = new Gui();
				gui.setVisible(true);

			}
		});

	}

	public Gui() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);

		contenPane = new JPanel();
		contenPane.setLayout(new BorderLayout());
		btnLoad = new JButton(TU_WAS);
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clickedLoadButton();
			}
		});

		pb = new JProgressBar(MIN, MAX);
		contenPane.add(btnLoad, BorderLayout.CENTER);
		contenPane.add(pb, BorderLayout.SOUTH);
		setContentPane(contenPane);

	}

	/**
	 * Der erste ObjektParameter des SwingWorkers bestimmt den R�ckgabewert der doInBackground()-Methode.
	 * Es muss ein komplexer Datentyp zurueckgegeben werden. Dieser Wert kann mit get() aus der callback-Methode
	 * done() geholt werden.
	 * 
	 * Der zweite Objektparameter erm�glicht einen Fortschritt �ber die Methode
	 * publish() innerhalb der Methode doInBackground() w�hrend der Laufzeit des SwingWorker-Threads abzusetzen
	 * Der Wert kann dann in der callbackMethode process() abgerufen werden.
	 * 
	 */
	protected void clickedLoadButton() {
		
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				
				//Irgendetwas tun was l�nger dauert
				for (int i = 0; i < 30; i++) {
					Thread.sleep(100);
					
					//Testausgabe auf der Konsole
					System.out.println("Hello"+i);
					
					//Datentyp bestimtm durch den 2.ten Objektparameter 
					//Sende den Wert der Variablen raus
					publish(i);
					
				}
				
				//R�ckgabewert wenn doInBackground fertig ist (Kann mit get() in done() abgerufen werden
				return true;
			}
			
			
			/**
			 * Der Aufruf von process w�hrend des Threads kann nicht garantiert werden,
			 * weshalb eine ganze Liste von Objekten des zweiten Objektparameters �bergeben wird.
			 */
			@Override
			protected void process(List<Integer> chunks) {
				// TODO Auto-generated method stub
				super.process(chunks);
				
				//letzten Integerwert aus der Liste holen
				int value = chunks.get(chunks.size()-1);
				
				//Progressbar aktualisieren
				pb.setValue(value);
				btnLoad.setText(TU_WAS+" "+value);
				
			}
			
			

			
			@Override
			protected void done() {
				
				super.done();

				try {
					
					//nach erledigter Arbeit Threadsicher ein Swing-Interface hier updaten 
					//get holt den Boolschen Wert aus der doInBackgound Methode
					Boolean status = get();
					btnLoad.setText("Status"+status);
					System.out.println("Swingworker finished");
					pb.setValue(MAX);
					
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				
			}
			
		};
		
		//Swing Thread starten als Hintergundthread starten
		worker.execute();

	}
}
