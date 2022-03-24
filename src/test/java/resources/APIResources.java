package resources;

public enum APIResources {
	
	petAPI("/v2/pet"),
	storeAPI("v2/store/order");
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
