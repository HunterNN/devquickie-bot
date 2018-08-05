package devquickie.games;

import devquickie.CommandInstance;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GuessGame extends CommandInstance{
    private final static int max_value = 10;
    private final static int tries = 3;
    private final static String format_error_message = "Bitte verwende folgendes Format: `!guess_game [zahl]`";
    
    private int value;
    private int round;

    public GuessGame(){
        this.round = 0;
    }

	@Override
	public void runCommand(String[] command_args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if(round == 0){
            this.value = (int)(Math.random() * max_value);
            sendMessageWithUserName(event, "Ich habe mir eine Zahl zwischen 0 bis " + max_value + " ausgedacht. \nVersuche die Zahl erraten.");
        } else if(command_args.length > 1){
            try {
                float guessed_value = Float.parseFloat(command_args[1]);
                if(guessed_value == value){
                    sendMessageWithUserName(event, "Du hast die Zahl " + value + " erraten!");
                    remove();
                    return;
                } else if(guessed_value < value) {
                    sendMessageWithUserName(event, "Falsch. Die Gesuchte Zahl ist grösser.");
                } else {
                    sendMessageWithUserName(event, "Falsch. Die Gesuchte Zahl ist kleiner.");
                }
            } catch (NumberFormatException e) {
                sendMessageWithUserName(event, format_error_message);

                return;
            }
        } else {
            sendMessageWithUserName(event, format_error_message);
            return;
        }
        round++;

        if(round > tries){
            sendMessageWithUserName(event, "Leider hast du alle Versuche aufgebraucht. Die Gesuchte Zahl war: " + value);
            remove();
        }
	}
}