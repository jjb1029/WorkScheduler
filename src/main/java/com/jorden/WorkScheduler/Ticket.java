package com.jorden.WorkScheduler;

import jakarta.persistence.*;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ticketNo;
	private String ticketStatus; // open, in progress, closed
	private String ticketDescription;
	@OneToOne(optional=false)
	private User ticketCreator;
	@OneToOne(optional=false)
	private User ticketHandler;
	
	public Ticket() {
		
	}
	
	public Ticket(int ticketNo, String ticketDescription, User ticketCreator, User ticketHandler) {
		this.ticketNo = ticketNo;
		this.ticketStatus = "open"; // once a ticket is created, it can only be open
		this.ticketDescription = ticketDescription;
		this.ticketCreator = ticketCreator; 
		this.ticketHandler = ticketHandler;
	}
	
	public Ticket(String ticketDescription, User ticketCreator, User ticketHandler) {
		this.ticketStatus = "open"; // once a ticket is created, it can only be open
		this.ticketDescription = ticketDescription;
		this.ticketCreator = ticketCreator; 
		this.ticketHandler = ticketHandler;
	}
	
	public int getTicketNo() {
		return ticketNo;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public User getTicketCreator() {
		return ticketCreator;
	}

	public void setTicketCreator(User ticketCreator) {
		this.ticketCreator = ticketCreator;
	}

	public User getTicketHandler() {
		return ticketHandler;
	}

	public void setTicketHandler(User ticketHandler) {
		this.ticketHandler = ticketHandler;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ticket Number " + ticketNo + 
				"\n" + ticketDescription);
		
		return sb.toString();
	}

}
