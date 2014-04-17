import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReadFile {
	public static List<String> GetTweetFromFile(String file_name, String file_result)
	{
		List<String> result = new ArrayList<String>();;
		try(BufferedReader reader = new BufferedReader(new FileReader(file_name))) {	
			String line;
			while ((line = reader.readLine())!= null) {
				//do not thing
				//line = reader.readLine();
				result = ParseTweet(line);
			}
		} catch (IOException x) {
			// TODO Auto-generated catch block
			System.err.format("IOException: %s%n", x);
		}
		return result;
	}
	public static String GetTweet(String text)
	{
		String result= "";
		String key1= "\"text\":";
		String key2="\"id\":";
		String id;
		String content;
		int index_of_text=0;
		int dem= 0;
		while (text.indexOf(key1, index_of_text)>0) {
			
		//	System.out.println(key1+"\t"+index_of_text);
			
			
			dem = dem +1;
			index_of_text = text.indexOf(key1, index_of_text);
			int term_index = text.indexOf(", \"", index_of_text); //index cua dau phay
			
			System.out.println(key1+"\t"+index_of_text + "\t" + text.substring(index_of_text+key1.length(), term_index));
			
			index_of_text = text.indexOf(key1, index_of_text)+1;
		}
		System.out.println(dem);
		
		
		
		
		return result;
	}
	public static List<String> ParseTweet(String text)//get tweet_content, id, lang
	{
		List<String> result = new ArrayList<String>();
		String text_term = "\"text\":";
		String id_term = "\"id\":";
		String lang_term = "\"lang\": \"en\"";//loc ngon ngu
		int dem = 0;	
		List<String> text_s = Arrays.asList(text.split("\"contributors\":"));
		for (String string : text_s) {
			//if(!(string.contains(id_term)&&string.contains(lang_term)) ) continue;//kiem tra dk
			//tweet content
			if(string.contains(id_term)&&string.contains(lang_term)&& !string.contains("\"\\u"))
			{
				String tweet_content="";
				String id ="";
				dem = dem + 1;
				//lay tweet

				tweet_content = GetInfo(text_term, string);
				id = GetInfo(id_term, string);
				//end_of_
			//	System.out.println(dem+"\t"+ id+"\t"+tweet_content);
				//String[] aa =  new String[90];
				result.add(dem+" "+id+" "+tweet_content);
			}
			
		}
		return result;
		
	}
	public static String GetInfo(String key, String string)
	{
		String result= "";
		int index_of_text = string.indexOf(key);
		int end_of_text = string.indexOf(", \"", index_of_text);
		result = string.substring(index_of_text+key.length(), end_of_text);
		
		return result;
	}
	public static void WriteResult(String line, String filename)
	{
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		String newLine = System.getProperty("line.separator");
		writer.write(line+newLine);
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public static void CreateDataset(String path_data, String path_dataset) throws IOException
	{
		File folder = new File(path_data);
		File[] listOfFiles = folder.listFiles();
		int i = 0;
		int total = listOfFiles.length;
		int dem = 0;
		for (File file : listOfFiles) {
			i++;
			if(file.isFile()&&file.length()>1024)
			{
				dem = dem +1;
				String path_result = path_dataset+"/"+dem+".txt";
			//	System.out.println(file.getName()+"\t"+ file.getPath());
				@SuppressWarnings("unused")
				List<String> term = GetTweetFromFile(file.getPath(), path_dataset);
				PrintWriter pw = new PrintWriter(new FileWriter(path_result));
				for (String string : term) {			
					//String newLine = System.getProperty("line.separator");
					pw.println(string);		
				}
				pw.close();
				System.out.println(i+"\\"+total+ "\t"+ file.getName()+"\tdone");
			}				
		}
		
		
		
	}
}
