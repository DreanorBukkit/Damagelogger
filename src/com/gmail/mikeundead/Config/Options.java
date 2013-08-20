package com.gmail.mikeundead.Config;

public class Options
{
	private boolean isEnabled;
	private boolean damageDone;
	private boolean deaths;
	private boolean healingReceived;
	private boolean dotDamageTaken;
	private boolean healingDone;
	private boolean damageTaken;
	private boolean dotDamageDone;

	public Options()
	{
		this.isEnabled = true;
		this.damageDone = true;
		this.deaths = true;
		this.healingReceived = true;
		this.healingDone = true;
		this.dotDamageDone = true;
		this.dotDamageTaken = true;
		this.damageTaken = true;
	}

	public void setEnabled(boolean enabled)
	{
		this.isEnabled = enabled;
	}

	public boolean getEnabled()
	{
		return this.isEnabled;
	}

	public void setDamageDone()
	{
		this.damageDone = !damageDone;
	}

	public boolean getDamageDone()
	{
		return this.damageDone;
	}

	public boolean getDamageTaken()
	{
		return this.damageTaken;
	}

	public boolean getDotDamageDone()
	{
		return this.dotDamageDone;
	}

	public boolean getDotDamageTaken()
	{
		return this.dotDamageTaken;
	}

	public boolean getHealingDone()
	{
		return this.healingDone;
	}

	public boolean getHealingReceived()
	{
		return this.healingReceived;
	}

	public boolean getDeaths()
	{
		return this.deaths;
	}

	public void setDamageTaken()
	{
		this.damageTaken = !damageTaken;
	}

	public void setDotDamageDone()
	{
		this.dotDamageDone = !dotDamageDone;
	}

	public void setDotDamageTaken()
	{
		this.dotDamageTaken = !dotDamageTaken;
	}

	public void setHealingDone()
	{
		this.healingDone = !healingDone;
	}

	public void setHealingReceived()
	{
		this.healingReceived = !healingReceived;
	}

	public void setDeaths()
	{
		this.deaths = !deaths;
	}
}