package devquickie;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class CommandInstance {
    private String user_id;
    public abstract void runCommand(String[] command_args, MessageReceivedEvent event);
    
    public void remove(){
        CommandInstanceHandle.removeInstance(user_id);
    }

    public void setUserId(String value){
        this.user_id = value;
    }
}