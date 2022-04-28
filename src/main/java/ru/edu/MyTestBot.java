package ru.edu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTestBot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "5387806192:AAHarJkTesSrJ6XPILMi3OJFb2eOpqheZ7I";
    private static final String BOT_NAME = "AlexNN52bot";


    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if(update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);

                //Отправка в чат
                execute(outMess);
                }
            }catch (TelegramApiException e) {
                e.printStackTrace();
            }


    }

    private String parseMessage(String text) {
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if(text.equals("/start"))
            response = "Приветствую, бот знает много цитат. Жми /get, чтобы получить случайную из них";
        else if(text.equals("/get"))
            response = "Fuck you!";
        else
            response = "Сообщение не распознано";

        return response;
    }

}
