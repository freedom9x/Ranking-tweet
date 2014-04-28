import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class AG_graph {

	public static String query = "nexus 5";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N = 200;
		String query;
		double[][] Agreement_Graph = new double[N+1][N];
		Tweet[] tweets = new Tweet[200];
		//query = "nexus 5";
		//lay 200 tweet
		try {
			tweets=ReadFile.GetNtweet(N, AG_graph.query);
			//------save result
			ReadFile.WriteResult(tweets, "result/200tweets.txt", false, AG_graph.query);
			//////remove stop words
			ReadFile.WriteResult(tweets, "result/200tweets_rm_sw.txt", true, AG_graph.query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		System.out.println("-------------------");	
		 //tinh agreemnet
		for(int i = 0; i < N; i++)
		{
			PrintWriter pw = new PrintWriter(new FileWriter("result/AG_graph/AGgraph-"+i+".txt"));
			for(int j = 0; j <N; j++)
			{		
					if(i==j)
					{
						Agreement_Graph[i][j]=0.0;
						continue;
					}
					Agreement_Graph[i][j]=TFIDF.Agreement(ReadFile.RemoveAllSW(tweets[i].content, AG_graph.query),
							ReadFile.RemoveAllSW(tweets[j].content, AG_graph.query), tweets, N);
					System.out.print(i+"\\"+j+"="+Agreement_Graph[i][j]+"  ");					
					pw.println(i+"\\"+j+"="+Agreement_Graph[i][j]+"  ");
					
			}
			System.out.println();
			pw.close();
		}
		
		System.out.println("-------------------");	
		
	}

}
