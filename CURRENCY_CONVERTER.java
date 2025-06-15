import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;

public class CURRENCY_CONVERTER{

    // Replace with your actual API key from https://www.exchangerate-api.com/
    private static final String API_KEY = "72fc944958b38087ea45091a";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        try {
            double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);
            System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }
    }

    public static double convertCurrency(String base, String target, double amount) throws IOException, InterruptedException {
        String url = API_URL + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("API call failed with status code: " + response.statusCode());
        }

        JSONObject json = new JSONObject(response.body());
        if (!json.has("conversion_rates")) {
            throw new IOException("Invalid response from exchange rate API.");
        }

        JSONObject rates = json.getJSONObject("conversion_rates");

        if (!rates.has(target)) {
            throw new IOException("Target currency not found in response.");
        }

        double rate = rates.getDouble(target);
        return amount * rate;
    }
}
