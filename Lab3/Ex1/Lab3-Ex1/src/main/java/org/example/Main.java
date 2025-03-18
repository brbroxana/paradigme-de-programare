package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// Clasa RSS stochează informațiile unei știri din feed-ul RSS
class RSS {
    private String title;
    private String link;
    private String description;
    private String pubDate;
//Setter si getter pentru titlu
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
//Setter si getter pentru link
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
//Setter si getter pentru descriere
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
//Setter si getter pentru data publicatiei
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    // Constructor- inițializeaza un obiect RSS cu date
    public RSS(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // Se conectează la URL-ul feed-ului RSS și obține conținutul XML
            Document data = Jsoup.connect("http://rss.cnn.com/rss/edition.rss").get();
            // Selectează toate elementele <item> din feed-ul RSS
            Elements items = data.select("item");

            // Creează o listă pentru a stoca obiectele RSS extrase
            List<RSS> rssItems = new ArrayList<>();
            // Iterează prin fiecare element <item> și extrage informațiile relevante
            for (Element item : items) {
                String title = item.select("title").text();
                String link = item.select("link").text();
                String description = item.select("description").text();
                String pubDate = item.select("pubDate").text();
                // Creează un obiect RSS și îl adaugă în listă
                RSS rssItem = new RSS(title, link, description, pubDate);
                rssItems.add(rssItem);
            }
            // Afișează lista de știri extrase din feed-ul RSS
            for (RSS rssItem : rssItems) {
                System.out.println("Titlu: " + rssItem.getTitle());
                System.out.println();
                System.out.println("Link: " + rssItem.getLink());
                System.out.println();
                System.out.println("Description: " + rssItem.getDescription());
                System.out.println();
                System.out.println("Publish Date: " + rssItem.getPubDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}