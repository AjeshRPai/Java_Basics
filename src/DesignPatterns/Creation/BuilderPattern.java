package DesignPatterns.Creation;

class Sandwich
{
	private final String filling;
	private final String bread;
	private final String dressing;
	private final String sauce;

	public static class Builder
	{
		private String filling;
		private String bread;
		private String dressing;
		private String sauce;

		public Builder()
		{

		}

		public Sandwich build()
		{
			return new Sandwich(this);
		}

		public Builder addFilling(String filling)
		{
			this.filling = filling;
			return this;
		}

		public Builder addBread(String bread)
		{
			this.bread = bread;
			return this;
		}

		public Builder addDressing(String dressing)
		{
			this.dressing = dressing;
			return this;
		}

		public Builder addSauce(String sauce)
		{
			this.sauce = sauce;
			return this;
		}

	}

	private Sandwich(Builder builder){
		this.bread = builder.bread;
		this.dressing= builder.dressing;
		this.filling = builder.filling;
		this.sauce = builder.sauce;
	}

	public String getFilling()
	{
		return filling;
	}

	public String getBread()
	{
		return bread;
	}

	public String getDressing()
	{
		return dressing;
	}

	public String getSauce()
	{
		return sauce;
	}

	@Override
	public String toString()
	{
		return "Sandwich{" +
			"filling='" + filling + '\'' +
			", bread='" + bread + '\'' +
			", dressing='" + dressing + '\'' +
			", sauce='" + sauce + '\'' +
			'}';
	}
}

class BuilderUsage
{
	public static void main(String[] args)
	{
		Sandwich sandwich = new Sandwich.Builder()
			.addBread("Oat meal")
			.addDressing("Olives")
			.addFilling("Meat")
			.addSauce("Mustard").build();

		System.out.println("sandwich = " + sandwich);


	}
}

