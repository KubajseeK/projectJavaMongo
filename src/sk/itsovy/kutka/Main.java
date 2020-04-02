package sk.itsovy.kutka;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
	String uri = "mongodb+srv://Admin:admin@samplecluster-6cqhx.mongodb.net/test";
	MongoClientURI clientURI = new MongoClientURI(uri);
	MongoClient mongoClient = new MongoClient(clientURI);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("SampleDatabase");
	MongoCollection collection = mongoDatabase.getCollection("SCol");
		System.out.println("Database Connected");

		/**INSERT ONE **/
		Document document = new Document("name", "Kubik");
		document.append("Sex", "male");
		document.append("Age", "20");
		document.append("Race", "White");

		collection.insertOne(document);

		document = new Document("name", "Peter Ganoczi");
		document.append("Age", 28);
		document.append("Race", "White");
		collection.insertOne(document);

		/** INSERT MANY **/

		List<Document> inputList = new ArrayList<>();
		document = new Document("name", "Janko Hraško");
		document.append("Age", "10");
		document.append("Race", "African");
		inputList.add(document);

		document = new Document("name", "Peter Pan");
		document.append("Age", "13");
		document.append("Race", "African");
		inputList.add(document);

		document = new Document("name", "Zdena Studenková");
		document.append("Age", "650");
		document.append("Race", "Mummy");
		inputList.add(document);

		collection.insertMany(inputList);


	/**UPDATE **/
	Document search = new Document("name", "Kubik");
	Document found = (Document) collection.find(search).first();

	if(found != null) {
		System.out.println("Found User");
		Bson updatedValue = new Document("name", "Jakub Kutka");
		Bson updateOperation = new Document("$set", updatedValue);
		collection.updateOne(found, updateOperation);
		System.out.println("User Updated");
	}
		/**DELETE**/
		search = new Document("name", "Jakub Kutka");
		found = (Document) collection.find(search).first();
		if (found != null) {
			collection.deleteOne(found);
			System.out.println("User Deleted");
		}


    }
}
