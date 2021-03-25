package fc.main;

import java.io.File;
import java.util.Scanner;

public class JSONXMLConversion {

	public static void main(String[] args) {
		
		// Input Example
		// Json File - XML File
		// {File Path}/{File Name} {File Path}/{File Name}
		// Sample:
		// C:\Users\KARTHIK\Desktop\Assessment\examples\example.json C:\Users\KARTHIK\Desktop\Assessment\examples\json_example.xml

		Scanner in = new Scanner(System.in);

		String json_file = in.next();
		String xml_file = in.next();

		Converter convert = new XMLConvertersion();
		if (convert.convertJSONtoXML(new File(json_file), new File(xml_file))) {
			System.out.println("File converted successfully!!");
		} else {
			System.out.println("Failed to Conversion");
		}
		in.close();
	}

}
