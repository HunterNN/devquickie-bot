package devquickie;

import java.util.HashMap;
import java.util.Map;

import devquickie.games.GuessGame;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandHandler {
    HashMap<String, CommandInterface> command_map;
    
    public CommandHandler(){
        command_map = new HashMap<String, CommandInterface>();

        command_map.put("!info", new CommandInterface(){
        
            @Override
            public void runCommand(String[] command_args, MessageReceivedEvent event) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Folgende Befehle stehen zur Vefügung:").queue();

                for(Map.Entry<String, CommandInterface> entry : command_map.entrySet()){
                    if(entry.getKey().contains("!")){
                        channel.sendMessage("`" + entry.getKey() + "` \t" + entry.getValue().getDescription()).queue();
                    }
                } 
            }

			@Override
			public String getDescription() {
				return "Zeigt diese Liste an.";
			}
        });
        
        command_map.put("invalid_command", new CommandInterface(){
        
            @Override
            public void runCommand(String[] command_args, MessageReceivedEvent event) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Der Command `" + command_args[0] + "` existiert nicht. Schreibe `!info` in den Chat für eine Liste der Commands").queue();
            }

			@Override
			public String getDescription() {
				return null;
			}
        });

        command_map.put("!guess_game", new CommandInterface(){
        
            @Override
            public void runCommand(String[] command_args, MessageReceivedEvent event) {
                CommandInstanceHandle.handleCommand(command_args, event, new GuessGame());
            }
        
            @Override
            public String getDescription() {
                return "Errate eine Zahl.";
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
