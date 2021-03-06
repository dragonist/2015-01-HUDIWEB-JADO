package jado.model;

public class Shop {
	private String url;
	private String title;
	private String phone;
	private String banner_url;
	private String logo_url;
	private String theme;
	private String address;
	private String footer;
	

	public Shop(String url, String phone, String address, String title,String banner_url,
			String logo_url, String theme, String footer) {
		super();
		this.url = url;
		this.title = title;
		this.phone = phone;
		this.banner_url = banner_url;
		this.logo_url = logo_url;
		this.theme = theme;
		this.address = address;
		this.footer = footer;
	}


	public Shop(String url, String phone, String address) {
		this(url, phone, address, "제목을 입력해 주세요", "default", "default", "theme1", "입력해 주세요");
	}


	public String getUrl() {
		return url;
	}


	public String getTitle() {
		return title;
	}


	public String getPhone() {
		return phone;
	}


	public String getBanner_url() {
		return banner_url;
	}


	public String getLogo_url() {
		return logo_url;
	}


	public String getTheme() {
		return theme;
	}


	public String getAddress() {
		return address;
	}


	public String getFooter() {
		return footer;
	}



	
	
}
