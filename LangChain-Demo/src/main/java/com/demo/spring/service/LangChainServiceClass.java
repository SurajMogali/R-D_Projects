package com.demo.spring.service;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.data.message.UserMessage.userMessage;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import dev.langchain4j.classification.TextClassifier;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.stereotype.Service;

import com.demo.spring.assistant.PersonExtractor;
import com.demo.spring.entity.Person;
import com.demo.spring.util.ApiKeys;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class LangChainServiceClass {
	public String generateAnswer() {
		ChatLanguageModel model = OpenAiChatModel.withApiKey("demo");

		String joke = model.generate("Tell me a joke about Java");

		return joke;

	}
    //Response<AiMessage> gives the token along with the answer
	public void ChatMemoryExecution() {
		ChatLanguageModel model = OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY);

		ChatMemory chatMemory = TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenizer("gpt-3.5-turbo"));

		// You have full control over the chat memory.
		// You can decide if you want to add a particular message to the memory
		// (e.g. you might not want to store few-shot examples to save on tokens).
		// You can process/modify the message before saving if required.

		chatMemory.add(userMessage("Hello, my name is Klaus"));
		AiMessage answer = model.generate(chatMemory.messages()).content();
		System.out.println(answer.text()); // Hello Klaus! How can I assist you today?
		chatMemory.add(answer);

		chatMemory.add(userMessage("What is my name?"));
		Response<AiMessage> answerWithName = model.generate(chatMemory.messages());
		System.out.println(answerWithName);

	}



	//It will consider the last 10 messages and gives the answer
	public void multipleChatMemory() {
		interface Assistant {

			String chat(@MemoryId int memoryId, @UserMessage String userMessage);
		}

		Assistant assistant = AiServices.builder(Assistant.class)
				.chatLanguageModel(OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY))
				.chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).build();

		System.out.println(assistant.chat(1, "Hello, my name is Klaus"));
		System.out.println(assistant.chat(1, "Hello, I play cricket"));
		System.out.println(assistant.chat(1, "Hello, my favourite player is Virat kOhli"));
		System.out.println(assistant.chat(1, "Hello, my favourite team is RCB"));
		System.out.println(assistant.chat(1, "RCB team will play at Neokred"));
		System.out.println(assistant.chat(1, "Even I work in Neokred"));
		System.out.println(assistant.chat(1, "It is a fintech Company"));
		System.out.println(assistant.chat(1, "The work culture is good here"));
		System.out.println(assistant.chat(1, "The match will be exciting"));
		System.out.println(assistant.chat(1, "RCB will be playing against MI team"));
		System.out.println(assistant.chat(1, "Vijay Malya is also coming"));
		// Hi Klaus! How can I assist you today?

		System.out.println(assistant.chat(2, "Hello, my name is Francine"));
		// Hello Francine! How can I assist you today?

		System.out.println(assistant.chat(1, "What is my name"));
		// Your name is Klaus.

		System.out.println(assistant.chat(2, "What is my name?"));
		// Your name is Francine.

	}



	ChatLanguageModel model = OpenAiChatModel.withApiKey("demo");

	interface Friend {

		@SystemMessage("You are a girlfriend of mine. Answer using slang.")
		String chat(String userMessage);
	}

	public String SystemMessageGenerator() {
		com.demo.spring.service.LangChainServiceClass.Friend friend = (Friend) AiServices.create(Friend.class, model);

		String answer = friend.chat("Hello"); // Hey! What's up?
		return answer;
	}

	public String userMessageGenerator() {
		interface Friend {

			@UserMessage("You are my friend with the name Thejashvi, answer using slang to me for {{myQuestion}}")
			String chat(@V("myQuestion") String userMessage);
		}

		Friend friend = AiServices.create(Friend.class, model);

		String answer = friend.chat("What is your  name"); // Hey! What's shakin'?
		return answer;

	}

	public Person objectFetcher() {
		ChatLanguageModel model = OpenAiChatModel.builder().apiKey("demo").logRequests(true).logResponses(true).build();

		PersonExtractor personExtractor = AiServices.create(PersonExtractor.class, model);

		String text = """
				In 1968, amidst the fading echoes of Independence Day,
				a child named Suraj arrived under the calm evening sky.
				This newborn, bearing the surname Mogali, marked the start of a new journey.
				He was welcomed into Venkataramana PG for Gents,4th T Block,37th C Cross,Jaynagar in the heart of Bangalore
				an abode that echoed with the gentle hum of suburban dreams and aspirations.
				""";

		Person person = personExtractor.extractPersonFrom(text);

		return person;
	}

	//For Fetching from the PDF document with maximum 1000 tokens only ie Felix Story.pdf
	public String fetchFromPDFDocument() {
		String answer = null;
		try {
			// Load the PDF document and extract text
			PDDocument pdfDocument = PDDocument
					.load(new File("D:\\workspace1\\LangChain-Demo\\src\\main\\resources\\Felix_Story.pdf"));
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			String pdfText = pdfTextStripper.getText(pdfDocument);
			pdfDocument.close();

			// Specify the question you want to ask the model
			String question = "Who is Oliver?";

			// Create a prompt for the model that includes question and PDF text
			PromptTemplate promptTemplate = PromptTemplate
					.from("Answer the following question to the best of your ability:\n" + "\n" + "Question:\n"
							+ "{{question}}\n" + "\n" + "Base your answer on the following information:\n"
							+ "{{pdfText}}");

			Map<String, Object> variables = new HashMap<>();
			variables.put("question", question);
			variables.put("pdfText", pdfText);

			Prompt prompt = promptTemplate.apply(variables);

			// Send the prompt to the OpenAI chat model
			ChatLanguageModel chatModel = OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY);
			AiMessage aiMessage = chatModel.generate(prompt.toUserMessage()).content();

			// See an answer from the model
			answer = aiMessage.text();
			// Charlie is a cheerful carrot living in VeggieVille...
		} catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}


	//For Fetching from the .txt document  ie Neokred_Employees.txt
	public String fetchfromDocument2(String question)
	{
		EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

		EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

		EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
				.documentSplitter(DocumentSplitters.recursive(300, 0))
				.embeddingModel(embeddingModel)
				.embeddingStore(embeddingStore)
				.build();

		Document document = loadDocument(Path.of("D:\\workspace1\\LangChain-Demo\\src\\main\\resources\\Neokred_Products.txt"), new TextDocumentParser());
		ingestor.ingest(document);

		ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
				.chatLanguageModel(OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY))
				.retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
				//.chatMemory((MessageWindowChatMemory.withMaxMessages(10)))
				// .promptTemplate() // you can override default prompt template
				.build();

		String answer = chain.execute("Answer the question using the document only ,if there is no answer return 'Contact Neokred',the question is : "+question);
		return answer; // Charlie is a cheerful carrot living in VeggieVille...

	}


	private static Path toPath(String fileName) {
		try {
			URL fileUrl = LangChainServiceClass.class.getResource(fileName);
			return Paths.get(fileUrl.toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	//For Fetching from the PDF document ie Neokred_Details.docx
	public String fetchfromDocument3(String question)
	{
		EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

		EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

		EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
				.documentSplitter(DocumentSplitters.recursive(300, 0))
				.embeddingModel(embeddingModel)
				.embeddingStore(embeddingStore)
				.build();

		Document document = loadDocument(Path.of("D:\\workspace1\\LangChain-Demo\\src\\main\\resources\\Neokred_Products.docx"), new ApachePoiDocumentParser());
		ingestor.ingest(document);

		ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
				.chatLanguageModel(OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY))
				.retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
				//.chatMemory((MessageWindowChatMemory.withMaxMessages(20)))
				// .promptTemplate() // you can override default prompt template
				.build();

		String answer = chain.execute("Answer the question using the document only ,if there is no answer return 'Contact Neokred',the question is : "+question);
		return answer; // Charlie is a cheerful carrot living in VeggieVille...

	}


	//For Fetching from the PDF document ie Neokred_Details.pdf
	public String fetchfromDocument4()
	{
		EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

		EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

		EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
				.documentSplitter(DocumentSplitters.recursive(300, 0))
				.embeddingModel(embeddingModel)
				.embeddingStore(embeddingStore)
				.build();

		Document document = loadDocument(Path.of("D:\\workspace1\\LangChain-Demo\\src\\main\\resources\\Neokred_Products.pdf"), new ApachePdfBoxDocumentParser());
		ingestor.ingest(document);

		ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
				.chatLanguageModel(OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY))
				.retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
				// .chatMemory() // you can override default chat memory
				// .promptTemplate() // you can override default prompt template
				.build();

		String answer = chain.execute("Neokred Founders?");
		return answer; // Charlie is a cheerful carrot living in VeggieVille...

	}

	enum CustomerServiceCategory {

		BILLING_AND_PAYMENTS,
		TECHNICAL_SUPPORT,
		ACCOUNT_MANAGEMENT,
		PRODUCT_INFORMATION,
		ORDER_STATUS,
		RETURNS_AND_EXCHANGES,
		FEEDBACK_AND_COMPLAINTS
	}


	public void EmbeddingModelTextClassifier()
	{

		Map<CustomerServiceCategory, List<String>> examples = new HashMap<>();
		examples.put(CustomerServiceCategory.BILLING_AND_PAYMENTS, asList(
				"Can I pay using PayPal?",
				"Do you accept Bitcoin?",
				"Is it possible to pay via wire transfer?",
				"I keep getting an error message when I try to pay.",
				"My card was charged twice, can you help?",
				"Why was my payment declined?",
				"How can I request a refund?",
				"When will I get my refund?",
				"Can I get a refund if I cancel my subscription?",
				"Can you send me an invoice for my last order?",
				"I didn't receive a receipt for my purchase.",
				"Is the invoice sent to my email automatically?",
				"How do I upgrade my subscription?",
				"What are the differences between the Basic and Premium plans?",
				"How do I cancel my subscription?",
				"Can I switch to a monthly plan from an annual one?",
				"I want to downgrade my subscription, how do I go about it?",
				"Is there a penalty for downgrading my plan?"
		));
		examples.put(CustomerServiceCategory.TECHNICAL_SUPPORT, asList(
				"The app keeps crashing whenever I open it.",
				"I can't save changes in the settings.",
				"Why is the search function not working?",
				"The installer is stuck at 50%.",
				"I keep getting an ‘Installation Failed' message.",
				"How do I install this on a Mac?",
				"I can't connect to the server.",
				"Why am I constantly getting disconnected?",
				"My Wi-Fi works, but your app says no internet connection.",
				"Why is the app so slow?",
				"I'm experiencing lag during video calls.",
				"The website keeps freezing on my browser.",
				"I get a ‘404 Not Found' error.",
				"What does the ‘Permission Denied' error mean?",
				"Why am I seeing an ‘Insufficient Storage' warning?",
				"Is this compatible with Windows 11?",
				"The app doesn't work on my Android phone.",
				"Do you have a browser extension for Safari?"
		));
		examples.put(CustomerServiceCategory.ACCOUNT_MANAGEMENT, asList(
				"I forgot my password, how can I reset it?",
				"I didn't receive a password reset email.",
				"Is there a way to change my password from within the app?",
				"How do I set up two-factor authentication?",
				"I lost my phone, how can I log in now?",
				"Why am I not getting the 2FA code?",
				"My account has been locked, what do I do?",
				"Is there a limit on login attempts?",
				"I've been locked out for no reason, can you help?",
				"How do I change my email address?",
				"Can I update my profile picture?",
				"How do I edit my shipping address?",
				"Can I share my account with family?",
				"How do I give admin access to my team member?",
				"Is there a guest access feature?",
				"How do I delete my account?",
				"What happens to my data if I deactivate my account?",
				"Can I reactivate my account later?"
		));
		examples.put(CustomerServiceCategory.PRODUCT_INFORMATION, asList(
				"What does the ‘Sync' feature do?",
				"How does the privacy mode work?",
				"Can you explain the real-time tracking feature?",
				"When will the new model be in stock?",
				"Do you have this item in a size medium?",
				"Are you restocking the sold-out items soon?",
				"What's the difference between version 1.0 and 2.0?",
				"Is the Pro version worth the extra cost?",
				"Do older versions support the new update?",
				"Is this product compatible with iOS?",
				"Will this work with a 220V power supply?",
				"Do you have options for USB-C?",
				"Are there any accessories included?",
				"Do you sell protective cases for this model?",
				"What add-ons would you recommend?",
				"What does the warranty cover?",
				"How do I claim the warranty?",
				"Is the warranty international?"
		));
		examples.put(CustomerServiceCategory.ORDER_STATUS, asList(
				"Where is my order right now?",
				"Can you give me a tracking number?",
				"How do I know my order has been shipped?",
				"Can I change the shipping method?",
				"Do you offer overnight shipping?",
				"Is pickup from the store an option?",
				"When will my order arrive?",
				"Why is my delivery delayed?",
				"Can I specify a delivery date?",
				"It's past the delivery date, where is my order?",
				"Will I be notified if there's a delay?",
				"How long will the weather delay my shipment?",
				"I received my order, but an item is missing.",
				"The package was empty when it arrived.",
				"I got the wrong item, what should I do?",
				"Will all my items arrive at the same time?",
				"Why did I receive only part of my order?",
				"Is it possible to get the remaining items faster?"
		));
		examples.put(CustomerServiceCategory.RETURNS_AND_EXCHANGES, asList(
				"What's your return policy?",
				"Is the return shipping free?",
				"Do I need the original packaging to return?",
				"How do I get a return label?",
				"Do I need to call customer service for a return?",
				"Is an RMA number required?",
				"I need to exchange for a different size.",
				"Can I exchange a gift?",
				"How long does the exchange process take?",
				"My item arrived damaged, what do I do?",
				"The product doesn't work as described.",
				"There's a part missing, can you send it?",
				"I received the wrong item, how can I get it corrected?",
				"I didn't order this, why did I receive it?",
				"You sent me two of the same item by mistake.",
				"Is there a restocking fee for returns?",
				"Will I get a full refund?",
				"How much will be deducted for restocking?"
		));
		examples.put(CustomerServiceCategory.FEEDBACK_AND_COMPLAINTS, asList(
				"The material quality is not as advertised.",
				"The product broke after a week of use.",
				"The colors faded after the first wash.",
				"The representative was rude to me.",
				"I was on hold for 30 minutes, this is unacceptable.",
				"Your customer service resolved my issue quickly, thank you!",
				"Your website is hard to navigate.",
				"The app keeps crashing, it's frustrating.",
				"The checkout process is confusing.",
				"You should offer a chat feature for quicker help.",
				"Can you add a wishlist feature?",
				"Please make a mobile-friendly version of the website.",
				"I found a bug in your software.",
				"There's a typo on your homepage.",
				"The payment page has a glitch.",
				"Can you start offering this in a gluten-free option?",
				"Please add support for Linux.",
				"I wish you had more colors to choose from."
		));

		EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
		TextClassifier<CustomerServiceCategory> classifier = new EmbeddingModelTextClassifier<>(embeddingModel, examples);

		List<CustomerServiceCategory> categories = classifier.classify("Can i pay in credit card");

		System.out.println(categories);

	}

	enum Sentiment {
		POSITIVE, NEUTRAL, NEGATIVE
	}

	interface SentimentAnalyzer {

		@UserMessage("Analyze sentiment of {{it}}")
		Sentiment analyzeSentimentOf(String text);

		@UserMessage("Does {{it}} has a positive sentiment?")
		boolean isPositive(String text);
	}


    public void analyzeSentiment()
	{
		SentimentAnalyzer sentimentAnalyzer = AiServices.create(SentimentAnalyzer.class, model);

		Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf("This is ridiculous!");
// POSITIVE

		boolean positive = sentimentAnalyzer.isPositive("It's awful!");

		System.out.println(sentiment);
		System.out.println(positive);


	}






}








