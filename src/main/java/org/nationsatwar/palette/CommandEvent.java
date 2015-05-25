package org.nationsatwar.palette;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class CommandEvent implements ICommand {
	
	private String command;
	
	private List<String> aliases;
	
	public CommandEvent(String command) {
		
		this.command = command;
		
		aliases = new ArrayList<String>();
		aliases.add(command);
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return command;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		
		return (command + " <command>");
	}

	@Override
	public List<String> getAliases() {
		
		return aliases;
	}
	
	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		
	}
	
	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		
		return true;
	}
	
	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		
		return null;
	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		
		return false;
	}
	
	protected void addAlias(String alias) {
		
		aliases.add(alias);
	}
	
	protected String combineArgs(String[] args, int startArgument, int endArgument) {
		
		if (args.length < endArgument + 1 || startArgument > endArgument || startArgument < 0)
			return "Error";
		
		String combinedString = args[startArgument];
		
		for (int i = startArgument + 1; i <= endArgument; i++)
			combinedString += " " + args[i];
		
		return combinedString;
	}
	
	protected String combineArgs(String[] args, int startArgument) {
		
		return combineArgs(args, startArgument, args.length - 1);
	}
}