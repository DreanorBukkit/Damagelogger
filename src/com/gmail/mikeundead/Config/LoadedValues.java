package com.gmail.mikeundead.Config;

import java.util.ArrayList;

public class LoadedValues
{
	private String damageDone;
	private String damageTaken;
	private String dotDamageDone;
	private String dotDamageTaken;
	private String otherDeath;
	private String ownDeath;
	private String healingReceived;
	private String healingDone;

	public LoadedValues(ArrayList<String> messages)
	{
		this.damageDone = messages.get(0);
		this.damageTaken = messages.get(1);
		this.dotDamageDone = messages.get(2);
		this.dotDamageTaken = messages.get(3);
		this.healingDone = messages.get(4);
		this.healingReceived = messages.get(5);
		this.ownDeath = messages.get(6);
		this.otherDeath = messages.get(7);
	}

	public String getDamageDone()
	{
		return this.damageDone;
	}

	public String getDamageTaken()
	{
		return this.damageTaken;
	}

	public String getDotDamageDone()
	{
		return this.dotDamageDone;
	}

	public String getDotDamageTaken()
	{
		return this.dotDamageTaken;
	}

	public String getHealingDone()
	{
		return this.healingDone;
	}

	public String getHealingReceived()
	{
		return this.healingReceived;
	}

	public String getOwnDeath()
	{
		return this.ownDeath;
	}

	public String getOtherDeath()
	{
		return this.otherDeath;
	}
}
