package com.gmail.mikeundead.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.mikeundead.DamageLogger;

public class ConfigHandler
{
	private File configFile;
    private FileConfiguration config;
    private DamageLogger plugin;

    public ConfigHandler(DamageLogger plugin)
    {
    	this.plugin = plugin;
    	this.configFile = new File(this.plugin.getDataFolder(), "config.yml");

	    try
	    {
	    	this.FirstRun();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }

	    this.config = new YamlConfiguration();
	    this.LoadYamls();
    }

	private void FirstRun() throws Exception
	{
	    if(!this.configFile.exists())
	    {
	        this.configFile.getParentFile().mkdirs();

	        this.config = new YamlConfiguration();

	        this.SaveYamls();

	        this.Copy(this.plugin.getResource("config.yml"), configFile);
	    }
	}

	private void Copy(InputStream in, File file)
	{
	    try
	    {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;

	        while((len=in.read(buf)) > 0)
	        {
	            out.write(buf, 0, len);
	        }

	        out.close();
	        in.close();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	private void SaveYamls()
	{
	    try
	    {
	        this.config.save(configFile);
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	}

	private void LoadYamls()
	{
	    try
	    {
	        this.config.load(configFile);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	public LoadedValues LoadValues()
	{
		ArrayList<String> messages = this.LoadMessages();

		return new LoadedValues(messages);
	}

	private ArrayList<String> LoadMessages()
	{
		ArrayList<String> messages = new ArrayList<String>();
		messages.add(this.config.getString("DamageDone"));
		messages.add(this.config.getString("DamageTaken"));
		messages.add(this.config.getString("DotDamageDone"));
		messages.add(this.config.getString("DotDamageTaken"));
		messages.add(this.config.getString("HealingDone"));
		messages.add(this.config.getString("HealingReceived"));
		messages.add(this.config.getString("OwnDeath"));
		messages.add(this.config.getString("OtherDeath"));

		return messages;
	}
}
