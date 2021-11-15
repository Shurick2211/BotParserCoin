package App;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;


public class ParseKurce {
    private static Document getPage(String url) throws IOException {
        Document page;
        page = Jsoup.parse(new URL(url), 5000);
        return page;
    }

    public static ArrayList<Valuta> valutas;
    public static Valuta getValuta(String name){
        for (Valuta val:valutas)
            if(val.getName().equals(name))return val;
        return null;
    }

    public static void getValutes() throws IOException {
        String urlKr = "https://myfin.by/crypto-rates/";

        valutas=new ArrayList<>();
        try {
            Document pageKr = getPage(urlKr);
            Elements krTablo=pageKr.select("td");

            for (int i=0;i<krTablo.size();i+=6) {

                Valuta valuta=new Valuta("",0.0);
                if (!krTablo.get(i).text().contains("/")) {
                    if(krTablo.get(i).text().split(" ").length>2)
                        valuta.setName(krTablo.get(i).text().split(" ")[0]+krTablo.get(i).text().split(" ")[1]);
                    else valuta.setName(krTablo.get(i).text().split(" ")[0]);}
                else valuta.setName(krTablo.get(i).text().split("/")[0].replace(" ", ""));
                valuta.setKurs(Double.parseDouble(krTablo.get(i+1).text().split(" ")[0].replace("$","").trim()));
                valutas.add(valuta);
            }
            }catch (SocketTimeoutException e){System.out.println("Инет тормозит!");}
           // valutas.forEach(System.out::println);
    }

}
