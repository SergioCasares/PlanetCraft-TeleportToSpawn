package me.djbiokinetix.teleport.Eventos;

import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.djbiokinetix.teleport.Main;

public class Eventos implements Listener{

	public Main plugin;
	
	public Eventos(Main instancia)
	{
		plugin = instancia;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p=e.getPlayer();
		try
		{
			if(plugin.getConfig().getString("localizacion.mundo")!=null)
			{
				p.teleport(plugin.localizacion);
				if(plugin.getConfig().getBoolean("debug")==true)
				{
					plugin.l.log(Level.INFO, "[Code] Jugador teletransportado por evento.");
				}
			}
			else
			{
				if(p.isOp())
				{
					p.sendMessage(plugin.setColor("&8[&6Code&8] &cInicio no establecido"));
				}
			}
		}
		catch(Exception ex)
		{
			plugin.l.log(Level.INFO, "[Code] Error al inicio del plugin: "+ex.getCause().toString());
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		Player p=e.getPlayer();
		try
		{
			if(plugin.getConfig().getString("localizacion.mundo")!=null)
			{
				p.teleport(plugin.localizacion);
				if(plugin.getConfig().getBoolean("debug")==true)
				{
					plugin.l.log(Level.INFO, "[Code] Jugador teletransportado por evento.");
				}
			}
			else
			{
				if(p.isOp())
				{
					p.sendMessage(plugin.setColor("&8[&6Code&8] &cInicio no establecido"));
				}
			}
		}
		catch(Exception ex)
		{
			plugin.l.log(Level.INFO, "[Code] Error al inicio del plugin: "+ex.getCause().toString());
		}
	}
	
}
