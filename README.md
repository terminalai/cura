# Cura

_By Karimi Zayan, Mario Tanijaya and Prannaya Gupta_

**Submitted for**: Splash Awards 2022

**Challenge Tracks**: AI for Good, AI in Healthcare, AI in Mental Wellness

## Work
### 🔗 Links
- [Code](https://github.com/terminalai/cura)
- [To-Do List Invite](https://to-do.microsoft.com/sharing?InvitationToken=95Pg0MzYqN7Tn-f4OcSd7g38xemaOautRTvXjCDxCcM6oDn4NzMqk1L-_hFkfonK4)
- [Figma Team Invitation](https://www.figma.com/team_invite/redeem/22wkI5W5pTlS3KSsOQv9Eo)
- [Pitch Deck](https://nushighedu-my.sharepoint.com/:p:/g/personal/h1810124_nushigh_edu_sg/EdsAwtaQ3ktImtgTc8G6eAEB__voYm-MrkIIm3TqKbpIIg?e=RofF4E)

### Submission Requirements
- A PowerPoint Presentation of overall flow of Cura
- Updated Proposal
- Demo a Working Prototype via a Video Recording (MP4, < 2 minutes)
- Complete Literacy in AI (AI Singapore)


**Deadline**: 1st September, 2359

**Presentation Format**: 6 minutes of Presentation & Showcase of prototype on 14/15 Sep from 2pm to 5pm.

### Current Tasks
- [ ] Develop an Android Application based off the Wireframes from Manfred (Zayan)
- [ ] Create a mathematical/AI model for matching people based off interests (Mario)
- [ ] Create a multimodal NLP model for the bot (Prannaya)
- [ ] Port over models to GCP (Zayan?)

## Proposal

### Opportunity Identified
The COVID-19 pandemic has been tough on all of us. While we worry about our loved ones and elderly folk, we also should worry about another group, healthcare workers. They work their thankless essential jobs for countless hours, yet very few seem to care about the mental wellbeing. In a study conducted in 2020, it was found that 79.7% of healthcare workers are disengaged and 75.3% are exhausted. There is also a large social stigma against healthcare workers with 66.7% of doctors feeling this way and many of these workers spend days and even weeks before being able to meet and talk with their friends and families. Hence, a safe space to connect and share burdens is a desideratum.

### Proposed Solution

We intend to develop a messaging application with an in-built bot that tries to use AI/ML algorithms to match caregivers anonymously based off a selection of interests, simply sharing their common interests so they can converse openly with one another. This gives them an avenue to open up about their experiences and frustrations. If the conversations get overly negative, a bot conducting real-time sentiment analysis can provide resources or begin fun activities to engage the caregivers and lighten the mood. This is simply to ensure the conversation does not grow toxic. The aimed end-product is to develop an efficient network of Caregivers that can easily vent out their frustrations, with a bot there too help ease their mental state.


The bot’s proposed algorithm is as follows:

1. Bot takes in the last 20 messages
2. Firstly, the bot conducts message-wise sentiment analysis using the existing API from SOAN (in addition to the function on Google Cloud Platform given the budget) on the set of messages and computes the concentration of sentiment via a rolling window method.
3. The bot also similarly computes the general sentiment of the sample of messages using another existing API from SOAN.
4. Another model utilised on these texts is a Sequential Sentence Classification Model, built likely on RoBERTa. Following this, some topic modelling is done via the existing LDA APIs from SOAN to identify specific important texts.
5. If the general sentiment and concentration of sentiment is too negative and high towards the end, the bot runs a simplistic Sequence-to-Sequence NLP Model built on HuggingFace’s DistilGPT-2 which, with the assistance of the specific important texts identified in part 4, generates a bot message to be sent into the chat.

### Target Audience

Healthcare workers that are under stress and need an avenue to vent their frustrations and open up about their struggles. These healthcare workers want to talk to someone they relate to.

### Originality and Value
We are anonymously grouping together individuals from similar yet possibly diverse walks of life, allowing them to vent their frustrations openly and securely to others suffering from possibly similar circumstances. The novelty here is simply that this gives them not only an incentive to express their dissatisfaction, yet also giving them the unique opportunity to help each other pick themselves up.

### Type of Dataset
-	NUS SMS Corpus (for the Sequential Sentence Classification Model)
-	SOAN (https://github.com/MaartenGr/soan)
-	CoreNLP and Gensim/NLTK Corpuses/Tokenizers
-	HuggingFace’s existing model structures
