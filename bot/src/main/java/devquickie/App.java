package devquickie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class App implements EventListener
{
    private static final String token_path = "bot/assets/token.txt";

    private static String token;

    public static void main( String[] args )
    {
        loadToken();
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(new App())
                .buildBlocking();

		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void loadToken(){
        File file = new File(token_path);
    
        try {
            Scanner sc = new Scanner(file);
            token = sc.nextLine();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }    
    }

	@Override
	public void onEvent(Event event) {
		if(event instanceof ReadyEvent){
            System.out.println("hat geklappt");
        }
	}
}
