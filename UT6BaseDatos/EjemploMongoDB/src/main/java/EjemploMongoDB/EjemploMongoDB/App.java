package EjemploMongoDB.EjemploMongoDB;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://martin:martiin@cluster0.0xedu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Crear un nuevo cliente y conectarse al servidor
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Confirmar la conexión exitosa con un ping
                MongoDatabase database = mongoClient.getDatabase("Cluster0");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                // Obtener la colección
                MongoCollection<Document> collection = database.getCollection("mi_coleccion");

                // Insertar un documento de ejemplo
                Document document = new Document("nombre", "Ejemplo")
                        .append("edad", 30)
                        .append("ciudad", "EjemploCity");
                collection.insertOne(document);
                System.out.println("Documento insertado correctamente.");

                // Consultar e imprimir todos los documentos en la colección
                MongoCursor<Document> cursor = collection.find().iterator();
                try {
                    while (cursor.hasNext()) {
                        System.out.println(cursor.next().toJson());
                    }
                } finally {
                    cursor.close();
                }
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}