import java.io.IOException;


public class MainMain {
	
	
	public static void main(String[] args){
		int N = 200;
		double[][] Agreement_Graph = new double[N+1][N];
		Tweet[] tweets = new Tweet[200];
		String query;
		String topic;
		///////
		query = "nexus 5";
		try {
			tweets=ReadFile.GetNtweet(N, query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		ReadFile.WriteResult(tweets, "result/200tweets.txt", false, query);
		//////remove stop words
		ReadFile.WriteResult(tweets, "result/200tweets_rm_sw.txt", true, query);
		
		System.out.println("-------------------");	
	}
	private static void RemoveQuey(Tweet[] tweets)
	{
		for (Tweet tweet : tweets) {
			tweet.content = tweet.content.replaceAll("query", "");
		}
	}
}
