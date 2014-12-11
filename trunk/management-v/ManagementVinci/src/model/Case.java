package model;


public class Case {
	
	private int id;
	Double numberOfDayInvoice;
	private String number,client,positon,status,otherConditions,project,country,dailyIndemnity;
	private int startDate,endDate,paymentDate,paymentDueDate;
	private Double amountTaxFree,amountWithTax;
	String dailyRate;
	private boolean historised;
	
	private int eRef;
	
	
	
	public Case(int id, Double numberOfDayInvoice,int startDate, int endDate, int paymentDate,
			int paymentDueDate, String number, String client,
			String positon, String status, String otherConditions,
			String project,  Double amountTaxFree, Double amountWithTax,
			String dailyRate, String dailyIndemnity,String country) {
		super();
		this.id = id;
		this.numberOfDayInvoice = numberOfDayInvoice;
		this.number = number;
		this.client = client;
		this.positon = positon;
		this.status = status;
		this.otherConditions = otherConditions;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentDate = paymentDate;
		this.paymentDueDate = paymentDueDate;
		this.amountTaxFree = amountTaxFree;
		this.amountWithTax = amountWithTax;
		this.dailyRate = dailyRate;
		this.dailyIndemnity = dailyIndemnity;
		
		this.country=country;
		this.setHistorised(false);
		this.eRef=-1;
	}

	public Case(){
		
	}
	
	public Case(int id, Double numberOfDayInvoice,int startDate, int endDate, int paymentDate,
			int paymentDueDate, String number, String client,
			String positon, String status, String otherConditions,
			String project,  Double amountTaxFree, Double amountWithTax,
			String dailyRate, String dailyIndemnity,String country,
			boolean historised) {
		super();
		this.id = id;
		this.numberOfDayInvoice = numberOfDayInvoice;
		this.number = number;
		this.client = client;
		this.positon = positon;
		this.status = status;
		this.otherConditions = otherConditions;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentDate = paymentDate;
		this.paymentDueDate = paymentDueDate;
		this.amountTaxFree = amountTaxFree;
		this.amountWithTax = amountWithTax;
		this.dailyRate = dailyRate;
		this.dailyIndemnity = dailyIndemnity;
		
		this.country=country;
		this.setHistorised(historised);
		this.eRef=-1;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getNumberOfDayInvoice() {
		return numberOfDayInvoice;
	}
	public void setNumberOfDayInvoice(Double double1) {
		this.numberOfDayInvoice = double1;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getPositon() {
		return positon;
	}
	public void setPositon(String positon) {
		this.positon = positon;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOtherConditions() {
		return otherConditions;
	}
	public void setOtherConditions(String otherConditions) {
		this.otherConditions = otherConditions;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public int getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getPaymentDueDate() {
		return paymentDueDate;
	}
	public void setPaymentDueDate(int paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}
	public Double getAmountTaxFree() {
		return amountTaxFree;
	}
	public void setAmountTaxFree(Double amountTaxFree) {
		this.amountTaxFree = amountTaxFree;
	}
	public Double getAmountWithTax() {
		return amountWithTax;
	}
	public void setAmountWithTax(Double amountWithTax) {
		this.amountWithTax = amountWithTax;
	}
	public String getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(String string) {
		this.dailyRate = string;
	}
	public String getDailyIndemnity() {
		return dailyIndemnity;
	}
	public void setDailyIndemnity(String dailyIndemnity) {
		this.dailyIndemnity = dailyIndemnity;
	}
	
	public String toString(){
		return "N° "+number+" ";
	}

	public int geteRef() {
		return eRef;
	}

	public void seteRef(int eRef) {
		this.eRef = eRef;
		//eRef.getCases().add(this);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.getClass().equals(this.getClass())){
			if(((Case) obj).getId()==this.getId()){
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}

	public void setCountry(String aValue) {
		this.country=aValue;
	}

	public String getCountry() {
		return country;
	}

	public boolean isHistorised() {
		return historised;
	}

	public void setHistorised(boolean historised) {
		this.historised = historised;
	}
	
	
	
}
