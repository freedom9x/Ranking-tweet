import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;


public class TopK_result {

	Tweet[] tweets =  new Tweet[200];
	static double[][] AG_graph = new double[201][200];
	static int K =30;
	static int[] topK= new int[30];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = 200;
		Tweet[] tweets = new Tweet[200];
		ReadFile.GetTweetResult(tweets, "result/200tweets.txt", N);
		ReadFile.GetAG(AG_graph, N, "result/AG_graph");
		ReadFile.RankingAG(tweets, AG_graph, N);
		//topK = ReadFile.TopK_AG(K, AG_graph, N);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("result/RankingResult.txt"));
		for(int i = 0 ; i < N; i++)
		{
			System.out.println(tweets[i].total_AG);
			writer.write(i+" "+tweets[i].ID+" \""+tweets[i].content+"\""+
					" "+"total_AG="+tweets[i].total_AG+"\n");
		}
		writer.close();
		System.out.print("cho");
	}
	
}
