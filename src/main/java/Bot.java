public class Bot {
    public String start(String request, String[] listCommand) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (request) {
            case ("/help"):
                stringBuilder.append("Список команд + пояснение\n");
                for (var command : listCommand) {
                    stringBuilder.append(command + "\n");
                }
                break;
            case ("/weather"):
                stringBuilder.append("Пока в разработке");
                break;
            case ("/joke"):
                stringBuilder.append("Пока в разработке");
                break;
            case ("/wikipedia"):
                stringBuilder.append("Пока в разработке");
                break;
            case ("/game"):
                stringBuilder.append("В скорой разработке");
                //game();
                break;
            case ("/exit"):
                stringBuilder.append("До связи");
                break;
            default:
                stringBuilder.append("Вы написали: " + request + "Напишите /help, чтобы узнать список команд");
        }
        return stringBuilder.toString();
    }
}
