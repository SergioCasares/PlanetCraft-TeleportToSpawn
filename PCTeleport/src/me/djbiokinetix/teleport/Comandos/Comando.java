package me.djbiokinetix.teleport.Comandos;

import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.djbiokinetix.teleport.Main;

public class Comando implements CommandExecutor{
	
	public Main plugin;
	
	public Comando(Main instancia)
	{
		plugin=instancia;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args)
	{
		if(cs instanceof Player)
		{
			Player p=(Player)cs;
			if(args.length==0)
			{
				if(plugin.getConfig().getString("localizacion.mundo")!=null)
				{
					p.teleport(plugin.localizacion);
					p.sendMessage(plugin.setColor("&8[&6Code&8] &7Teletransportando..."));
					if(plugin.getConfig().getBoolean("debug")==true)
					{
						plugin.l.log(Level.INFO, "[Code] Usuario teletransportado al inicio.");
					}
				}
				else
				{
					p.sendMessage(plugin.setColor("&8[&6Code&8] &cInicio no establecido."));
					return true;
				}
			}
			if (args.length>0)
			{
				if(args[0].equalsIgnoreCase("set"))
				{
					if(p.hasPermission("spawn.set"))
					{
						plugin.localizacion=p.getLocation();
						plugin.salvarLocalizacion(plugin.localizacion);
						String mundo=plugin.getConfig().getString("localizacion.mundo");
						String x=plugin.getConfig().getString("localizacion.x");
						String y=plugin.getConfig().getString("localizacion.y");
						String z=plugin.getConfig().getString("localizacion.z");
						String yaw=plugin.getConfig().getString("localizacion.yaw");
						String pitch=plugin.getConfig().getString("localizacion.pitch");
						p.sendMessage(plugin.setColor("&8[&6Code&8] &7Localizacion establecida."));
						p.sendMessage(plugin.setColor("&8[&6Code&8] &2W&7: &b"+mundo));
						p.sendMessage(plugin.setColor("&8[&6Code&8] &2X&7: &b"+x));
						p.sendMessage(plugin.setColor("&8[&6Code&8] &2Y&7: &b"+y));
						p.sendMessage(plugin.setColor("&8[&6Code&8] &2Z&7: &b"+z));
						if(plugin.getConfig().getBoolean("debug")==true)
						{
							plugin.l.log(Level.INFO, "[Code] YAW: "+yaw);
							plugin.l.log(Level.INFO, "[Code] PITCH: "+pitch);
						}
					}
					else
					{
						p.sendMessage(plugin.setColor("&8[&6Code&8] &cNo tienes permisos de ejecutar este subcomando."));
					}
				}
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(p.hasPermission("spawn.reload"))
					{
						plugin.reloadConfig();
						plugin.saveConfig();
						p.sendMessage(plugin.setColor("&8[&6Code&8] &bConfiguracion recargada."));
						if(plugin.getConfig().getBoolean("debug")==true)
						{
							plugin.l.log(Level.INFO,"[Code] Configuracion recargada por [ADMIN]"+p.getName()+"!");
						}
					}
					else
					{
						p.sendMessage(plugin.setColor("&8[&6Code&8] &cNo tienes permisos de ejecutar este subcomando."));
					}
				}
			}
		}
		else
		{
			cs.sendMessage("[Code] No puedes ejecutar este comando desde consola.");
			return true;
		}
		return false;
	}
}
