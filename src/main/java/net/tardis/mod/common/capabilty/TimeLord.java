package net.tardis.mod.common.capabilty;

public class TimeLord implements ITimeLord {
	
	int regens = 12;
	
	@Override
	public int getRegenerationsLeft() {
		return regens;
	}
	
	@Override
	public int useRegeneration() {
		return --regens;
	}
	
}
