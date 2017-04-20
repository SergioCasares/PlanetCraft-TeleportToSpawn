package me.djbiokinetix.teleport;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.djbiokinetix.teleport.Comandos.Comando;
import me.djbiokinetix.teleport.Eventos.Eventos;

public class Main extends JavaPlugin {

	public final Logger l=Logger.getLogger("Minecraft");
	public Location localizacion;
	public PluginManager pm=Bukkit.getPluginManager();
	
	@Override
	public void onEnable()
	{
		getCommand("spawn").setExecutor(new Comando(this));
		pm.registerEvents(new Eventos(this),this);
		cargarDiaInfinito();
		cargarLocalizacion();
		cargarConfiguracion();
	}
	
	public void cargarLocalizacion()
	{
		try
		{
			String configuracion=getConfig().getString("localizacion.mundo");
			World world=Bukkit.getWorld(getConfig().getString("localizacion.mundo"));
			double x=getConfig().getDouble("localizacion.x");
			double y=getConfig().getDouble("localizacion.y");
			double z=getConfig().getDouble("localizacion.z");
			float yaw=Float.parseFloat(getConfig().getString("localizacion.yaw"));
			float pitch=Float.parseFloat(getConfig().getString("localizacion.pitch"));
			if(configuracion!=null)
			{
				localizacion=new Location(world,x,y,z,yaw,pitch);
			}
		}
		catch(Exception ex)
		{
			l.log(Level.INFO, "[Code] cargarLocalizacion():\n[Code] Error: 1\n[Code] Causa: "+ex.getCause().toString());
		}
	}
	
	@SuppressWarnings("deprecation")
	public void cargarDiaInfinito()
	{
		final World world=Bukkit.getWorld(getConfig().getString("localizacion.mundo")); 
		final boolean debug=getConfig().getBoolean("debug");
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
		{
			public void run()
			{
				if(world.getTime()>=7000L)
				{
					world.setTime(7000L);
					world.setStorm(false);
					if (debug == true) {
						l.log(Level.INFO, "Dia establecido!");
					}
				}
			}
		},20L,1*20L);
	}
	
	public void cargarConfiguracion()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void salvarLocalizacion(Location localizacion)
	{
		getConfig().set("localizacion",localizacion);
		getConfig().set("localizacion.mundo",localizacion.getWorld().getName());
		getConfig().set("localizacion.x",Double.valueOf(localizacion.getX()));
		getConfig().set("localizacion.y",Double.valueOf(localizacion.getY()));
		getConfig().set("localizacion.z",Double.valueOf(localizacion.getZ()));
		getConfig().set("localizacion.yaw",Float.valueOf(localizacion.getYaw()));
		getConfig().set("localizacion.pitch",Float.valueOf(localizacion.getPitch()));
		getConfig().set("debug",false);
		saveConfig();
	}
	
	public String setColor(String s)
	{
		s=ChatColor.translateAlternateColorCodes('&',s);
		return s;
	}
	
	@Override
	public void onDisable() {
		reloadConfig();
		saveConfig();
	}
	
}