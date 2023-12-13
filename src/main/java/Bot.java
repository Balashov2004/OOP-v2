import com.google.protobuf.StringValue;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Giggle_Box_bot";
    }

    @Override
    public String getBotToken() {
        String str = null;
        try {
            str = new String(Commands.class.getResourceAsStream("/token.txt").readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }


    public void ptintToTg(Long chatId, String mes){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(mes);
        sendMessage.setChatId(chatId);
        try {
            // Отправка сообщения
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onUpdateReceived(Update update) {
        String[] listCommand = {"/help: список команд", "/exit: завершить работу", "/weather: погода", "/joke: анекдотыа", "/tinder: знакомства", "/game: blackjacke"};
        Message message = update.getMessage();
        String answer = "";
        if (message.hasText()) {
            String text = message.getText();
            SendMessage sendMessage = new SendMessage();
            if (text.equals("/start")) {
                sendMessage.setText("Hello, I'm Giggle!");
                sendMessage.setChatId(message.getChatId());

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboardRowList = new ArrayList<>();

                for (int i = 0; i < listCommand.length; i++) {
                    KeyboardRow keyboardRow = new KeyboardRow();
                    KeyboardButton keyboardButton = new KeyboardButton();
                    String buttonText = listCommand[i].split(":")[0].trim();
                    keyboardButton.setText(buttonText);
                    keyboardRow.add(keyboardButton);
                    keyboardRowList.add(keyboardRow);
                }

                replyKeyboardMarkup.setKeyboard(keyboardRowList);
                replyKeyboardMarkup.setOneTimeKeyboard(true); // Установка флага OneTimeKeyboard в true
                sendMessage.setReplyMarkup(replyKeyboardMarkup); // Установка клавиатуры

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (text.equals("/tinder")){
                System.out.println("here");
                HashMap<String, String> profile = new HashMap<>();
                Long chatID = message.getChatId();
                if (UserExistsChecker.checkUserExistence(String.valueOf(chatID))) {
//                    Tinder.tinder(chatID);
                    ptintToTg(chatID, "exit from tinder");
                }
                else {
                    profile = Form.form(String.valueOf(chatID), profile);
                    RegistrationUsers.signUpUser(profile);
                    ptintToTg(chatID, "Добавлен новый пользователь");
                }
            }
             else {
                Long chatID = message.getChatId();
                String chatIDString = chatID.toString();
                try {
                    answer = Commands.start(text, chatIDString);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setText(answer);
                sendMessage.setChatId(message.getChatId());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

