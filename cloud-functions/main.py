import json
import numpy
import math
from google.cloud import firestore
from google.cloud import language_v1

client = firestore.Client()
nlp_client = language_v1.LanguageServiceClient()

def monitor_firestore(data, context):
    """
    Triggered by a change to a Firestore document. 
    We can create a new document of all conversations that have bot enabled for monitoring.
    user/{username}/conversations/botEnabled
    Args:
    data (dict): The event payload
    context (google.cloud.functions.Context): Metadata for the event. 
    """
    trigger_resource = context.resource
    path_parts = context.resource.split('/documents/')[1].split('/')
    collection_path = path_parts[0]
    document_path = '/'.join(path_parts[1:])
    print('Function triggered by change to: %s' % trigger_resource)

    print('\nOld value:')
    print(json.dumps(data["oldValue"]))

    print('\nNew value:')
    print(json.dumps(data["value"]))
    if "sentiment_details" in data["value"]["fields"] and data["value"]["fields"]["fromBot"]["booleanValue"] != True == 'negative' and data["value"]["fields"]["sentiment_details"]["sentiment"]["stringValue"] == 'negative':
        add_recommendation(data,context,document_path)

def add_sentiment(data,context):
    trigger_resource = context.resource
    path_parts = context.resource.split('/documents/')[1].split('/')
    collection_path = path_parts[0]
    document_path = '/'.join(path_parts[1:])
    print('Sentiment function triggered by change to: %s' % trigger_resource)
    text_sentiment = 'neutral'
    if "sentiment_details" not in data["value"]["fields"] and data["value"]["fields"]["fromBot"]["booleanValue"] != True:
        # conduct sentiment analysis
        print('in the sentiment analysis')
        type_ = language_v1.Document.Type.PLAIN_TEXT
        language = "en"
        nlp_document = {"content": data["value"]["fields"]["message"]["stringValue"], "type_": type_, "language": language}
        encoding_type = language_v1.EncodingType.UTF8
        response = nlp_client.analyze_sentiment(request = {'document': nlp_document, 'encoding_type': encoding_type})
        if response.document_sentiment.score >= 0.4:
            text_sentiment = "positive"
        elif response.document_sentiment.score <= -0.25:
            text_sentiment = 'negative'
        else:
            text_sentiment = 'neutral'
        field_updates = { u"sentiment_details": {
            u"score": response.document_sentiment.score,
            u"magnitude": response.document_sentiment.magnitude,
            u"sentiment": text_sentiment
        }}
        print(field_updates)
        doc = client.collection(collection_path).document(document_path)
        doc.update(field_updates)

def add_recommendation(data, context, related_document):
    client.collection(u'messages').add({'fromBot': True,'userFrom': data["value"]["fields"]["userFrom"]["stringValue"], "userTo":data["value"]["fields"]["userTo"]["stringValue"],"message": "Reach out to a counsellor please. ","relatedMessage": related_document})

def five_point_summary(user_a, user_b):
    collect_ref = client.collection(u'messages')
    collect_ref.where(u'userFrom', u'==',user_a)
    collect_ref.where(u'userTo', u'==', user_b)
    collect_ref.limit_to_last(25)
    messages = collect_ref.stream()
    sentitment_scores = []
    for message in messages:
        if "sentiment_details" in message:
            sentitment_scores.append(message["sentiment_details"]["sentiment"])
    return (numpy.min(sentitment_scores), numpy.max(sentitment_scores),numpy.std(sentitment_scores), numpy.mean(sentitment_scores), numpy.median(sentitment_scores))
    
