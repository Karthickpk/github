package fc.main;

public class FormateContantElements {

	public static StringBuilder getStringTag(String key, Object value, StringBuilder root) {
		return root.append("<string name=\"").append(key).append("\">").append(value).append("</string>");
	}
	
	public static StringBuilder getNumberTag(String key, Object value, StringBuilder root) {
		return root.append("<number name=\"").append(key).append("\">").append(value).append("</number>");
	}
	
	public static StringBuilder getBooleanTag(String key, Object value, StringBuilder root) {
		return root.append("<boolean name=\"").append(key).append("\">").append(value).append("</boolean>");
	}
	
	public static StringBuilder getArrayTag(String key, Object value, StringBuilder root) {
		return root.append("<array name=\"").append(key).append("\">").append(value).append("</array>");
	}
	
	public static StringBuilder getObjectTag(String key, Object value, StringBuilder root) {
		return root.append("<object name=\"").append(key).append("\">").append(value).append("</object>");
	}
	
	public static StringBuilder getNullTag(String key, Object value, StringBuilder root) {
		return root.append("<null name=\"").append(key).append("\"/>");
	}
	
	//Nested Tag
	
	public static StringBuilder getNestedStringTag(Object value, StringBuilder root) {
		return root.append("<string>").append(value).append("</string>");
	}
	
	public static StringBuilder getNestedNumberTag(Object value, StringBuilder root) {
		return root.append("<number>").append(value).append("</number>");
	}
	
	public static StringBuilder getNestedBooleanTag(Object value, StringBuilder root) {
		return root.append("<boolean>").append(value).append("</boolean>");
	}
	
	public static StringBuilder getNestedArrayTag(Object value, StringBuilder root) {
		return root.append("<array>").append(value).append("</array>");
	}
	
	public static StringBuilder getNestedObjectTag(Object value, StringBuilder root) {
		return root.append("<object>").append(value).append("</object>");
	}
}
