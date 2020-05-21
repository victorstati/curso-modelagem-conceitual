package com.stati.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.stati.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
