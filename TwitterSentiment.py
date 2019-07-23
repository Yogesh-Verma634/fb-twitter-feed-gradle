import nltk
nltk.download(vader_lexicon)
from nltk.sentiment.vader import SentimentIntensityAnalyzer

hotel_rev = ["Just went and tried to buy pjs but they were not ringing half price and the lady."]
  
sid = SentimentIntensityAnalyzer()
for sentence in hotel_rev:
     print(sentence)
     ss = sid.polarity_scores(sentence)
     for k in ss:
         #print('{0}: {1}, '.format(k, ss[k]), end='')
         print ss
     print()
