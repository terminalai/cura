from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
import pandas as pd

texts = [
    "I'm not gonna kill myself more",
    "i am like secretly hoping i get kicked so i dun have to do sypt this yr anymore",
    "surprisingly, not dead, still not dead",
    "we are scrounging together something presentable",
    "cause we'll just get laughed at into oblivion",
    "i got kicked :(((",
    "I really want to kill myself",
    "\"torture.pdf\" lmao",
    "and I wanna kill myself"
]

analyzer = SentimentIntensityAnalyzer()

sentiments = [{"texts": text, **analyzer.polarity_scores(text)} for text in texts]

df = pd.DataFrame(sentiments)

print(df)

print(df.rolling(4).mean().dropna())
