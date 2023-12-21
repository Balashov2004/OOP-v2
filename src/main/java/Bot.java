
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    private LogicsTinder logicsTinder;

    public Bot() {
        logicsTinder = new LogicsTinder(this);
    }


    private DictionaryValue dictionaryValue = new DictionaryValue();

    @Override
    public void onUpdateReceived(Update update) {
        String[] listCommand = {"/help: список команд", "/exit: завершить работу", "/weather: погода", "/joke: анекдотыа", "/tinder: знакомства", "/game: blackjacke"};
        Message message = update.getMessage();
        String answer = "";
        Long chatID = message.getChatId();

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
            else if (text.equals("/tinder") || dictionaryValue.getFlag(String.valueOf(String.valueOf(chatID))) == 1){
                logicsTinder.logics(String.valueOf(chatID), text);
            }
             else {
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

