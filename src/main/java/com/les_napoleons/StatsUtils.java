package com.les_napoleons;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class StatsUtils {

    public void getTwitterFollowers() {

        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("6WcdrVzkqTuDvEZMQeLYNnK48", "aBt2toj0sXiARBht5wh65ddeJ2ndrrSw8Pl87zUsl5iFac2Y25");
        AccessToken accessToken = new AccessToken("2467520784-CHESBQLEo2rKXm1d2K2wdPrPs1XeXhAMMJK5Gps", "kFHXnwmjYIAyylI4qKt1m8ke2iGOKAZW99UKIWtFe7CVi");
        twitter.setOAuthAccessToken(accessToken);
        //TODO https://twittercounter.com/pages/api
        //TODO Request API key data in JSON, parse JSON etc
        String twitterScreenName;
        try {
            List<Long> followerList = new ArrayList<Long>();
            long cursor = -1L;
            IDs ids;
            do {
                ids = twitter.getFollowersIDs(cursor);
                for (long userID : ids.getIDs()) {
                    followerList.add(userID);
                }
            } while ((cursor = ids.getNextCursor()) != 0);
            //getFollowersIds only returns 5k ids, so a cursor is needed to run through all the list
            System.out.println(followerList.size());

            /*IDs followerIDs = twitter.getFollowersIDs(twitterScreenName, -1);
            long[] ids = followerIDs.getIDs();
            IDs id = twitter.getFriendsIDs(20000);

            IDs test = twitter.getFollowersIDs(twitterScreenName,);
            System.out.println(test.getIDs().length);

            //System.out.println(twitter.showUser(ids[159]).getScreenName());
            //System.out.println(twitter.showUser(ids[159]).getLocation());
            //System.out.println(twitter);*/

            /*for (long id : ids) {
                twitter4j.User user = twitter.showUser(id);
                //here I am trying to fetch the followers of each id
                String userScreenName = user.getScreenName();
                System.out.println("Name: " + user.getScreenName());
                System.out.println("Location:" + user.getLocation());*/

                /* This block gets followers of followers which is.. fine I guess?

                IDs followerIDsOfFollowers = twitter.getFollowersIDs(user.getScreenName(), -1);
                long[] fofIDs = followerIDsOfFollowers.getIDs();
                for (long subId : fofIDs) {
                    twitter4j.User user1 = twitter.showUser(subId);
                    System.out.println("Follower Master:" + userScreenName + " Follower of Follower Name: " + user1.getScreenName());
                    System.out.println("Location:" + user1.getLocation());

                }*/
            //}

        } catch (TwitterException e) {
            System.out.println(e.getMessage());
        }
    }
}