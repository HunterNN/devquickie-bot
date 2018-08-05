package devquickie;

import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandInstanceHandle {
    private static HashMap<String, CommandInstance> instances = new HashMap<String, CommandInstance>();

    static void handleCommand(String[] command_args, MessageReceivedEvent event, CommandInstance instance){
        String user_id = event.getAuthor().getId();
        if(instances.get(user_id) == null){
            instances.put(user_id, instance);
            instance.setUserId(user_id);
            instance.runCommand(command_args, event);
        } else {
            instances.get(user_id).runCommand(command_args, event);
        }
    }

    static void removeInstance(String user_id){
        instances.remove(user_id);
    }
}