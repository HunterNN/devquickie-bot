package devquickie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHandler {
    HashMap command_map;
    
    public CommandHandler(){
        command_map = new HashMap<String, CommandInterface>();

        command_map.put("!info", new CommandInterface(){
        
            @Override
            public void runCommand(String[] command_args, MessageReceivedEvent event) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Folgende Befehle stehen zur Vefügung:").queue();
                ArrayList<String> commands = new ArrayList<String>(command_map.keySet());
                for(String command : commands){
                    System.out.println(command);
                }
            }
        });
        
        command_map.put("invalid_command", new CommandInterface(){
        
            @Override
            public void runCommand(String[] command_args, MessageReceivedEvent event) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Der Command " + command_args[0] + " existiert nicht. Schreibe \"!info\" in den Chat für eine Liste der Commands").queue();
            }
        });

    }

    public void handle(String[] command_args, MessageReceivedEvent event){
        CommandInterface command = (CommandInterface) command_map.get(command_args[0]);
        if(command != null){
            command.runCommand(command_args, event);
        } else {
            command = (CommandInterface) command_map.get("invalid_command");
            command.runCommand(command_args, event);
        }
        
    }    
}
