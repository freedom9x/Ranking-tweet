import java.util.HashMap;


public class Query {
	//private static HashMap<String,Double> topics;
	public static String TopicDectection(String querry){
		String topic= "";
		
		if (querry.contains("ipad")) topic = "iphone_5s";
		if (querry.contains("iphone 5s")) topic = "iphone_5s";
		if (querry.contains("kindlefire")) topic = "kindlefire";
		if (querry.contains("lg g2")) topic = "lg g2";		
		if (querry.contains("moto x")) topic = "moto x";
		if (querry.contains("nexus 5")) topic = "nexus 5";
		if (querry.contains("nexus 10")) topic = "nexus 10";
		if (querry.contains("surface 2")) topic = "surface 2";		
		return topic;
	}
}
