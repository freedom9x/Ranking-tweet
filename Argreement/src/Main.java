import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int N = 200;
		double[][] Agreement_Graph = new double[N+1][N];
		Tweet[] tweets = new Tweet[200];
	//	Tweet[][] Agreement_Graph = new Tweet[N+1][N];
		String query;
		String topic;
		
		//6tweet test deu lien quan den cau query amazaon
		query = "nexus 5";
	//	topic = Query.TopicDectection(query);
		try {
			tweets=ReadFile.GetNtweet(N, query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
	

//		
		for(int i = 0; i<200; i++)
		{
			tweets[i].Remove1();
			tweets[i].rmv_stopword();
			tweets[i].content = tweets[i].content.replace(query, "");//bo tu query
			tweets[i].content = tweets[i].content.replaceAll("( )+", " ");
		//	System.out.println(tweets[i].content);
			
		}
		ReadFile.WriteResult(tweets, "result/200tweets.txt");
//		for (Tweet tweet : tweets) {
//			System.out.println(tweet.ID+"\t"+tweet.content);
//		}
//		
		System.out.println("-------------------");	
		
        //tinh agreemnet
		for(int i = 0; i < N-1; i++)
		{
			PrintWriter pw = new PrintWriter(new FileWriter("result/AGgraph-.txt"+i+".txt"));
			for(int j = i + 1; j < N; j++)
			{		
					Agreement_Graph[i][j]=TFIDF.Agreement(tweets[i].content, tweets[j].content, tweets, N);
					System.out.print(i+"\\"+j+"="+Agreement_Graph[i][j]+"  ");					
					pw.println(i+"\\"+j+"="+Agreement_Graph[i][j]+"  ");
					
			}
			System.out.println();
			pw.close();
		}
		
		System.out.println("-------------------");	
		
//		System.out.println(TFIDF.IDF_term("love", tweets, 6));
//		List<String> term = TFIDF.Get_same(tweets[0].content, tweets[1].content);
//		for (String string : term) {
//			System.out.println(string);
//		}
//		
//		double ag = TFIDF.Agreement(tweets[0].content, tweets[1].content, tweets, 6);
//		
//		NumberFormat formatter = new DecimalFormat("#0.00");
//		System.out.println(formatter.format(ag));
		
		

	}

}
