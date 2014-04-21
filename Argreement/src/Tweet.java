

public class Tweet {
	//9 features
	long ID;
	String user_name;
	String content;
	String img_url;
	String source;
	int retweet_count;
	int follow_count;
	int friend_count;
	String hash_tag;
	int feature_score;
	int rank_score;
	
	/*public Tweet(String text_info)//example: text_info= "id username  follow_count friend_count img_url source retweet_count content"
	{
		text_info = pre_process(text_info);// pre process text_info
		String[] part = text_info.split(" ");// 8 part
		
		for(int i = 0; i<9; i++)
		{
			switch (i) {
			case 0:
				ID = Integer.parseInt(part[i]);
			case 1:
				user_name = part[i];
			case 2:
				content= part[i];
			case 3:
				img_url = part[i];
			case 4:
				source = part[i];
			case 5:
				retweet_count = Integer.parseInt(part[i]);
			case 6:
				follow_count = Integer.parseInt(part[i]);
			case 7:
				friend_count = Integer.parseInt(part[i]);
			case 8:
				hash_tag = part[i];
				break;

			default:
				break;
			}
		}
	}*/
	public Tweet(String content, long id)
	{
		this.content = content;
		this.ID = id;
	}

	public Tweet(String content) {
		// TODO Auto-generated constructor stub
		this.content = content;
	}
	public void rmv_stopword() {
		// TODO Auto-generated method stub
		this.content = this.content.replace("(", " ");
		this.content = this.content.replace(")", " ");
		this.content = this.content.replace("\"", " ");
		this.content = this.content.replace("“", " ");
		this.content = this.content.replace("#", " ");
		this.content = this.content.replace(",", " ");
		this.content = this.content.replace(".", " ");
		this.content = this.content.replace("-", " ");
		this.content = this.content.replace(";", " ");
		this.content = this.content.replace(":", " ");
		this.content = this.content.replace("!", " ");
		this.content = this.content.replace("...", " ");
		this.content = this.content.trim().replaceAll("\\s+", " ");
		//remove
//		for( int i = 0; i<this.content.length(); i++)
//		{
//			int temp = (int)content.charAt(i);
//		}
		String [] stop_words = {"a", "an", "and", "are", "as", "at", "be", "but", "by",
				"for", "if", "in", "into", "is", "it",
				"no", "not", "of", "on", "or", "such",
				"that", "the", "their", "then", "there", "these",
				"they", "this", "to", "was", "will", "with"};
		
		//String[] parts = this.content.split(" ");
		
		for (String string : stop_words) {
			if(content.contains(string))
			{
				String regex = "\\s*\\b"+string+"\\b\\s*";
				content=content.replaceAll(regex, " ");
			}
		}
		content= content.toLowerCase();
		this.content = this.content.replaceAll("( )+", " ");
		
	}
}
