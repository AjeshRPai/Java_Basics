package DesignPatterns.Behavioral;

import java.util.*;

enum ChannelTypeEnum
{
	ENGLISH,
	HINDI,
	FRENCH,
	ALL;
}

class Channel
{
	private double frequency;
	private ChannelTypeEnum TYPE;

	public Channel(double freq, ChannelTypeEnum type)
	{
		this.frequency = freq;
		this.TYPE = type;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public ChannelTypeEnum getTYPE()
	{
		return TYPE;
	}

	@Override
	public String toString()
	{
		return "Frequency=" + this.frequency + ", Type=" + this.TYPE;
	}
}

interface ChannelCollection
{
	public void addChannel(Channel c);

	public void removeChannel(Channel c);

	public ChannelIterator iterator(ChannelTypeEnum type);
}

interface ChannelIterator
{
	public boolean hasNext();

	public Channel next();
}

class ChannelCollectionImpl implements ChannelCollection
{
	private List<Channel> channelList = new ArrayList<>();

	@Override
	public void addChannel(Channel c)
	{
		channelList.add(c);
	}

	@Override
	public void removeChannel(Channel c)
	{
		channelList.remove(c);
	}

	@Override
	public ChannelIterator iterator(ChannelTypeEnum type)
	{
		return new ChannelsIteratorImpl(type, channelList);
	}

	private class ChannelsIteratorImpl implements ChannelIterator
	{
		private ChannelTypeEnum channelTypeEnum;
		private List<Channel> channels = new ArrayList<>();
		private int position;

		public ChannelsIteratorImpl(ChannelTypeEnum channelTypeEnum, List<Channel> channels)
		{
			this.channelTypeEnum = channelTypeEnum;
			this.channels = channels;
		}

		@Override
		public boolean hasNext()
		{
			while(position < channels.size())
			{
				Channel channel = channels.get(position);
				if(channel.getTYPE() == channelTypeEnum || channelTypeEnum == ChannelTypeEnum.ALL)
				{
					return true;
				}
				else
					position++;
			}
			return false;

		}

		@Override
		public Channel next()
		{
			Channel channel = channels.get(position);
			position++;
			return channel;
		}
	}
}

class IteratorPatternDemo
{

	public static void main(String[] args)
	{
		ChannelCollection channels = populateChannels();
		ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
		while(baseIterator.hasNext())
		{
			Channel c = baseIterator.next();
			System.out.println(c.toString());
		}
		System.out.println("Another iterator");
		// Channel Type Iterator
		ChannelIterator englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH);
		while(englishIterator.hasNext())
		{
			Channel c = englishIterator.next();
			System.out.println(c.toString());
		}
	}

	private static ChannelCollection populateChannels()
	{
		ChannelCollection channels = new ChannelCollectionImpl();
		channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
		channels.addChannel(new Channel(99.5, ChannelTypeEnum.HINDI));
		channels.addChannel(new Channel(100.5, ChannelTypeEnum.FRENCH));
		channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
		channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
		channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
		channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
		channels.addChannel(new Channel(105.5, ChannelTypeEnum.HINDI));
		channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
		return channels;
	}
}


