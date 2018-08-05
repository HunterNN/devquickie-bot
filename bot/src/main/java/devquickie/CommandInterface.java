package devquickie;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface CommandInterface {
    public void runCommand(String[] command_args, MessageReceivedEvent event);
    public String getDescription();
}