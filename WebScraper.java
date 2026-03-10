import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
public class WebScraper {


    public static void main(String[] args) {

        try {
            String url = "http://books.toscrape.com/";

            Document doc = Jsoup.connect(url).get();

            Elements books = doc.select("article.product_pod");

            FileWriter csvWriter = new FileWriter("books.csv");

            csvWriter.append("Book Name,Price,Rating\n");

            for (Element book : books) {

                String name = book.select("h3 a").attr("title");
                String price = book.select(".price_color").text();
                String rating = book.select("p.star-rating").attr("class").replace("star-rating ", "");

                csvWriter.append(name + "," + price + "," + rating + "\n");
            }

            csvWriter.close();

            System.out.println("Data scraped and saved to CSV");

        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
    

