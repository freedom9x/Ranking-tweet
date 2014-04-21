import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TFIDF {
	
	public static double TF_term(String term, String text)
	{
		 double max_TF = 0 ;
		 List<String> list = Arrays.asList(text.split(" "));
		 Set<String> uniqueWords = new HashSet<String>(list);
		 
		 for(String word : uniqueWords)
		 {
			 double term1 = Collections.frequency(list, word);
			 if(max_TF<term1) max_TF = term1;
		 }
		 return Collections.frequency(list, term)/max_TF;
	}
	
    public static double IDF_term(String term, Tweet[] tweets, int N)
    {
    	int n = 0;
    	for(Tweet t : tweets)
    		if(Term_in_text(term, t.content))
    			n = n + 1;
  	
    	double idf = Math.log(N/n);
    	return idf;
    }
    
    public static boolean Term_in_text (String term, String text)
    {
    	List<String> list = Arrays.asList(text.split(" "));
    //	Set<String> uniqueWords =  new HashSet<String>(list);
    	if(Collections.frequency(list, term)==0) return false;
    	return true;
    }
    
   	public static List<String> Get_same(String text1, String text2)
    {
    	List<String> result = new ArrayList<String>(); 
    	List<String> list1 = Arrays.asList(text1.split(" "));
    	List<String> list2 = Arrays.asList(text2.split(" "));
    	
    	Set<String> uni_text1 = new HashSet<String>(list1);
    	Set<String> uni_text2 = new HashSet<String>(list2);
    	for (String string : uni_text1) {
			if(uni_text2.contains(string) && string!=" " && string!="")
				result.add(string);
			
		}
    	
    	return result;
    }
   	public static double Agreement(String text1, String text2, Tweet[] tweets, int N) throws IOException
   	{
//   		tweets[i].Remove1();
//		tweets[i].rmv_stopword();
//		tweets[i].content = tweets[i].content.replace(query, "");//bo tu query
//		tweets[i].content = tweets[i].content.replaceAll("( )+", " ");
		if(text1.isEmpty()|text1.isEmpty()) return 0.0;
		
   		double ag_score=0.0;
   		List<String> same_term = Get_same(text1, text2);
   		if(same_term.isEmpty()) return 0.0;
   		for (String term : same_term) {
			ag_score+=  TF_term(term, text1) + TF_term(term, text2) + 
					IDF_term(term, tweets, N)+ Tagger.Tag(term);
		}
   		//same_term.get(0).
   		return ag_score;
   	}
    //public static boolean
}
