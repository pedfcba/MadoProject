package mado.person;

import weibo4j.Account;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mado.object.AllEffect;
import mado.object.MapInfo;
import mado.object.Tags;
import mado.speak.SpeakBehavior;
import mado.xml.ParseConfigNodes;
import mado.xml.ParseEffectNodes;
import mado.xml.ParseMapNodes;
import mado.xml.ParsePersonNodes;


public class MadoParser{
	private static ParseConfigNodes config;
	private static ParseMapNodes map;
	private static ParsePersonNodes person;
	private static MadoSpeak speaker;
	Random rand = new Random();
	private String lastsentence;


	public void wakeUp()
	{
		person.getPerson().setWake(Tags.wake);
	}

	public MadoParser()
	{
		config = new ParseConfigNodes();
		map = new ParseMapNodes();
		person = new ParsePersonNodes();
		speaker = new MadoSpeak();
		lastsentence = "";
	}

	public void createPerson(String name)
	{
		person.parseNodes(config.getAdress(name));
		map.parseNodes(config.getAdress(person.getPerson().getLocation()));
	}

	public void changeMap(String name)
	{
		map.parseNodes(config.getAdress(name));
	}

	public void pickEffect()
	{
		List<String> effects = map.getMap().getEffectinfo();
		for(int i = 0; i < effects.size(); i++)
			person.getPerson().addEffect(effects.get(i));
		person.refreshXml();
	}


	public void viewInfo()
	{
		System.out.println("人物：");
		person.getPerson().viewInfo();
		System.out.println();
		System.out.println("地图：");
		map.getMap().viewInfo();
	}

	public String performActivity()
	{
		String sentence = "";
		sentence = speaker.speak(map, person);
		//System.out.println(sentence);
		if(!lastsentence.equals(sentence))
			lastsentence = sentence;
		else
			return performActivity();
		return sentence;
	}

	public String getEffects()
	{	
		ParseEffectNodes effect = new ParseEffectNodes();
		LinkedList<String> link = (LinkedList<String>) effect.getEffectList(person.getPerson().getEffects());
		String effects = "目前收集到的EFFECT：";
		if(!link.isEmpty())
		{
			int j = 0;
			for(; j < link.size()-1; j++)
			{
				effects += link.get(j)+ "、";
			}
			effects += link.get(j);
			effects += "。已经有" + link.size() + "个了~";
		}
		else
			effects += "一个都没有呢。。。";
		return effects;
	}

	//发送微博
	private void sendTweet(String accessToken, long remainseconds) {
		// TODO Auto-generated method stub
		Weibo weibo = new Weibo();
		weibo.client.setToken(accessToken);
		Timeline timeline = new Timeline();
		timeline.client.setToken(accessToken);
		Random rand = new Random();
		//获取剩余次数需要Account对象
		Account am = new Account();
		am.client.setToken(accessToken);
		int refresh = rand.nextInt(160000);
		remainseconds *= 1000;
		try {
			int count = 0;
			while(remainseconds > 0)
			{
				//一小时的计数器，若剩余时间不足一小时，替换成剩余时间
				long hour = 3600000;
				if(hour > remainseconds)
					hour = remainseconds;
				//剩余限制次数
				long limit = am.getAccountRateLimitStatus().getApiRateLimit().get(0).getRemainingHits();
				//一小时内发出limit条微博
				while(limit > 0)
				{
					count++;
					if(count % 20 == 0)
					{
						timeline.UpdateStatus(getEffects());
					}
					else
						timeline.UpdateStatus(performActivity());
					//平均每次间隔的上限
					int reflimit = (int)(hour/limit);
					refresh = rand.nextInt(reflimit);
					while(refresh < 60000 && reflimit >= 60000)
					{
						refresh = rand.nextInt(reflimit);
					}
					remainseconds -= refresh;
					System.out.println("sleep: " + refresh);
					System.out.println("limit: " + limit);
					if(hour > refresh)
						hour -= refresh;
					else
						break;
					Thread.sleep(refresh);
					limit = am.getAccountRateLimitStatus().getApiRateLimit().get(0).getRemainingHits();
				}
				if(limit > 0)
					timeline.UpdateStatus(performActivity());
				if(hour > 0)
					Thread.sleep(hour);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MadoParser mado = new MadoParser();
		mado.createPerson("附窗子");

		/*				for(int i = 1; i < 100; i++)
		{
			System.out.println(mado.performActivity());
		}
		while(!mado.person.getPerson().isWaking())
		{
			System.out.println(mado.performActivity());
		}
		System.out.println(mado.getEffects());

		 */

		String accessToken = "2.009LT_VDOayDYE70c8cd9cdd4Bk_7C";
		//String status = "灯下的我，在写日记";
		mado.sendTweet(accessToken, 3600);

	}

}
