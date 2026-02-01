package ru.itupolev.hackaton.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String text);
}