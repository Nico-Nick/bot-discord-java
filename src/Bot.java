import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        String token = "seu token"; 
        String guildId = "server id"; 

        var jda = JDABuilder.createDefault(token)
                .addEventListeners(new Bot())
                .build();

        jda.awaitReady();

        Guild guild = jda.getGuildById(guildId);
        if (guild != null) {
            guild.upsertCommand(Commands.slash("ping", "Responde com pong")).queue();
        } else {
            System.out.println("❌ Servidor não encontrado!");
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            event.reply("🏓 Pong! Olá Mundo").queue();
        }
    }
}
