import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
				System.out.println(i+"\\"+total+ "\t"+path_dataset+"/"+ file.getName()+"\tdone");
			}				
		}	
	}
	//doc 200 tweet
	public static Tweet[] GetNtweet (int N, String querry) throws IOException
	{
		Tweet[] tweets = new Tweet[N];
		String topic = Query.TopicDectection(querry);
		int i = 0;
		File folder = new File("dataset/"+topic);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if(file.length()==0) continue;
			System.out.println("-------------------"+file.getName());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String term;
			if(i==N) return tweets;
			while((term = reader.readLine())!=null)
			{
				if(i==N) return tweets;
				long id;
				String content ="";
				String[] parts = term.split("  ");
				id = Long.parseLong(parts[1]);
				for(int j = 2 ; j<parts.length; j++)
				{
					parts[j].replace("\"", "");
					content += parts[j];
				}
				tweets[i] = new Tweet(content, id);
				i++;
			}
			reader.close();
		}
		return tweets;
	}
	public static void Create() throws IOException
	{
		File folder1 = new File("data");
		
		File[] listOfFiles = folder1.listFiles();
		for (File file : listOfFiles) {
			String path_data = "data/"+file.getName();
			String path_dataset = "dataset/"+file.getName();
			File folder_dataset = new File(path_dataset);
			folder_dataset.mkdir();
			System.out.println("-------------"+path_dataset+"-------------------------");
			ReadFile.CreateDataset(path_data, path_dataset);
			
		}
	}
}
