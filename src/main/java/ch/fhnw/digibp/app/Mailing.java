package ch.fhnw.digibp.app;
//import java.io.File;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import org.glassfish.jersey.client.ClientResponse;

// generell Probleme mit Imports. Weiss nicht mehr wie dies genau geht. Zudem wollte ich nachfragen, welche Implementierungs-Variante (1 oder 2) besser ist.
public class Mailing {

// Variante 1: Habe ein HTML-Template auf Mailgun erstellt. Dieses kann so angesprochen werden und mit Daten gefüttert werden. Siehe weiter unten.
/*  public static ClientResponse SendSimpleMessage() {
    Client client = Client.create();
    client.addFilter(new HTTPBasicAuthFilter("api", "e5f4c78f466b826a709a47f3b52793a6-fa6e84b7-aaf08b9e"));
    WebResource webResource = client.resource("https://api.mailgun.net/v3/mailgun.simonhafner.me/messages");
    MultivaluedMapImpl formData = new MultivaluedMapImpl();
    formData.add("from", "Mailgun Sandbox <postmaster@mailgun.simonhafner.me>");
    formData.add("to", "Simon Hafner <simon.hafner@students.fhnw.ch>");
    formData.add("subject", "Hello Simon Hafner");
        formData.add("template", "rejection");

    // hier können wir Variablen übergeben
    formData.add("h:X-Mailgun-Variables", "{"test": "test"}");
    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
        post(ClientResponse.class, formData); */
            
// Variante 2: Hier generiert man den komplettenn Mail-Inhalt selbst. Somit kein HMTL-Mail; ausser man implementiert dies gleich hier ;-)
/* 	public static JsonNode sendSimpleMessage() throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "mailgun.simonhafner.me" + "/messages"),
			.basicAuth("api", "e5f4c78f466b826a709a47f3b52793a6-fa6e84b7-aaf08b9e")
			.queryString("from", "XYZ Inc. <stephan@XYZ.inc>")
			.queryString("to", "simon.hafner@students.fhnw.ch")
			.queryString("subject", "hello")
			.queryString("text", "testing")
			.asJson();
		return request.getBody(); 
	} */
}