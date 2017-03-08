package chat.model;

import chat.controller.ChatController;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.*;


public class CTECTwitter 
{
	private ChatController baseController;
	private Twitter twitterBot;
	private List<Status> searchedTweets;
	private List<String> ignoredWords;
	private List<String> tweetedWords;
	private List<Status> allTheTweets;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		twitterBot = TwitterFactory.getSingleton();
		ignoredWords = new ArrayList<String>();
		searchedTweets = new ArrayList<Status>();
		
	}
	
	private String [] createIgnoredWordsArray()
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		{
			wordCount++;
		}
		boringWordScanner.close();
		
		boringWords = new String [wordCount];
		
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();
		
		return boringWords;
	}
	
	private void removeBoringWords()
	{
		String [] boringWords = createIgnoredWordsArray();
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void removeBlankWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	public void sentTweet(String textToTweet)
	{
		try
		{
			twitterBot.updateStatus(textToTweet + " @chatbotCTEC ");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
	
	public void createIgnoredWordList()
	{
		
	}
	
	public void collectTweets(String username)
	{
		
	}
	
	
	public String getMostPopularWord(String username)
	{
		removeBoringWords();
		removeBlankWords();
		return "";
	}
	public String getMostCommonWord()
	{
		return null;
	}
}
