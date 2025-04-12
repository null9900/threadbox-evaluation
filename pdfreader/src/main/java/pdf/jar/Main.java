package pdf.jar;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.itextpdf.forms.xfa.XfaForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import threadbox.Threadbox;

import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor; 

public class Main {
	
    private static void displayPDFTextInNewWindow(String pdfText) {
        JFrame pdfFrame = new JFrame("PDF Viewer");
        pdfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pdfFrame.setSize(600, 400);
        JTextArea textArea = new JTextArea(pdfText);
        JScrollPane scrollPane = new JScrollPane(textArea);
        pdfFrame.add(scrollPane);
        pdfFrame.setVisible(true);
    }
    
    private static String parsePDF(String filePath) {
		try {
			PdfReader reader = new PdfReader(filePath);
        	PdfDocument pdfDoc = new PdfDocument(reader);
        	XfaForm xfaForm = new XfaForm(pdfDoc);
        	return PdfTextExtractor.getTextFromPage(pdfDoc.getFirstPage(), new SimpleTextExtractionStrategy());
		}catch(IOException e) {
			System.out.print(e.toString());
			return "";
		}	
	}
    
	private static void parseAndDisplayPDF(String filePath) {
		new Thread(() -> {
			Threadbox.permissions("rpath unix", "PDF parser thread", false);
			String text = parsePDF(filePath);
			displayPDFTextInNewWindow(text);	   
		}).start();
	}
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("PDF Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        JFileChooser fileChooser = new JFileChooser();
        JButton openButton = new JButton("Open PDF");
        frame.setLayout(new BorderLayout());
        frame.add(openButton, BorderLayout.NORTH);
        frame.setVisible(true);
        openButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                parseAndDisplayPDF(selectedFile.toString());
            }
        });
    }
    
	public static void main(String args[]) {
		Threadbox.sandbox_ps();
    	Threadbox.permissions("unix rpath threading", "", false);
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
	}
}
