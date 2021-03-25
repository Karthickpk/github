package fc.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XMLConvertersion implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public boolean convertJSONtoXML(File f1, File f2) {
		Scanner json_scan = null;
		FileOutputStream fos = null;
		String firstTag = "<object>";
		String endTag = "</object>";
		
		try {
			StringBuilder line = new StringBuilder();
			json_scan = new Scanner(f1);
			while (json_scan.hasNextLine()) {
				line.append(json_scan.nextLine());
			}

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(line.toString(), Map.class);
			Set<Entry<String, Object>> content = map.entrySet();

			fos = new FileOutputStream(f2, true);
			if (getBaseXMLElment(content) != null) {
				byte[] b = (firstTag + getBaseXMLElment(content) + endTag).getBytes();
				fos.write(b);
				return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (json_scan != null)
					json_scan.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static String getBaseXMLElment(Set<Entry<String, Object>> elments)
			throws JsonMappingException, JsonProcessingException {
		StringBuilder baseRoot = new StringBuilder();
		for (Entry<String, Object> it : elments) {
			if (it.getValue() instanceof String) {
				FormateContantElements.getStringTag(it.getKey(), it.getValue(), baseRoot);
			} else if (it.getValue() instanceof Integer) {
				FormateContantElements.getNumberTag(it.getKey(), it.getValue(), baseRoot);
			} else if (it.getValue() instanceof Double) {
				FormateContantElements.getNumberTag(it.getKey(), it.getValue(), baseRoot);
			} else if (it.getValue() instanceof Boolean) {
				FormateContantElements.getBooleanTag(it.getKey(), it.getValue(), baseRoot);
			} else if (it.getValue() instanceof ArrayList) {
				FormateContantElements.getArrayTag(it.getKey(), getChildArrayElement((List<Object>) it.getValue()), baseRoot);
			} else if (it.getValue() instanceof Object) {
				Set<Entry<String, Object>> s = ((Map) it.getValue()).entrySet();
				FormateContantElements.getObjectTag(it.getKey(),getBaseXMLElment(s), baseRoot);
			} else if (it.getValue() == null) {
				FormateContantElements.getNullTag(it.getKey(), it.getValue(), baseRoot);
			}
		}
		return baseRoot.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getChildArrayElement(List<Object> vale) throws JsonMappingException, JsonProcessingException {
//		System.out.println(vale);
		StringBuilder baseRoot = new StringBuilder();
		for (int i = 0; i < vale.size(); i++) {
			if (vale.get(i) instanceof String) {
				FormateContantElements.getNestedStringTag(vale.get(i), baseRoot);
			} else if (vale.get(i) instanceof Integer) {
				FormateContantElements.getNestedNumberTag(vale.get(i), baseRoot);
			} else if (vale.get(i) instanceof Double) {
				FormateContantElements.getNestedNumberTag(vale.get(i), baseRoot);
			} else if (vale.get(i) instanceof Boolean) {
				FormateContantElements.getNestedBooleanTag(vale.get(i), baseRoot);
			} else if (vale.get(i) instanceof ArrayList) {
				FormateContantElements.getNestedArrayTag(getChildArrayElement((List<Object>) vale.get(i)), baseRoot);
			} else if (vale.get(i) instanceof Object) {
				Set<Entry<String, Object>> s = ((Map) vale.get(i)).entrySet();
				FormateContantElements.getNestedObjectTag(getBaseXMLElment(s), baseRoot);
			} 
		}
		return baseRoot.toString();
	}
}