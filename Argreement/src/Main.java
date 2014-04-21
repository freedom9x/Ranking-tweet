import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Tweet[] tweets = new Tweet[200];
		String query;
		String topic;
		int N = 200;
		//6tweet test deu lien quan den cau query amazaon
		query = "kindlefire";
		topic = Query.TopicDectection(query);
		try {
			tweets=ReadFile.GetNtweet(N, topic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
//		tweets[0] = new Tweet("@stellargirl I love my Kindle2. Not that the DX is cool, but the 2 is fantastic in its own right.");
//		tweets[1] = new Tweet("Reading my kindle2...  Love it... Lee childs is good read.");
//		tweets[2] = new Tweet("Ok, first assesment of the #kindle2 ...it fucking rocks!!!");
//		tweets[3] = new Tweet("@kenburbary You'll love your Kindle2. I've had mine for a few months and never looked back. The new big one is huge! No need for remorse! :)");
//		tweets[4] = new Tweet("@mikefish  Fair enough. But i have the Kindle2 and I think it's perfect  :)");
//		tweets[5] =  new Tweet("@richardebaker no. it is too big. I'm quite happy with the Kindle2.");
//		
		for(int i = 0; i<200; i++)
		{
			tweets[i].rmv_stopword();
			tweets[i].content = tweets[i].content.replace(query, "");//bo tu query
			tweets[i].content = tweets[i].content.replaceAll("( )+", " ");
			System.out.println(tweets[i].content);
			
		}
		for (Tweet tweet : tweets) {
			System.out.println(tweet.ID+"\t"+tweet.content);
		}

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
		
		System.out.println("-------------------");	

	}

}
