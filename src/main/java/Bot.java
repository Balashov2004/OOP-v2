import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot  extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Giggle_Box_bot";
    }

    @Override
    public String getBotToken(){
        return "6647213028:AAGiDWE2Lx-7caXdsrORS1JfXrGgGGbFJaM";
    }
    @Override
    public void onUpdateReceived(Update update){

        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
