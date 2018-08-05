package devquickie;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class CommandInstance {
    private String user_id;
    public abstract void runCommand(String[] command_args, MessageReceivedEvent event);
    
    public void sendMessageWithUserName(MessageReceivedEvent event, String message){
        MessageChannel channel = event.getChannel();
        channel.sendMessage("<@"+event.getAuthor().getId() + ">" + "\n" + message).queue();
    }

    public void remove(){
        CommandInstanceHandler.removeInstance(user_id);
    }

    public void setUserId(String value){
        this.user_id = value;
    }
}