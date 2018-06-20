package com.les_napoleons;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StatsUtils {
    private static final String API_KEY = "1408717b9334938b2118f4e62448025d";
    private TwitterFactory factory;
    private Twitter twitter;
    private final String API_URL = "http://api.twittercounter.com/";

    public StatsUtils() {
        this.factory = new TwitterFactory();
        this.twitter = factory.getInstance();
        this.twitter.setOAuthConsumer("6WcdrVzkqTuDvEZMQeLYNnK48", "aBt2toj0sXiARBht5wh65ddeJ2ndrrSw8Pl87zUsl5iFac2Y25");
        AccessToken accessToken = new AccessToken("2467520784-CHESBQLEo2rKXm1d2K2wdPrPs1XeXhAMMJK5Gps", "kFHXnwmjYIAyylI4qKt1m8ke2iGOKAZW99UKIWtFe7CVi");
        this.twitter.setOAuthAccessToken(accessToken);
    }

    public String getAPI() throws IOException {
        String request = API_URL + "?apikey=" + API_KEY + "&twitter_id=15160529#_ga=2.156675132.646665159.1529437898-627797711.1529437898";
        URL url = new URL(request);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        System.out.println(status);
        if (status != 200 ) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            return content.toString();
        } else {
            return "An error has occured";
        }

    }


    public void getTwitterFollowers() {
        try {
            List<Long> followerList = new ArrayList<Long>();
            long cursor = -1L;
            IDs ids;
            do {
                ids = this.twitter.getFollowersIDs(cursor);
                for (long userID : ids.getIDs()) {
                    followerList.add(userID);
                }
            } while ((cursor = ids.getNextCursor()) != 0);
            //getFollowersIds only returns 5k ids, so a cursor is needed to run through all the list
            System.out.println(followerList.size());
        } catch (TwitterException ignored) {
        }
    }
}