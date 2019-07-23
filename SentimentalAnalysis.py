import json
import nltk

from tweepy import Stream
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from ConfigParser import SafeConfigParser
#nltk.download('vader_lexicon')
from nltk.sentiment.vader import SentimentIntensityAnalyzer

class MyListener(StreamListener):

    def on_data(self, data):
        all_data = json.loads(data)
        tweet = all_data["text"]
        ss = sid.polarity_scores(tweet)
        print ss
        print tweet + '\n'
        return True

    def on_error(self, status):
        print(status)
        return True

sid = SentimentIntensityAnalyzer()

config = SafeConfigParser()
config.read('config.ini')

consumer_key = config.get('twitter_api_key', 'consumer_key')
consumer_secret = config.get('twitter_api_key', 'consumer_secret')
access_token = config.get('twitter_api_key', 'access_token')
access_secret = config.get('twitter_api_key', 'access_secret')

auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_secret)

twitter_stream = Stream(auth, MyListener())
twitter_stream.filter(track=['@wall'])
