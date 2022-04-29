package ru.edu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String RESOURCE_URL = "https://www.anekdot.ru/";
    private List<String> quoteList = new ArrayList();

    public Storage() {
//        quoteList.add("What");
//        quoteList.add("Where");
//        quoteList.add("Why");
//        quoteList.add("Who");
        parser(RESOURCE_URL);
    }

    public String getRandQuote()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }

    private void parser(String strURL)
    {
        String classNmae = "text";
        Document doc = null;
        try {
            //Получаем документ нужной нам страницы
            doc = Jsoup.connect(strURL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем группу объектов, обращаясь методом из Jsoup к определенному блоку
        Elements elQuote = doc.getElementsByClass(classNmae);

        //Достаем текст из каждого объекта поочереди и добавляем в наше хранилище
        elQuote.forEach(el -> {
            quoteList.add(el.text());
        });
    }

}
